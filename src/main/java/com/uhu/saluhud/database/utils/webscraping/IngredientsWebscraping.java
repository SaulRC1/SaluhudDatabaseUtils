package com.uhu.saluhud.database.utils.webscraping;

import com.uhu.saluhud.database.utils.models.nutrition.Ingredient;
import org.openqa.selenium.WebDriver;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
public class IngredientsWebscraping
{

    private final WebDriver driver;

    // Constructor por defecto que crea un nuevo WebDriver
    public IngredientsWebscraping()
    {
        // Configuración de Selenium WebDriver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\juald\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors"); // Ignorar errores de certificado
        this.driver = new ChromeDriver(options);
    }

    public List<Ingredient> getIngredientsWebscraping() throws IOException, InterruptedException
    {
        //Conectamos con la url deseada
        this.driver.get("https://www.bedca.net/bdpub/");

        // Encontrar el botón de "Consulta" usando Xpath y hacer clic
        WebElement consultaButton = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[4]/div[4]/a"));
        consultaButton.click();
        // Esperar a que el contenido dinámico se cargue
        Thread.sleep(1000);

        // Encontrar el botón de "Lista alfabética" usando Xpath y hacer clic
        WebElement listaAlfabeticaButton = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[2]/div[2]/table/tbody/tr/td[1]/a"));
        listaAlfabeticaButton.click();
        // Esperar a que el contenido dinámico se cargue
        Thread.sleep(1000);

        //Encontrar el botón de "Todos" para listar todos los ingredientes la web usando Xpath
        WebElement todosButton = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[2]/div[1]/p/a[1]"));
        todosButton.click();
        //Esperar a que el contenido dinánimo de la página cargue
        Thread.sleep(1000);

        // Encontrar la tabla de ingredientes usando XPath
        WebElement ingredientsTable = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[2]/div[2]/table/tbody"));
        List<WebElement> ingredientRows = ingredientsTable.findElements(By.tagName("tr"));

        List<Ingredient> listIngredients = new ArrayList<>();

        // Iterar sobre cada fila de la tabla (cada ingrediente)
        for (int i = 1; i < ingredientRows.size(); i++) {
            WebElement row = ingredientRows.get(i);

            // Hacer clic en el ingrediente
            WebElement ingredientLink = row.findElement(By.xpath("./td[1]/a"));
            ingredientLink.click();
            Thread.sleep(2000);

            // Extraer el nombre del ingrediente
            WebElement nameElement = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[2]/div[2]/h4[1]"));
            String name = nameElement.getText();
            System.out.println("Nombre del ingrediente: " + name);

            int kilocalories = 0, fats = 0, carbs = 0, proteins = 0;

            if (isElementPresent(By.xpath("/html/body/div[1]/div/div[3]/div[2]/div[2]/h4[1]"))) {

                if (name.matches("Cabrito lechal,.*")
                        || name.matches("Carne de cerdo de capa blanca.*")
                        || name.matches("Cordero l.*")) {

                    // Extraer grasas
                    WebElement fatElement = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[2]/div[2]/table[3]/tbody[3]/tr/td[2]"));
                    String fatsStr = fatElement.getText();
                    if (fatsStr.equals("traza")) {
                        fats = 0;
                    } else {
                        fats = (int) Double.parseDouble(fatsStr);
                    }

                    //Extraer calorias
                    WebElement caloriesElement = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[2]/div[2]/table[3]/tbody[2]/tr/td[2]"));
                    String calories = caloriesElement.getText();
                    System.out.println("Valor sin parsear: " + calories);

                    Pattern pattern = Pattern.compile("\\s*\\((\\d+)\\)\\s*");
                    Matcher matcher = pattern.matcher(calories);
                    String kilocaloriesStr = null;
                    if (matcher.find()) {
                        kilocaloriesStr = matcher.group(1); // Obtiene el número entre paréntesis
                        System.out.println("Kilocalorias: " + kilocaloriesStr);
                    }
                    kilocalories = Integer.parseInt(kilocaloriesStr);

                    //Extraer proteinas
                    WebElement proteinElement = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[2]/div[2]/table[3]/tbody[4]/tr/td[2]"));
                    String proteinsStr = proteinElement.getText();
                    if (proteinsStr.equals("traza")) {
                        proteins = 0;
                    } else {
                        proteins = (int) Double.parseDouble(proteinsStr);
                    }

                    // Extraer carbohidratos
                    WebElement carbsElement;
                    if (name.matches("Cabrito lechal,.*")
                            || name.matches("Cordero lechal, costillar, asado")) {
                        carbsElement = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[2]/div[2]/table[3]/tbody[8]/tr/td[2]"));
                    } else if (name.matches("Cordero lechal, costillar, crudo")
                            || name.matches("Cordero lechal, espalda, crudo")
                            || name.matches("Cordero lechal, pierna, crudo")
                            || name.matches("Cordero ligero.*")) {
                        carbsElement = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[2]/div[2]/table[3]/tbody[7]/tr/td[2]"));
                    } else {
                        carbsElement = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[2]/div[2]/table[3]/tbody[9]/tr/td[2]"));
                    }
                    String carbsStr = carbsElement.getText();
                    if (carbsStr.equals("traza")) {
                        carbs = 0;
                    } else {
                        carbs = (int) Double.parseDouble(carbsStr);
                    }

                } else if (name.equals("")) {
                    System.out.println("Datos no disponibles para el ingrediente: " + name);
                } else {
                    // Extraer grasas
                    WebElement fatElement;
                    if (name.equalsIgnoreCase("Zumo de uva")) {
                        fatElement = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[2]/div[2]/table[3]/tbody/tr[5]/td[2]"));
                    } else {
                        fatElement = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[2]/div[2]/table[3]/tbody[4]/tr/td[2]"));
                    }

                    String fatsStr = fatElement.getText();
                    if (fatsStr.equals("traza") || fatsStr.equals("-")) {
                        fats = 0;
                    } else {
                        fats = (int) Double.parseDouble(fatsStr);
                    }

                    // Extraer calorías
                    WebElement caloriesElement;
                    if (name.equalsIgnoreCase("Nispero, conserva en su jugo")) {
                        caloriesElement = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[2]/div[2]/table[3]/tbody[1]/tr[4]/td[2]"));
                        kilocalories = 0;
                    } else if (name.equalsIgnoreCase("Zumo de uva")) {
                        caloriesElement = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[2]/div[2]/table[3]/tbody/tr[4]/td[2]"));
                        kilocalories = 0;
                    } else {
                        caloriesElement = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[2]/div[2]/table[3]/tbody[3]/tr/td[2]"));
                        String calories = caloriesElement.getText();
                        System.out.println("Valor sin parsear: " + calories);

                        Pattern pattern = Pattern.compile("\\s*\\((\\d+)\\)\\s*");
                        Matcher matcher = pattern.matcher(calories);
                        String kilocaloriesStr = null;
                        if (matcher.find()) {
                            kilocaloriesStr = matcher.group(1); // Obtiene el número entre paréntesis
                            System.out.println("Kilocalorias: " + kilocaloriesStr);
                        }
                        kilocalories = Integer.parseInt(kilocaloriesStr);
                    }

                    // Extraer proteínas
                    WebElement proteinElement;
                    if (name.equalsIgnoreCase("Zumo de uva")) {
                        proteinElement = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[2]/div[2]/table[3]/tbody/tr[6]/td[2]"));
                    } else {
                        proteinElement = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[2]/div[2]/table[3]/tbody[5]/tr/td[2]"));
                    }
                    String proteinsStr = proteinElement.getText();
                    if (proteinsStr.equals("traza") || proteinsStr.equals("-")) {
                        proteins = 0;
                    } else {
                        proteins = (int) Double.parseDouble(proteinsStr);
                    }

                    // Extraer carbohidratos
                    WebElement carbsElement;
                    if (name.equalsIgnoreCase("Chocolate negro, con azúcar")
                            || name.equalsIgnoreCase("Chocolate, negro")
                            || name.equalsIgnoreCase("Menta, fresca")
                            || name.equalsIgnoreCase("Mousse de yogur, con frutas")
                            || name.equalsIgnoreCase("Mousse de yogur, natural")
                            || name.equalsIgnoreCase("Seitán")) {
                        carbsElement = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[2]/div[2]/table[3]/tbody[8]/tr/td[2]"));
                    } else if (name.equalsIgnoreCase("Nispero, conserva en su jugo")) {
                        carbsElement = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[2]/div[2]/table[3]/tbody[1]/tr[10]/td[2]"));
                    } else if (name.equalsIgnoreCase("Zumo de uva")) {
                        carbsElement = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[2]/div[2]/table[3]/tbody/tr[10]/td[2]"));
                    } else {
                        carbsElement = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[2]/div[2]/table[3]/tbody[9]/tr/td[2]"));
                    }
                    String carbsStr = carbsElement.getText();
                    if (carbsStr.equals("traza") || carbsStr.equals("-")) {
                        carbs = 0;
                    } else {
                        carbs = (int) Double.parseDouble(carbsStr);
                    }
                }

                Ingredient ingredient = new Ingredient(name, kilocalories, proteins, carbs, fats);
                // Guardar la información del ingrediente
                listIngredients.add(ingredient);
                System.out.println("Ingrediente numero: " + i);

                // Volver a la lista principal
                WebElement backButton = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[2]/div[2]/p/a"));
                backButton.click();
                Thread.sleep(2000);

                // Volver a capturar la tabla de ingredientes para evitar referencias obsoletas
                ingredientsTable = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[2]/div[2]/table/tbody"));
                ingredientRows = ingredientsTable.findElements(By.tagName("tr"));

            } else {
                System.out.println("Datos no disponibles para el ingrediente: " + name);
            }
        }

        System.out.println("Numero de ingredientes obtenidos: " + listIngredients.size());

        // Cerrar el navegador de Selenium cuando hayas terminado
        driver.quit();

        return listIngredients;
    }

    /**
     *
     * @param ingredients
     */
    public void generateIngredientSQL(List<Ingredient> ingredients)
    {
        // Escribir datos en archivo SQL
        try (FileWriter writer = new FileWriter("ingredients.sql")) {
            for (Ingredient ingredient : ingredients) {
                String sql = String.format(
                        "INSERT INTO INGREDIENT (name, kilocalories, protein_amount, carbohydrates_amount, fat_amount) VALUES ('%s', %d, %d, %d, %d);\n",
                        ingredient.getName(), ingredient.getKilocalories(), ingredient.getProteinAmount(), ingredient.getCarbohydratesAmount(), ingredient.getFatAmount()
                );
                writer.write(sql);
            }
        } catch (IOException ex) {
            Logger.getLogger(IngredientsWebscraping.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Verificar si un elemento está presente en la página
    private boolean isElementPresent(By by)
    {
        try {
            driver.findElement(by);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
}
