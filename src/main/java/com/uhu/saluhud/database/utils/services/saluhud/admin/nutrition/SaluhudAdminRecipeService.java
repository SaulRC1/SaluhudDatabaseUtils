package com.uhu.saluhud.database.utils.services.saluhud.admin.nutrition;

import com.uhu.saluhud.database.utils.models.nutrition.Ingredient;
import com.uhu.saluhud.database.utils.models.nutrition.Recipe;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.uhu.saluhud.database.utils.repositories.saluhud.admin.nutrition.SaluhudAdminRecipeRepository;
import jakarta.validation.Valid;

/**
 * Service class for managing recipes.
 *
 * @author Juan Alberto Dominguez Vazquez
 */
@Service
@Transactional(readOnly = true, transactionManager = "saluhudAdminTransactionManager")
public class SaluhudAdminRecipeService {

    @Autowired
    private SaluhudAdminRecipeRepository recipeRepository;

    private static final Logger logger = Logger.getLogger(SaluhudAdminRecipeService.class.getName());

    public List<Recipe> findAllRecipes() {
        return this.recipeRepository.findAll();
    }

    /**
     * Saves a new recipe.
     *
     * @param recipe The recipe to save.
     * @return The saved recipe.
     */
    @Transactional(transactionManager = "saluhudAdminTransactionManager")
    public Recipe saveRecipe(@Valid Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    /**
     * Updates an existing recipe.
     *
     * @param recipe The recipe to update.
     */
    @Transactional(transactionManager = "saluhudAdminTransactionManager")
    public void updateRecipe(@Valid Recipe recipe) {
        try {
            Optional<Recipe> result = this.recipeRepository.findById(recipe.getId());

            if (result.isPresent()) {
                Recipe existingRecipe = result.get();
                if (!recipe.getName().isBlank()) {
                    existingRecipe.setName(recipe.getName());
                }
                if (!recipe.getDescription().isBlank()) {
                    existingRecipe.setDescription(recipe.getDescription());
                }
                if (!recipe.getIngredientsDescription().isBlank()) {
                    existingRecipe.setIngredientsDescription(recipe.getIngredientsDescription());
                }
                if (!recipe.getIngredients().isEmpty()) {
                    existingRecipe.setIngredients(recipe.getIngredients());
                }
                if (!recipe.getAllergenics().isEmpty()) {
                    existingRecipe.setAllergenics(recipe.getAllergenics());
                }
                if (!recipe.getElaborationSteps().isEmpty()) {
                    existingRecipe.setElaborationSteps(recipe.getElaborationSteps());
                }

                this.recipeRepository.save(existingRecipe);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating Recipe", e);
            throw e;
        }
    }

    /**
     * Delete a recipe.
     *
     * @param recipe The recipe receta to delete.
     */
    @Transactional(transactionManager = "saluhudAdminTransactionManager")
    public void deleteRecipe(@Valid Recipe recipe) {
        try {
            if (this.recipeRepository.existsById(recipe.getId())) {
                this.recipeRepository.delete(recipe);
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting recipe", e);
            throw e;
        }
    }

    /**
     *
     * @param id
     * @return
     */
    public Recipe getRecipeById(long id) {
        Recipe selectedRecipe;
        try {
            selectedRecipe = this.recipeRepository.findOne(id);
            if (selectedRecipe == null) {
                return null;
            } else {
                return selectedRecipe;
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error getting recipe by ID", e);
            throw e;
        }
    }

    /**
     * Finds a recipe by its name.
     *
     * @param name The name of the recipes.
     * @return The recipes found, if any.
     */
    public List<Recipe> getRecipeByName(String name) {
        List<Recipe> selectedRecipes;
        try {
            selectedRecipes = this.recipeRepository.findByName(name);
            if (selectedRecipes == null) {
                return null;
            } else {
                return selectedRecipes;
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error getting recipe by name", e);
            throw e;
        }
    }

    /**
     * Finds recipes containing a specific ingredient.
     *
     * @param ingredient The specific ingredient.
     * @return List of recipes containing the ingredient.
     */
    public List<Recipe> findByIngredient(@Valid Ingredient ingredient) {
        return recipeRepository.findByIngredient(ingredient);
    }

    /**
     * Finds recipes containing a specific allergenic.
     *
     * @param allergenId The ID of the allergenic.
     * @return List of recipes containing the allergenic.
     */
    public List<Recipe> findByAllergenic(Long allergenId) {
        return recipeRepository.findByAllergenic(allergenId);
    }

    /**
     * Finds recipes containing a keyword in the ingredients description.
     *
     * @param keyword The keyword to search for.
     * @return List of recipes containing the keyword in the ingredients
     * description.
     */
    public List<Recipe> findByIngredientsDescriptionContaining(String keyword) {
        return recipeRepository.findByIngredientsDescriptionContaining(keyword);
    }

    /**
     * Finds recipes containing a keyword in the description.
     *
     * @param keyword The keyword to search for.
     * @return List of recipes containing the keyword in the description.
     */
    public List<Recipe> findByDescriptionContaining(String keyword) {
        return recipeRepository.findByDescriptionContaining(keyword);
    }

    /**
     * Finds recipes whose ingredients have a maximum number of kilocalories.
     *
     * @param maxKilocalories The maximum number of kilocalories.
     * @return List of recipes that meet the criteria.
     */
    public List<Recipe> findByIngredientMaxKilocalories(int maxKilocalories) {
        return recipeRepository.findByIngredientMaxKilocalories(maxKilocalories);
    }

    /**
     * Finds recipes whose ingredients have a minimum amount of protein.
     *
     * @param minProteinAmount The minimum amount of protein.
     * @return List of recipes that meet the criteria.
     */
    public List<Recipe> findByIngredientMinProteinAmount(int minProteinAmount) {
        return recipeRepository.findByIngredientMinProteinAmount(minProteinAmount);
    }
}
