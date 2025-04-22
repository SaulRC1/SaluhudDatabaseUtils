package com.uhu.saluhuddatabaseutils.test.models.nutrition;

import com.uhu.saluhuddatabaseutils.datasource.SaluhudAdministrationPortalDataSourceConfig;
import com.uhu.saluhuddatabaseutils.models.nutrition.Allergenic;
import com.uhu.saluhuddatabaseutils.services.administrationportal.nutrition.AdministrationPortalRecipeService;
import com.uhu.saluhuddatabaseutils.services.administrationportal.nutrition.AdministrationPortalRecipeElaborationStepService;
import com.uhu.saluhuddatabaseutils.models.nutrition.Ingredient;
import com.uhu.saluhuddatabaseutils.models.nutrition.Recipe;
import com.uhu.saluhuddatabaseutils.models.nutrition.RecipeElaborationStep;
import com.uhu.saluhuddatabaseutils.models.nutrition.RecipeIngredient;
import com.uhu.saluhuddatabaseutils.models.nutrition.RecipeIngredientId;
import com.uhu.saluhuddatabaseutils.services.administrationportal.nutrition.AdministrationPortalIngredientService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
@DataJpaTest
@TestPropertySource(locations = {"classpath:datasources/saluhud-administration-portal-datasource.properties"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = "com.uhu.saluhuddatabaseutils.services.administrationportal.nutrition")
@ContextConfiguration(classes = SaluhudAdministrationPortalDataSourceConfig.class)
public class SaluhudRecipeElaborationStepsTest
{

    @Autowired
    private AdministrationPortalRecipeElaborationStepService recipeElaborationStepService;

    @Autowired
    private AdministrationPortalRecipeService recipeService;

    @Autowired
    private AdministrationPortalIngredientService ingredientService;

    @Test
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    @Rollback
    public void testRecipeElaborationStepsTestCRUD()
    {
        List<RecipeElaborationStep> elaborationSteps = new ArrayList<>();
        List<RecipeIngredient> recipeIngredients = new ArrayList<>();
        Set<Allergenic> allergenics = new HashSet<>();

        Ingredient ingredient1 = ingredientService.findById(20);
        Ingredient ingredient2 = ingredientService.findById(30);

        allergenics.addAll(ingredientService.getAllergensForIngredient(ingredient1));
        allergenics.addAll(ingredientService.getAllergensForIngredient(ingredient2));

        Recipe Bizcocho = new Recipe("Bizcocho", "Bizcocho de limón al horno", "Huevos, harina, sal, azucar, agua, aceite", 0, "");

        RecipeIngredientId recipeIngredientId1 = new RecipeIngredientId();
        RecipeIngredientId recipeIngredientId2 = new RecipeIngredientId();

        RecipeIngredient recipeIngredient = new RecipeIngredient(Bizcocho, ingredient1, BigDecimal.valueOf(150), "g");
        RecipeIngredient recipeIngredient2 = new RecipeIngredient(Bizcocho, ingredient2, BigDecimal.valueOf(1), "l");

        recipeIngredients.add(recipeIngredient);
        recipeIngredients.add(recipeIngredient2);

        Bizcocho.setAllergenics(allergenics);

        RecipeElaborationStep batirHuevos = new RecipeElaborationStep("Batir los huevos bien, a mano o con la batidora durante 5 min", 1, Bizcocho);
        elaborationSteps.add(batirHuevos);
        Bizcocho.setElaborationSteps(elaborationSteps);

        recipeIngredient.setId(recipeIngredientId1);
        recipeIngredient2.setId(recipeIngredientId2);
        Bizcocho.setRecipeIngredients(recipeIngredients);
        recipeService.saveRecipe(Bizcocho);

        System.out.println("Id: " + Bizcocho.getId());

        Assert.isTrue(this.recipeElaborationStepService.findAllSteps().contains(batirHuevos), "");
        Assert.isTrue(this.recipeService.findAllRecipes().contains(Bizcocho), "");
        Assert.isTrue(this.recipeService.getRecipeByName("Bizcocho").getFirst().getId() == Bizcocho.getId(), "");
        System.out.println("Kcal: " + Bizcocho.getKilocalories());
    }
}
