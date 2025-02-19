package com.uhu.saluhuddatabaseutils.test.webscraping;

import com.uhu.saluhuddatabaseutils.datasource.SaluhudAdministrationPortalDataSourceConfig;
import com.uhu.saluhuddatabaseutils.models.nutrition.Recipe;
import com.uhu.saluhuddatabaseutils.services.administrationportal.nutrition.AdministrationPortalIngredientService;
import com.uhu.saluhuddatabaseutils.webscraping.RecipesWebscraping;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            SaluhudAdministrationPortalDataSourceConfig.class
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
    private AdministrationPortalIngredientService ingredientService;

    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    @Rollback
    @Test
    void testGetRecipesWebscraping() throws IOException
    {
        /*
        this.recipesWebscraping = new RecipesWebscraping(ingredientService);
        try {
            this.recipesWebscraping.getRecipesWebscraping();
            //this.recipesWebscraping.generateRecipeSQL(recipes);
        } catch (InterruptedException | ParseException ex) {
            Logger.getLogger(RecipesWebscrapingTest.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
}
