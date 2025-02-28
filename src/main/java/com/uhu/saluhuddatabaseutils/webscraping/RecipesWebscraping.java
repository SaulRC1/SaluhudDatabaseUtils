package com.uhu.saluhuddatabaseutils.webscraping;

import com.uhu.saluhuddatabaseutils.models.nutrition.Allergenic;
import com.uhu.saluhuddatabaseutils.models.nutrition.Ingredient;
import com.uhu.saluhuddatabaseutils.models.nutrition.Recipe;
import com.uhu.saluhuddatabaseutils.models.nutrition.RecipeElaborationStep;
import com.uhu.saluhuddatabaseutils.models.nutrition.RecipeIngredient;
import com.uhu.saluhuddatabaseutils.models.nutrition.RecipeIngredientId;
import com.uhu.saluhuddatabaseutils.services.administrationportal.nutrition.AdministrationPortalIngredientService;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
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

    private final AdministrationPortalIngredientService ingredientService;
    private final WebDriver driver;
    private final OpenAIClient openAIClient;

    // Constructor por defecto que crea un nuevo WebDriver
    /**
     *
     * @param ingredientService
     */
    public RecipesWebscraping(AdministrationPortalIngredientService ingredientService)
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
        List<RecipeIngredient> recipesIngredients = new ArrayList<>();
        List<RecipeElaborationStep> elaborationSteps = new ArrayList<>();

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

                // Extraer la descripcion de la receta
                String descripcion = "";
                int x = 0;
                while (x < pasos.size() && !pasos.get(x).getText().equals("")) {
                    descripcion = descripcion.concat(pasos.get(x).getText());
                    x++;
                }
                recipe.setDescription(descripcion);

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
                        .append("\n\n Para cada uno de los ingredientes que te paso al final, "
                                + "busca si coincide con alguno de la lista anterior que te pasé. "
                                + "Si existe una versión parecida o una variante, considéralo como válido, aunque no sea exacto. "
                                + "Normaliza el nombre del ingrediente de la receta que te venga, al nombre que coincida en la lista de ingredientes que te pasé"
                                + "Por ejemplo: azúcar, coincidiría con azúcar blanca."
                                + "Dame solo como respuesta la lista final de ingredientes normalizados por cada receta, nada más ");

                for (String ingredientName : ingredientes.getText().split("\n")) {
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

                // Patrón para encontrar cantidad y unidad
                //^\\s*-?\\s*(\\d+(?:\\.\\d+)?)?\\s*(gramos|½|¾|g|ml|litros|tazas|cucharadas|unidades|restos|vaso|Vaso|kg|mL)?\\s*(.*)$$
                Pattern pattern = Pattern.compile("^\\s*-?\\s*(\\d+\\s+\\d+/\\d+|\\d+/\\d+|[½¼¾]|\\d+\\.?\\d*)?\\s*(vaso|Vaso|vasito|Restos|gramos|ml|litros|tazas|cucharadas|unidades|restos|kg|mL|L|g)?\\s*(?:de\\s)?(.+)$");
                String[] ingredients = apiResponse.split("\n");
                for (String ingredient : ingredients) {
                    Matcher matcher = pattern.matcher(ingredient.trim());
                    if (matcher.find()) {
                        String quantityStr = matcher.group(1);
                        BigDecimal quantity = BigDecimal.ZERO;
                        if (quantityStr != null && !quantityStr.isEmpty()) {
                            if (quantityStr.equals("½") || quantityStr.equals("1/2")) {
                                quantity = new BigDecimal("0.5");
                            } else if (quantityStr.equals("¼") || quantityStr.equals("1/4")) {
                                quantity = new BigDecimal("0.25");
                            } else if (quantityStr.equals("¾") || quantityStr.equals("3/4")) {
                                quantity = new BigDecimal("0.75");
                            } else if (quantityStr.contains("/")) {
                                String[] fractionParts;

                                if (quantityStr.contains(" ")) {
                                    fractionParts = quantityStr.split(" ")[1].split("/");
                                    quantity = new BigDecimal(fractionParts[0]).divide(new BigDecimal(fractionParts[1]), 2, RoundingMode.HALF_UP);
                                } else {
                                    fractionParts = quantityStr.split("/");
                                    quantity = new BigDecimal(fractionParts[0]).divide(new BigDecimal(fractionParts[1]), 2, RoundingMode.HALF_UP);
                                }
                            } else {
                                quantity = new BigDecimal(quantityStr);
                            }
                        }

                        String unitOfMeasure = matcher.group(2);
                        if (unitOfMeasure == null || unitOfMeasure.trim().isEmpty()) {
                            unitOfMeasure = "unidad";  // Asignar "unidad" por defecto si no se encuentra unidad
                        }

                        String ingredientName = matcher.group(3).trim();
                        // Eliminar la preposición "de" si está al principio del nombre
                        if (ingredientName.startsWith("de ")) {
                            ingredientName = ingredientName.substring(3).trim(); // Eliminar "de "
                        }
                        // Convertir la primera letra del nombre del ingrediente a mayúscula
                        ingredientName = ingredientName.substring(0, 1).toUpperCase() + ingredientName.substring(1);

                        //Casos especiales
                        if (ingredientName.equalsIgnoreCase("berenjenas")) {
                            ingredientName = "Berenjena";
                        }
                        if (ingredientName.equalsIgnoreCase("huevo") || ingredientName.equalsIgnoreCase("huevos")) {
                            ingredientName = "Huevo de gallina fresco";
                        }
                        if (ingredientName.equalsIgnoreCase("huevos de gallina, enteros, crudos") || ingredientName.equalsIgnoreCase("Huevo de gallina, entero, crudo")) {
                            ingredientName = "Huevo de gallina fresco";
                        }
                        if (ingredientName.equalsIgnoreCase("pimiento rojo") || ingredientName.equalsIgnoreCase("pimiento verde")) {
                            ingredientName = "Pimiento rojo, crudo";
                        }
                        if (ingredientName.equalsIgnoreCase("cebolleta")) {
                            ingredientName = "Cebolla";
                        }
                        if (ingredientName.equalsIgnoreCase("rape")) {
                            ingredientName = "Rape, crudo";
                        }
                        if (ingredientName.equalsIgnoreCase("agua")) {
                            ingredientName = "Agua de la red";
                        }
                        if (ingredientName.equalsIgnoreCase("diente de ajo") || ingredientName.equalsIgnoreCase("dientes de ajo")) {
                            ingredientName = "Ajo";
                        }
                        if (ingredientName.equalsIgnoreCase("yogur, líquido, entero, natural") || ingredientName.equalsIgnoreCase("yogur, líquido, entero, sabor natural")) {
                            ingredientName = "Yogur líquido, natural, azucarado";
                        }
                        if (ingredientName.equalsIgnoreCase("mantequilla") || ingredientName.equalsIgnoreCase("mantequilla (no está en la lista, se omite)")) {
                            ingredientName = "Mantequilla salada";
                        }
                        if (ingredientName.equalsIgnoreCase("Y medio de harina de trigo")) {
                            ingredientName = "Harina de trigo";
                        }
                        if (ingredientName.equalsIgnoreCase("Vaso de almendra, cruda")) {
                            ingredientName = "Almendra, cruda";
                        }
                        if (ingredientName.equalsIgnoreCase("1 ½ vaso de harina de trigo")) {
                            ingredientName = "Harina de trigo";
                        }
                        if (ingredientName.equalsIgnoreCase("restos de patata")) {
                            ingredientName = "Patata, cruda";
                        }

                        System.out.println("Quantity: " + quantity);
                        System.out.println("Unidad: " + unitOfMeasure);
                        System.out.println("Nombre: " + ingredientName);

                        Ingredient ingredientObj = ingredientService.getIngredientByName(ingredientName);
                        if (ingredientObj != null) {
                            // Crear el RecipeIngredient y agregarlo
                            RecipeIngredientId recipeIngredientId = new RecipeIngredientId();
                            RecipeIngredient recipeIngredient = new RecipeIngredient(recipe, ingredientObj, quantity, unitOfMeasure);
                            recipeIngredient.setId(recipeIngredientId);

                            Set<Allergenic> allergenics = ingredientService.getAllergensForIngredient(ingredientObj);
                            recipe.setAllergenics(allergenics);

                            recipesIngredients.add(recipeIngredient);
                        } else {
                            System.out.println("Ingrediente no encontrado");
                        }
                    }
                }

                recipe.setRecipeIngredients(recipesIngredients);

                // Guardar los pasos de elaboración
                for (int stepIndex = 0; stepIndex < pasos.size(); stepIndex++) {
                    RecipeElaborationStep step = new RecipeElaborationStep();
                    step.setStepNumber(stepIndex + 1);
                    step.setStepDescription(pasos.get(stepIndex).getText());
                    step.setRecipe(recipe);
                    elaborationSteps.add(step);
                }
                recipe.setElaborationSteps(elaborationSteps);

                // Agregar la receta a la lista
                recipes.add(recipe);
                o++;

                // Volver atrás para iterar sobre la siguiente receta
                driver.navigate().back();
                Thread.sleep(2000);
                recetasLinks = driver.findElements(By.xpath("/html/body/main/div[2]//div//a[@title='Ver más']"));
            }

        }

        //generateRecipeIngredientSQL(recipesIngredients);
        //generateRecipeElaborationStepSQL(recipes);
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

    private void generateRecipeIngredientSQL(List<RecipeIngredient> recipesIngredients)
    {
        try (FileWriter writer = new FileWriter("recipesIngredients.sql")) {
            for (RecipeIngredient recipeIngredient : recipesIngredients) {
                String sqlRecipe = String.format(
                        "INSERT INTO RECIPE_INGREDIENT (recipe_name, ingredient_name, quantity, unit) VALUES ('%s', '%s', '%s', '%s');\n",
                        recipeIngredient.getQuantity().toString(), recipeIngredient.getUnit()
                );
                writer.write(sqlRecipe);
            }
        } catch (IOException ex) {
            Logger.getLogger(IngredientsWebscraping.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void generateRecipeElaborationStepSQL(List<Recipe> recipes)
    {
        try (FileWriter writer = new FileWriter("recipesElaborationStep.sql")) {
            for (Recipe recipe : recipes) {
                List<RecipeElaborationStep> steps = recipe.getElaborationSteps();
                for (RecipeElaborationStep step : steps) {
                    String sqlRecipe = String.format(
                            "INSERT INTO RECIPE_ELABORATION_STEP (step_number, step_description, id_recipe) VALUES ('%s', '%s', '%s');\n",
                            step.getStepNumber(), step.getStepDescription(), recipe.getId()
                    );
                    writer.write(sqlRecipe);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(IngredientsWebscraping.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
