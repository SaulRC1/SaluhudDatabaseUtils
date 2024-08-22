package com.uhu.saluhud.database.utils.models.nutrition.test;

import com.uhu.saluhud.database.utils.datasource.SaluhudAdminDataSourceConfig;
import com.uhu.saluhud.database.utils.models.nutrition.Allergenic;
import com.uhu.saluhud.database.utils.services.saluhud.admin.nutrition.SaluhudAdminRecipeService;
import com.uhu.saluhud.database.utils.services.saluhud.admin.nutrition.SaluhudAdminRecipeElaborationStepService;
import com.uhu.saluhud.database.utils.models.nutrition.Ingredient;
import com.uhu.saluhud.database.utils.models.nutrition.Recipe;
import com.uhu.saluhud.database.utils.models.nutrition.RecipeElaborationStep;
import com.uhu.saluhud.database.utils.services.saluhud.admin.nutrition.SaluhudAdminAllergenicService;
import com.uhu.saluhud.database.utils.services.saluhud.admin.nutrition.SaluhudAdminIngredientService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
@TestPropertySource(locations = { "classpath:datasources/saluhud-admin-datasource.properties" })
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = "com.uhu.saluhud.database.utils.services.saluhud.admin.nutrition")
@ContextConfiguration(classes = SaluhudAdminDataSourceConfig.class)
public class SaluhudRecipeElaborationStepsTest {

    @Autowired
    private SaluhudAdminRecipeElaborationStepService recipeElaborationStepService;
    
    @Autowired
    private SaluhudAdminRecipeService recipeService;
    
    @Autowired
    private SaluhudAdminIngredientService ingredientService;
    
    @Autowired
    private SaluhudAdminAllergenicService allergenicService;
    
    @Test
    public void testRecipeElaborationStepsTestCRUD() {

        List<RecipeElaborationStep> elaborationSteps = new ArrayList<>();
        List<Ingredient> ingredients = new ArrayList<>();
        Set<Allergenic> allergenics = new HashSet<>();

        Ingredient harina = new Ingredient("Harina", 364, 10, 73, 1);
        Ingredient huevo = new Ingredient("Huevo", 68, 6, 0, 5);
        Allergenic leche = new Allergenic("Leche");
        RecipeElaborationStep batirHuevos = new RecipeElaborationStep("Batir los huevos bien, a mano o con la batidora durante 5 min", 1);

        ingredientService.saveIngredient(harina);
        ingredientService.saveIngredient(huevo);
        allergenicService.saveAllergenic(leche);

        ingredients.add(huevo);
        ingredients.add(harina);
        allergenics.add(leche);
        elaborationSteps.add(batirHuevos);

        Recipe Bizcocho = new Recipe("Bizcocho", "Bizcocho de limón al horno", "Huevos, harina, sal, azucar, agua, aceite", ingredients, allergenics, elaborationSteps);

        recipeElaborationStepService.saveRecipeElaborationStep(batirHuevos);
        recipeService.saveRecipe(Bizcocho);
        
        Assert.isTrue(this.recipeElaborationStepService.findAllSteps().contains(batirHuevos), "");
        Assert.isTrue(this.recipeService.findAllRecipes().contains(Bizcocho), "");
    }
}
