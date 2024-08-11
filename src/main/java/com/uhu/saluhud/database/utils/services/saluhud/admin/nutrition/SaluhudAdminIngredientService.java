package com.uhu.saluhud.database.utils.services.saluhud.admin.nutrition;

import com.uhu.saluhud.database.utils.models.nutrition.Ingredient;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.uhu.saluhud.database.utils.repositories.saluhud.admin.nutrition.SaluhudAdminIngredientRepository;

/**
 * Service class for managing ingredients.
 *
 * @author Juan Alberto Dominguez Vazquez
 */
@Service
public class SaluhudAdminIngredientService {

    @Autowired
    private SaluhudAdminIngredientRepository ingredientRepository;

    private static final Logger logger = Logger.getLogger(SaluhudAdminIngredientService.class.getName());

    /**
     * Finds all ingredients.
     *
     * @return A list of all ingredients.
     */
    public List<Ingredient> findAllIngredients() {
        return this.ingredientRepository.findAll();
    }

    /**
     * Gets an ingredient by its ID.
     *
     * @param id The ID of the ingredient.
     * @return The ingredient if found, or null if not found.
     */
    public Ingredient getIngredientById(long id) {
        Ingredient selectedIngredient;
        try {
            selectedIngredient = this.ingredientRepository.findById(id).orElse(null);
            return selectedIngredient;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error getting ingredient by ID", e);
            throw e;
        }
    }

    /**
     * Finds an ingredient by its name.
     *
     * @param name The name of the ingredient.
     * @return The ingredient if found, or null if not found.
     */
    public Ingredient getIngredientByName(String name) {
        Ingredient selectedIngredient;
        try {
            selectedIngredient = this.ingredientRepository.findByName(name);
            return selectedIngredient;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error getting ingredient by name", e);
            throw e;
        }
    }

    /**
     * Finds ingredients with kilocalories less than or equal to a specific
     * value.
     *
     * @param maxKilocalories The maximum number of kilocalories.
     * @return A list of ingredients that meet the criteria.
     */
    public List<Ingredient> findByMaxKilocalories(int maxKilocalories) {
        return ingredientRepository.findByMaxKilocalories(maxKilocalories);
    }

    /**
     * Finds ingredients with a minimum amount of protein.
     *
     * @param minProteinAmount The minimum amount of protein.
     * @return A list of ingredients that meet the criteria.
     */
    public List<Ingredient> findByMinProteinAmount(int minProteinAmount) {
        return ingredientRepository.findByMinProteinAmount(minProteinAmount);
    }

    /**
     * Finds ingredients with a minimum amount of carbohydrates.
     *
     * @param minCarbohydratesAmount The minimum amount of carbohydrates.
     * @return A list of ingredients that meet the criteria.
     */
    public List<Ingredient> findByMinCarbohydratesAmount(int minCarbohydratesAmount) {
        return ingredientRepository.findByMinCarbohydratesAmount(minCarbohydratesAmount);
    }

    /**
     * Finds ingredients with a minimum amount of fat.
     *
     * @param minFatAmount The minimum amount of fat.
     * @return A list of ingredients that meet the criteria.
     */
    public List<Ingredient> findByMinFatAmount(int minFatAmount) {
        return ingredientRepository.findByMinFatAmount(minFatAmount);
    }

    /**
     * Finds ingredients within a specific range of kilocalories.
     *
     * @param minKilocalories The minimum number of kilocalories.
     * @param maxKilocalories The maximum number of kilocalories.
     * @return A list of ingredients that meet the criteria.
     */
    public List<Ingredient> findByKilocaloriesRange(int minKilocalories, int maxKilocalories) {
        return ingredientRepository.findByKilocaloriesRange(minKilocalories, maxKilocalories);
    }

    /**
     * Finds ingredients within a specific range of protein.
     *
     * @param minProtein The minimum amount of protein.
     * @param maxProtein The maximum amount of protein.
     * @return A list of ingredients that meet the criteria.
     */
    public List<Ingredient> findByProteinRange(int minProtein, int maxProtein) {
        return ingredientRepository.findByProteinRange(minProtein, maxProtein);
    }

    /**
     * Saves a new ingredient.
     *
     * @param ingredient The ingredient to save.
     */
    @Transactional
    public void saveIngredient(Ingredient ingredient) {
        this.ingredientRepository.save(ingredient);
    }

    /**
     * Updates an existing ingredient.
     *
     * @param ingredient The ingredient to update.
     */
    @Transactional
    public void updateIngredient(Ingredient ingredient) {
        try {
            Optional<Ingredient> result = this.ingredientRepository.findById(ingredient.getId());

            if (result.isPresent()) {
                Ingredient existingIngredient = result.get();
                existingIngredient.setName(ingredient.getName());
                existingIngredient.setKilocalories(ingredient.getKilocalories());
                existingIngredient.setProtein_amount(ingredient.getProteinAmount());
                existingIngredient.setCarbohydrates_amount(ingredient.getCarbohydratesAmount());
                existingIngredient.setFat_amount(ingredient.getFatAmount());

                this.ingredientRepository.save(existingIngredient);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating Ingredient", e);
            throw e;
        }
    }

    /**
     * Deletes an ingredient.
     *
     * @param ingredient The ingredient to delete.
     */
    @Transactional
    public void deleteIngredient(Ingredient ingredient) {
        try {
            this.ingredientRepository.delete(ingredient);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting ingredient", e);
            throw e;
        }
    }
}
