package com.uhu.saluhud.database.utils.models.nutrition.test;

import com.uhu.saluhud.database.utils.models.nutrition.Ingredient;
import com.uhu.saluhud.database.utils.services.saluhud.admin.nutrition.IngredientService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
public class SaluhudIngredientsTest {

    @Autowired
    private IngredientService ingredientService;

    @Test
    public void testIngredientCRUD() {
        Ingredient harina = new Ingredient("Harina", 364, 10, 73, 1);
        Ingredient carneDeRes = new Ingredient("Carne de Res", 250, 26, 0, 17);
        Ingredient huevo = new Ingredient("Huevo", 68, 6, 0, 5);
        Ingredient lechuga = new Ingredient("Lechuga", 5, 1, 2, 0);

        ingredientService.saveIngredient(harina);
        ingredientService.saveIngredient(carneDeRes);
        ingredientService.saveIngredient(huevo);
        ingredientService.saveIngredient(lechuga);

        Ingredient ingredientSelectedById = ingredientService.getIngredientById(harina.getId());
        assertEquals(ingredientSelectedById.getName(), "Harina");

        Ingredient ingredientSelectedByName = ingredientService.getIngredientByName("Huevo");
        assertEquals(ingredientSelectedByName.getName(), "Huevo");

        harina.setCarbohydrates_amount(75);
        ingredientService.updateIngredient(harina);
        ingredientService.deleteIngredient(lechuga);
    }
}
