package com.uhu.saluhuddatabaseutils.test.webscraping;

import com.uhu.saluhuddatabaseutils.services.administrationportal.nutrition.AdministrationPortalIngredientService;
import com.uhu.saluhuddatabaseutils.test.configuration.BaseTest;
import com.uhu.saluhuddatabaseutils.webscraping.RecipesWebscraping;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Juan Alberto Domínguez Vázquez
 */
public class RecipesWebscrapingTest extends BaseTest
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
