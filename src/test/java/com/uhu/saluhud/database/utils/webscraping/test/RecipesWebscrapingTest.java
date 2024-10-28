package com.uhu.saluhud.database.utils.webscraping.test;

import com.uhu.saluhud.database.utils.datasource.SaluhudAdminDataSourceConfig;
import com.uhu.saluhud.database.utils.models.nutrition.Recipe;
import com.uhu.saluhud.database.utils.services.saluhud.admin.nutrition.SaluhudAdminIngredientService;
import com.uhu.saluhud.database.utils.services.saluhud.admin.nutrition.SaluhudAdminRecipeService;
import com.uhu.saluhud.database.utils.webscraping.RecipesWebscraping;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Juan Alberto Domínguez Vázquez
 */
@SpringBootTest(classes
        = {
            SaluhudAdminDataSourceConfig.class
        })
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages
        = {
            "com.uhu.saluhud.database.utils.services.saluhud.admin.nutrition"
        })
public class RecipesWebscrapingTest
{

    private RecipesWebscraping recipesWebscraping;

    @Autowired
    private SaluhudAdminIngredientService ingredientService;

    @Autowired
    private SaluhudAdminRecipeService recipeService;

    @Transactional(transactionManager = "saluhudAdminTransactionManager")
    @Rollback
    @Test
    void testGetRecipesWebscraping() throws IOException
    {
        /*
        this.recipesWebscraping = new RecipesWebscraping(ingredientService);
        try {
            List<Recipe> recipes = this.recipesWebscraping.getRecipesWebscraping();
            for (Recipe recipe : recipes) {
                this.recipeService.saveRecipe(recipe);               
            }    
            Assertions.assertTrue(recipeService.findAllRecipes().size() == 40);
            this.recipesWebscraping.generateRecipeSQL(recipes);
        } catch (InterruptedException | ParseException ex) {
            Logger.getLogger(RecipesWebscrapingTest.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
}
