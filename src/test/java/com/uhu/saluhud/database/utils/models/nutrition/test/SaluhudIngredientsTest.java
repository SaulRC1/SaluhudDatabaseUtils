package com.uhu.saluhud.database.utils.models.nutrition.test;

import com.uhu.saluhud.database.utils.datasource.SaluhudAdminDataSourceConfig;
import com.uhu.saluhud.database.utils.models.nutrition.Ingredient;
import com.uhu.saluhud.database.utils.services.saluhud.admin.nutrition.SaluhudAdminIngredientService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.Assert;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
@DataJpaTest
@TestPropertySource(locations = {"classpath:datasources/saluhud-admin-datasource.properties"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = "com.uhu.saluhud.database.utils.services.saluhud.admin.nutrition")
@ContextConfiguration(classes = SaluhudAdminDataSourceConfig.class)
public class SaluhudIngredientsTest
{

    @Autowired
    private SaluhudAdminIngredientService ingredientService;

    @Test
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

        Ingredient ingredientSelectedByName = ingredientService.getIngredientByName("Huevo");
        assertEquals(ingredientSelectedByName.getName(), "Huevo");

        harina.setCarbohydrates_amount(75);
        ingredientService.updateIngredient(harina);
        Assert.isTrue(harina.getCarbohydratesAmount() == 75, "");
    }
}
