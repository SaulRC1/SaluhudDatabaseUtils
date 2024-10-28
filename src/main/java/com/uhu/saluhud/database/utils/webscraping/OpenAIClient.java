package com.uhu.saluhud.database.utils.webscraping;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Juan Alberto Domínguez Vázquez
 */
public class OpenAIClient
{

    private static final String API_KEY = System.getenv("OPENAI");
    private static final int REQUEST_LIMIT = 25;
    private static final long TOKEN_LIMIT = 190000;

    private final OpenAiService service = new OpenAiService(API_KEY);
    private final AtomicInteger requestCount = new AtomicInteger(0);
    private long tokenCount = 0;
    private final Lock lock = new ReentrantLock();
    private final Condition limitResetCondition = lock.newCondition();

    // ScheduledExecutorService para resetear los contadores
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public OpenAIClient()
    {
        scheduler.scheduleAtFixedRate(() -> {
            lock.lock();
            try {
                requestCount.set(0);
                tokenCount = 0;
                limitResetCondition.signalAll(); // Notificar a todos los hilos en espera
                System.out.println("Contadores reseteados.");
            } finally {
                lock.unlock();
            }
        }, 2, 2, TimeUnit.MINUTES);
    }

    /**
     * Llama a la API de OpenAI para comparar nombres de ingredientes y
     * normalizarlos.
     *
     * @param prompt Pregunta que compara dos ingredientes.
     * @return La respuesta de OpenAI con respecto a la comparación de los
     * ingredientes.
     * @throws IOException Si ocurre un error durante la llamada HTTP.
     * @throws java.lang.InterruptedException
     */
    public String callOpenAIAPI(String prompt) throws IOException, InterruptedException
    {
        lock.lock();
        try {
            // Verificar si se ha alcanzado el límite de solicitudes o tokens
            while (requestCount.get() >= REQUEST_LIMIT || tokenCount >= TOKEN_LIMIT) {
                System.out.println("Límite de solicitudes o tokens alcanzado. Esperando el siguiente intervalo...");
                limitResetCondition.await(); // Esperar hasta que se reseteen los contadores
            }

            // Construir y enviar la solicitud
            List<ChatMessage> messages = new ArrayList<>();
            ChatMessage userMessage = new ChatMessage(ChatMessageRole.USER.value(), prompt);
            messages.add(userMessage);

            ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                    .model("gpt-4o-mini")
                    .messages(messages)
                    .maxTokens(8500)
                    .temperature(0.7)
                    .build();

            ChatCompletionResult result = service.createChatCompletion(chatCompletionRequest);

            // Actualizar el conteo de tokens y solicitudes
            tokenCount += result.getUsage().getTotalTokens();
            System.out.println("Tokens usados: " + tokenCount);
            requestCount.incrementAndGet();
            System.out.println("Peticiones realizadas: " + requestCount.get());

            // Obtener la respuesta
            ChatMessage response = result.getChoices().get(0).getMessage();
            return response.getContent().trim().toLowerCase();
        } finally {
            lock.unlock();
        }
    }

    // Método para detener el scheduler cuando se termine el uso del cliente
    public void shutdown()
    {
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(1, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
        }
    }
}
