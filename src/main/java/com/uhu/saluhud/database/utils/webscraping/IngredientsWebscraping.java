package com.uhu.saluhud.database.utils.webscraping;

import com.uhu.saluhud.database.utils.models.nutrition.Ingredient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
public class IngredientsWebscraping
{

    public List<Ingredient> getIngredientsWebscraping() throws IOException
    {
        // Configuración de Selenium WebDriver
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Ejecutar en modo headless
        WebDriver driver = new ChromeDriver(options);

        // Navegar a la página inicial
        driver.get("https://www.bedca.net/bdpub/");

        // Pulsar el botón "Consulta"
        driver.findElement(By.xpath("//a[contains(text(), 'Consulta')]")).click();

        // Esperar hasta que la página se cargue completamente 
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // Pulsar el botón "Lista alfabética"
        driver.findElement(By.id("Alfabetica")).click();

        // Esperar hasta que la página se cargue completamente 
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // Pulsar el botón "Todos"
        driver.findElement(By.xpath("//a[contains(text(), 'Todos')]")).click();

        // Extraer los datos
        List<Ingredient> ingredients = new ArrayList<>();
        Document doc = Jsoup.parse(driver.getPageSource());
        Elements rows = doc.select("table tbody tr");  // Selector para las filas de la tabla

        for (Element row : rows)
        {
            Element nameElement = row.select("td#foodname a").first(); // Selector ajustado para extraer el nombre del ingrediente
            if (nameElement != null)
            {
                String foodUrl = nameElement.attr("href");
                Document foodDoc = Jsoup.connect(foodUrl).get();

                String name = nameElement.text(); // Obtener el nombre del ingrediente del elemento <a>
                int kilocalories = Integer.parseInt(foodDoc.select("span.kcal").text().replaceAll("[^0-9]", ""));
                int proteinAmount = Integer.parseInt(foodDoc.select("span.protein").text().replaceAll("[^0-9]", ""));
                int carbohydratesAmount = Integer.parseInt(foodDoc.select("span.carbs").text().replaceAll("[^0-9]", ""));
                int fatAmount = Integer.parseInt(foodDoc.select("span.fat").text().replaceAll("[^0-9]", ""));

                // Crear un nuevo objeto Ingredient con los datos obtenidos
                ingredients.add(new Ingredient(name, kilocalories, proteinAmount, carbohydratesAmount, fatAmount));
            }
        }

        driver.quit();
        return ingredients;
    }

    public void generateIngredientSQL(List<Ingredient> ingredients)
    {
        // Escribir datos en archivo SQL
        try ( FileWriter writer = new FileWriter("ingredients.sql"))
        {
            for (Ingredient ingredient : ingredients)
            {
                String sql = String.format(
                        "INSERT INTO INGREDIENT (name, kilocalories, protein_amount, carbohydrates_amount, fat_amount) VALUES ('%s', %d, %d, %d, %d);\n",
                        ingredient.getName(), ingredient.getKilocalories(), ingredient.getProteinAmount(), ingredient.getCarbohydratesAmount(), ingredient.getFatAmount()
                );
                writer.write(sql);
            }
        } catch (IOException ex)
        {
            Logger.getLogger(IngredientsWebscraping.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
