package com.uhu.saluhuddatabaseutils.test.models.nutrition;

import com.uhu.saluhuddatabaseutils.models.nutrition.Ingredient;
import com.uhu.saluhuddatabaseutils.services.administrationportal.nutrition.AdministrationPortalIngredientService;
import com.uhu.saluhuddatabaseutils.test.configuration.BaseAdministrationPortalJpaTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
public class SaluhudIngredientsTest extends BaseAdministrationPortalJpaTest
{

    @Autowired
    private AdministrationPortalIngredientService ingredientService;

    @Test
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    @Rollback
    public void testIngredientCRUD()
    {
        Ingredient harina = new Ingredient("Harina", 364, 10, 73, 1);
        Ingredient carneDeRes = new Ingredient("Carne de Res", 250, 26, 0, 17);
        Ingredient huevo = new Ingredient("Huevo", 68, 6, 0, 5);

        ingredientService.saveIngredient(harina);
        ingredientService.saveIngredient(carneDeRes);
        ingredientService.saveIngredient(huevo);

        Ingredient ingredientSelectedById = ingredientService.getIngredientById(harina.getId());
        assertEquals(ingredientSelectedById.getName(), "Harina");

        Ingredient ingredientSelectedByName = ingredientService.findByName("Huevo");
        assertEquals(ingredientSelectedByName.getName(), "Huevo");

        harina.setCarbohydratesAmount(75);
        ingredientService.updateIngredient(harina);
        Assert.isTrue(harina.getCarbohydratesAmount() == 75, "");
    }
}
