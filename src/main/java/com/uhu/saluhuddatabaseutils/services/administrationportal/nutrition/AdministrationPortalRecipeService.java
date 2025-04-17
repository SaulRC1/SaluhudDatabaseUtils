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
 * Service class for managing recipes. This service provides methods to perform
 * CRUD (Create, Read, Update, Delete) operations on recipe, such as saving,
 * updating, deleting, and retrieving recipes. It also supports pagination for
 * fetching large lists of recipes applying certains types of filters.
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

    /**
     * Finds all recipes. This method retrieves all recipes stored in the
     * repository.
     *
     * @return A list of all recipes.
     */
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
        if (recipe.getKilocalories() == 0)
        {
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
        try
        {
            Optional<Recipe> result = this.recipeRepository.findById(recipe.getId());

            if (result.isPresent())
            {
                Recipe existingRecipe = result.get();

                if (recipe.getAllergenics() == null)
                {
                    recipe.setAllergenics(new HashSet<>());
                }
                if (recipe.getElaborationSteps() == null)
                {
                    recipe.setElaborationSteps(new ArrayList<>());
                }
                if (recipe.getRecipeIngredients() == null)
                {
                    recipe.setRecipeIngredients(new ArrayList<>());
                }

                if (!recipe.getName().isBlank())
                {
                    existingRecipe.setName(recipe.getName());
                }
                if (!recipe.getDescription().isBlank())
                {
                    existingRecipe.setDescription(recipe.getDescription());
                }
                if (!recipe.getIngredientsDescription().isBlank())
                {
                    existingRecipe.setIngredientsDescription(recipe.getIngredientsDescription());
                }
                existingRecipe.getAllergenics().clear();
                if (recipe.getAllergenics() != null)
                {
                    existingRecipe.getAllergenics().addAll(recipe.getAllergenics());
                }
                if (!recipe.getElaborationSteps().isEmpty())
                {
                    existingRecipe.setElaborationSteps(recipe.getElaborationSteps());
                }

                existingRecipe.setKilocalories(recipe.getKilocalories() > 0
                        ? recipe.getKilocalories()
                        : calculateRecipeKcal(recipe));

                this.recipeRepository.save(existingRecipe);
            }
        } catch (Exception e)
        {
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
        try
        {
            if (this.recipeRepository.existsById(recipe.getId()))
            {
                this.recipeRepository.delete(recipe);
            }

        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error deleting recipe", e);
            throw e;
        }
    }

    /**
     * Retrieves a recipe by its ID.
     *
     * @param id The ID of the recipe.
     * @return The recipe if found, otherwise null.
     */
    public Recipe getRecipeById(long id)
    {
        Recipe selectedRecipe;
        try
        {
            selectedRecipe = this.recipeRepository.findOne(id);
            if (selectedRecipe == null)
            {
                return null;
            }
            else
            {
                return selectedRecipe;
            }

        } catch (Exception e)
        {
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
        try
        {
            selectedRecipes = this.recipeRepository.findByName(name);
            if (selectedRecipes == null)
            {
                return null;
            }
            else
            {
                return selectedRecipes;
            }

        } catch (Exception e)
        {
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

    /**
     * Calculates the total kilocalories of a recipe based on its ingredients
     * and their quantities.
     *
     * The calculation takes into account the unit of measurement (grams,
     * milliliters, kilograms, liters) and adjusts the kilocalories accordingly.
     * The recipe's total kilocalories are calculated by summing the
     * kilocalories for each ingredient.
     *
     * @param recipe The recipe whose kilocalories are to be calculated. The
     * recipe should contain a list of ingredients with their quantities and
     * units.
     * @return The total kilocalories of the recipe.
     * @throws IllegalArgumentException if an unsupported unit (other than "g",
     * "ml", "kg", "l") is provided.
     */
    public int calculateRecipeKcal(@Valid Recipe recipe)
    {
        int totalKilocalories = 0;
        for (RecipeIngredient recipeIngredient : recipe.getRecipeIngredients())
        {
            int kcalPer100Units = recipeIngredient.getIngredient().getKilocalories(); // kcal per 100 g/ml
            double quantity = recipeIngredient.getQuantity().doubleValue();
            String unit = recipeIngredient.getUnit();

            // Adjusted calculation based on the unit of measurement
            double kcalForIngredient = 0;
            if ("g".equalsIgnoreCase(unit) || "ml".equalsIgnoreCase(unit))
            {
                // For grams or milliliters, divide by 100 and multiply by the quantity
                kcalForIngredient = (kcalPer100Units / 100.0) * quantity;
            }
            else if ("kg".equalsIgnoreCase(unit) || "l".equalsIgnoreCase(unit))
            {
                // For kilograms or liters, multiply by 10 (equivalent to 1000/100)
                kcalForIngredient = kcalPer100Units * (quantity * 10);
            }
            else
            {
                throw new IllegalArgumentException("Unsupported unit: " + unit);
            }

            totalKilocalories += kcalForIngredient;
        }
        return totalKilocalories;
    }

    /**
     * Retrieves a paginated list of recipes.
     *
     * @param page The page number.
     * @param size The number of recipes per page.
     * @return A paginated list of recipes.
     */
    public Page<Recipe> getRecipes(int page, int size)
    {
        Pageable pageable = PageRequest.of(page, size);
        return recipeRepository.findAll(pageable);
    }

    /**
     * Searches for recipes by name, paginated.
     *
     * @param name The name or partial name of the recipe.
     * @param page The page number.
     * @param pageSize The number of recipes per page.
     * @return A paginated list of recipes matching the name.
     */
    public Page<Recipe> searchByName(String name, int page, int pageSize)
    {
        Pageable pageable = PageRequest.of(page, pageSize);
        return recipeRepository.findByNameContainingIgnoreCase(name, pageable);
    }

    /**
     * Searches for recipes with a maximum kilocalories value, paginated.
     *
     * @param maxKilocalories The maximum kilocalories.
     * @param page The page number.
     * @param pageSize The number of recipes per page.
     * @return A paginated list of recipes that have kilocalories below or equal
     * to the specified value.
     */
    public Page<Recipe> searchByMaxKilocalories(int maxKilocalories, int page, int pageSize)
    {
        Pageable pageable = PageRequest.of(page, pageSize);
        return recipeRepository.findByKilocaloriesLessThanEqual(maxKilocalories, pageable);
    }

    /**
     * Searches for recipes that contain a specific ingredient, paginated.
     *
     * @param ingredientId The ID of the ingredient.
     * @param page The page number.
     * @param pageSize The number of recipes per page.
     * @return A paginated list of recipes containing the ingredient.
     */
    public Page<Recipe> searchByIngredient(Long ingredientId, int page, int pageSize)
    {
        Pageable pageable = PageRequest.of(page, pageSize);
        return recipeRepository.findByIngredientId(ingredientId, pageable);
    }

    /**
     * Searches for recipes that exclude a specific allergenic, paginated.
     *
     * @param allergenicId The ID of the allergenic.
     * @param page The page number.
     * @param pageSize The number of recipes per page.
     * @return A paginated list of recipes that do not contain the allergenic.
     */
    public Page<Recipe> searchByAllergenicExclusion(Long allergenicId, int page, int pageSize)
    {
        Pageable pageable = PageRequest.of(page, pageSize);
        return recipeRepository.findByAllergenicExclusion(allergenicId, pageable);
    }

    /**
     * Searches for recipes that contain a specific allergenic, paginated.
     *
     * @param allergenicId The ID of the allergenic.
     * @param page The page number.
     * @param pageSize The number of recipes per page.
     * @return A paginated list of recipes containing the allergenic.
     */
    public Page<Recipe> searchByAllergenic(Long allergenicId, int page, int pageSize)
    {
        Pageable pageable = PageRequest.of(page, pageSize);
        return recipeRepository.findByAllergenic(allergenicId, pageable);
    }
}
