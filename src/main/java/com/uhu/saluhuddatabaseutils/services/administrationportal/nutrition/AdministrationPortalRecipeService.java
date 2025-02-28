package com.uhu.saluhuddatabaseutils.services.administrationportal.nutrition;

import com.uhu.saluhuddatabaseutils.models.nutrition.Recipe;
import com.uhu.saluhuddatabaseutils.models.nutrition.RecipeIngredient;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.uhu.saluhuddatabaseutils.repositories.administrationportal.nutrition.AdministrationPortalRecipeRepository;

/**
 * Service class for managing recipes.
 *
 * @author Juan Alberto Dominguez Vazquez
 */
@Service
@Transactional(readOnly = true, transactionManager = "saluhudAdministrationPortalTransactionManager")
public class AdministrationPortalRecipeService
{

    @Autowired
    private AdministrationPortalRecipeRepository recipeRepository;

    private static final Logger logger = Logger.getLogger(AdministrationPortalRecipeService.class.getName());

    public List<Recipe> findAllRecipes()
    {
        return this.recipeRepository.findAll();
    }

    /**
     * Saves a new recipe.
     *
     * @param recipe The recipe to save.
     * @return The saved recipe.
     */
    @Transactional(readOnly = false, transactionManager = "saluhudAdministrationPortalTransactionManager")
    public Recipe saveRecipe(@Valid Recipe recipe)
    {
        if (recipe.getKilocalories() == 0) {
            int totalKilocalories = calculateRecipeKcal(recipe);
            recipe.setKilocalories(totalKilocalories);
        }
        return recipeRepository.save(recipe);
    }

    /**
     * Updates an existing recipe.
     *
     * @param recipe The recipe to update.
     */
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    public void updateRecipe(@Valid Recipe recipe)
    {
        System.out.println("Intentando actualizar la receta con ID: " + recipe.getId());
        try {
            Optional<Recipe> result = this.recipeRepository.findById(recipe.getId());

            if (result.isPresent()) {
                Recipe existingRecipe = result.get();

                if (recipe.getAllergenics() == null) {
                    recipe.setAllergenics(new HashSet<>());
                }
                if (recipe.getElaborationSteps() == null) {
                    recipe.setElaborationSteps(new ArrayList<>());
                }
                if (recipe.getRecipeIngredients() == null) {
                    recipe.setRecipeIngredients(new ArrayList<>());
                }

                if (!recipe.getName().isBlank()) {
                    existingRecipe.setName(recipe.getName());
                }
                if (!recipe.getDescription().isBlank()) {
                    existingRecipe.setDescription(recipe.getDescription());
                }
                if (!recipe.getIngredientsDescription().isBlank()) {
                    existingRecipe.setIngredientsDescription(recipe.getIngredientsDescription());
                }
                if (!recipe.getAllergenics().isEmpty()) {
                    existingRecipe.getAllergenics().clear();
                    existingRecipe.setAllergenics(recipe.getAllergenics());
                }
                if (!recipe.getElaborationSteps().isEmpty()) {
                    existingRecipe.setElaborationSteps(recipe.getElaborationSteps());
                }
                if (recipe.getKilocalories() == 0) {
                    int totalKilocalories = calculateRecipeKcal(recipe);
                    existingRecipe.setKilocalories(totalKilocalories);
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
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    public void deleteRecipe(@Valid Recipe recipe)
    {
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
    public Recipe getRecipeById(long id)
    {
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
    public List<Recipe> getRecipeByName(String name)
    {
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
     * Finds recipes containing a specific allergenic.
     *
     * @param allergenId The ID of the allergenic.
     * @return List of recipes containing the allergenic.
     */
    public List<Recipe> findByAllergenic(Long allergenId)
    {
        return recipeRepository.findByAllergenic(allergenId);
    }

    /**
     * Finds recipes containing a keyword in the description.
     *
     * @param keyword The keyword to search for.
     * @return List of recipes containing the keyword in the description.
     */
    public List<Recipe> findByDescriptionContaining(String keyword)
    {
        return recipeRepository.findByDescriptionContaining(keyword);
    }

    public int calculateRecipeKcal(@Valid Recipe recipe)
    {
        int totalKilocalories = 0;
        for (RecipeIngredient recipeIngredient : recipe.getRecipeIngredients()) {
            int kcalPer100Units = recipeIngredient.getIngredient().getKilocalories(); // kcal por 100 g/ml
            double quantity = recipeIngredient.getQuantity().doubleValue();
            String unit = recipeIngredient.getUnit();

            // Cálculo ajustado de kilocalorías según la unidad
            double kcalForIngredient = 0;
            if ("g".equalsIgnoreCase(unit) || "ml".equalsIgnoreCase(unit)) {
                // Si está en gramos o mililitros, dividimos por 100 y multiplicamos por la cantidad
                kcalForIngredient = (kcalPer100Units / 100.0) * quantity;
            } else if ("kg".equalsIgnoreCase(unit) || "l".equalsIgnoreCase(unit)) {
                // Si está en kilogramos o litros, multiplicamos por 10 (que equivale a 1000/100)
                kcalForIngredient = kcalPer100Units * (quantity * 10);
            } else {
                throw new IllegalArgumentException("Unidad no soportada: " + unit);
            }

            totalKilocalories += kcalForIngredient;
        }
        return totalKilocalories;
    }

    public Page<Recipe> getRecipes(int page, int size)
    {
        Pageable pageable = PageRequest.of(page, size);
        return recipeRepository.findAll(pageable);
    }
}
