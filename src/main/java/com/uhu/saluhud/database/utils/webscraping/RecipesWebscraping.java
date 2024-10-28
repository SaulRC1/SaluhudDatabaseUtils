package com.uhu.saluhud.database.utils.webscraping;

import com.uhu.saluhud.database.utils.models.nutrition.Allergenic;
import com.uhu.saluhud.database.utils.models.nutrition.Ingredient;
import com.uhu.saluhud.database.utils.models.nutrition.Recipe;
import com.uhu.saluhud.database.utils.models.nutrition.RecipeElaborationStep;
import com.uhu.saluhud.database.utils.services.saluhud.admin.nutrition.SaluhudAdminIngredientService;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
public class RecipesWebscraping
{

    private final SaluhudAdminIngredientService ingredientService;
    private final WebDriver driver;
    private final OpenAIClient openAIClient;

    // Constructor por defecto que crea un nuevo WebDriver
    /**
     *
     * @param ingredientService
     */
    public RecipesWebscraping(SaluhudAdminIngredientService ingredientService)
    {
        // Configuración de Selenium WebDriver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\juald\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors"); // Ignorar errores de certificado
        this.driver = new ChromeDriver(options);
        this.ingredientService = ingredientService;
        this.openAIClient = new OpenAIClient();
    }

    /**
     *
     * @return @throws InterruptedException
     * @throws java.lang.InterruptedException
     * @throws IOException
     * @throws ParseException
     */
    public List<Recipe> getRecipesWebscraping() throws InterruptedException, IOException, ParseException
    {
        List<Recipe> recipes = new ArrayList<>();

        this.driver.get("https://www.alimentosdespana.es/es/estrategia-alimentos-espana/aprovecha-los-alimentos/recetas");
        driver.manage().window().maximize();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");

        int o = 0;
        while (true && o != 40) {
            List<WebElement> recetasLinks = driver.findElements(By.xpath("/html/body/main/div[2]//div//a[@title='Ver más']"));
            for (int i = 0; i < recetasLinks.size(); i++) {
                WebElement recetaLink = recetasLinks.get(i);

                boolean clicked = false;
                while (!clicked) {
                    try {
                        WebDriverWait wait = new WebDriverWait(driver, 10);
                        WebElement clickableLink = wait.until(ExpectedConditions.elementToBeClickable(recetaLink));
                        clickableLink.click();
                        clicked = true;
                    } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
                        recetasLinks = driver.findElements(By.xpath("/html/body/main/div[2]//div//a[@title='Ver más']"));
                        recetaLink = recetasLinks.get(i);
                    }
                }

                Thread.sleep(2000);

                // Extraer el nombre de la receta
                WebElement nombreReceta = driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div/h2"));
                // Extraer los ingredientes de la receta
                WebElement ingredientes = driver.findElement(By.xpath(nombreReceta.getText().equalsIgnoreCase("huevos rellenos")
                        ? "/html/body/main/div[2]/div[1]/div/div[1]/div/ul"
                        : "/html/body/main/div[2]/div[1]/div/div[2]/div//ul"));
                // Extraer los pasos de elaboración
                List<WebElement> pasos = driver.findElements(By.xpath("/html/body/main/div[2]/div[1]/div/div[3]/div/p"));

                Recipe recipe = new Recipe();
                recipe.setName(nombreReceta.getText());
                recipe.setIngredientsDescription(ingredientes.getText());

                String descripcion = "";
                int x = 0;
                while (x < pasos.size() && !pasos.get(x).getText().equals("")) {
                    descripcion = descripcion.concat(pasos.get(x).getText());
                    x++;
                }
                recipe.setDescription(descripcion);

                // Normalizar ingredientes
                List<Ingredient> normalizedIngredients = new ArrayList<>();

                // Agrupar ingredientes de la receta
                Map<String, Integer> ingredientMap = new HashMap<>();
                for (String ingredientText : ingredientes.getText().split("\n")) {
                    Matcher matcher = Pattern.compile("^(\\d+)\\s+(.*)").matcher(ingredientText);
                    String ingredientName = matcher.find() ? matcher.group(2) : ingredientText;
                    Integer quantity = matcher.find() ? Integer.valueOf(matcher.group(1)) : 1;

                    // Agrupar ingredientes
                    ingredientMap.put(ingredientName, ingredientMap.getOrDefault(ingredientName, 0) + quantity);
                }

                // Crear el contexto con la lista de ingredientes en la base de datos
                StringBuilder contextBuilder = new StringBuilder("Aqui tienes la lista de ingredientes de mi base de datos: ");
                for (Ingredient dbIngredient : ingredientService.findAllIngredients()) {
                    contextBuilder.append(dbIngredient.getName()).append(", ");
                }

                // Quitar la última coma y espacio del contexto
                if (contextBuilder.length() > 0) {
                    contextBuilder.setLength(contextBuilder.length() - 2);
                }

                // Crear el prompt para la API con el contexto y los ingredientes de la receta
                StringBuilder promptBuilder = new StringBuilder(contextBuilder)
                        .append("\n\n Para cada uno de los siguientes ingredientes, busca si coincide con alguno de la lista anterior que te pasé. Si existe una versión parecida o una variante, indica siempre que sí coincide aunque no sea exacta. No tengas en cuenta las cantidades en las comparaciones. Contesta solo con si o no: ");

                // Agregar todos los ingredientes de la receta al prompt en una sola línea
                for (String ingredientName : ingredientMap.keySet()) {
                    promptBuilder.append(ingredientName).append(", ");
                }

                // Quitar la última coma y espacio del prompt
                if (promptBuilder.length() > 0) {
                    promptBuilder.setLength(promptBuilder.length() - 2);
                }

                System.out.println(promptBuilder.toString());

                // Llamar a la API con el prompt que incluye el contexto
                String apiResponse = openAIClient.callOpenAIAPI(promptBuilder.toString());

                // Procesar la respuesta de la API
                System.out.println("API Response: " + apiResponse);

                // Procesar cada ingrediente de acuerdo a la respuesta
                for (Map.Entry<String, Integer> entry : ingredientMap.entrySet()) {
                    String ingredientName = entry.getKey();
                    int quantity = entry.getValue();

                    // Revisar si la respuesta contiene "yes" para el ingrediente
                    if (apiResponse.equalsIgnoreCase("yes")) {
                        Ingredient ingredient = ingredientService.getIngredientByName(ingredientName);
                        if (ingredient != null) {
                            // Agregar el ingrediente a la lista según la cantidad
                            for (int j = 0; j < quantity; j++) {
                                normalizedIngredients.add(ingredient);
                            }

                            // Agregar alérgenos asociados
                            Set<Allergenic> allergenics = ingredientService.getAllergensForIngredient(ingredient);
                            recipe.setAllergenics(allergenics);
                        }
                    }
                }

                // Asignar los ingredientes normalizados a la receta
                recipe.setIngredients(normalizedIngredients);

                // Guardar los pasos de elaboración
                List<RecipeElaborationStep> elaborationSteps = new ArrayList<>();
                for (int stepIndex = 0; stepIndex < pasos.size(); stepIndex++) {
                    RecipeElaborationStep step = new RecipeElaborationStep();
                    step.setStepNumber(stepIndex + 1);
                    step.setStepDescription(pasos.get(stepIndex).getText());
                    elaborationSteps.add(step);
                }
                recipe.setElaborationSteps(elaborationSteps);

                // Agregar la receta a la lista
                recipe.setIngredients(normalizedIngredients);
                recipes.add(recipe);
                o++;

                // Volver atrás para iterar sobre la siguiente receta
                driver.navigate().back();
                Thread.sleep(2000);
                recetasLinks = driver.findElements(By.xpath("/html/body/main/div[2]//div//a[@title='Ver más']"));
            }
        }
        openAIClient.shutdown();
        driver.quit();
        return recipes;
    }

    /**
     *
     * @param recipes
     */
    public void generateRecipeSQL(List<Recipe> recipes)
    {
        // Escribir datos en archivo SQL
        try (FileWriter writer = new FileWriter("recipes.sql")) {
            for (Recipe recipe : recipes) {
                String sqlRecipe = String.format(
                        "INSERT INTO RECIPE (name, description, ingredients_description) VALUES ('%s', '%s', '%s');\n",
                        recipe.getName(), recipe.getDescription(), recipe.getIngredientsDescription()
                );
                writer.write(sqlRecipe);
            }
        } catch (IOException ex) {
            Logger.getLogger(IngredientsWebscraping.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
