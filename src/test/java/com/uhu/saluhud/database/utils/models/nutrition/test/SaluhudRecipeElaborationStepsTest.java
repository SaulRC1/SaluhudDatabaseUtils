package com.uhu.saluhud.database.utils.models.nutrition.test;

import com.uhu.saluhud.database.utils.models.nutrition.Allergenic;
import com.uhu.saluhud.database.utils.models.nutrition.services.RecipeService;
import com.uhu.saluhud.database.utils.models.nutrition.services.RecipeElaborationStepService;
import com.uhu.saluhud.database.utils.models.nutrition.Ingredient;
import com.uhu.saluhud.database.utils.models.nutrition.Recipe;
import com.uhu.saluhud.database.utils.models.nutrition.RecipeElaborationStep;
import com.uhu.saluhud.database.utils.models.nutrition.services.AllergenicService;
import com.uhu.saluhud.database.utils.models.nutrition.services.IngredientService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
public class SaluhudRecipeElaborationStepsTest {

    @Test
    public void testRecipeElaborationStepsTestCRUD() {

        RecipeElaborationStepService recipeElaborationStepService = new RecipeElaborationStepService();
        RecipeService recipeService = new RecipeService();
        IngredientService ingredientService = new IngredientService();
        AllergenicService allergenicService = new AllergenicService();

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
    }
}
