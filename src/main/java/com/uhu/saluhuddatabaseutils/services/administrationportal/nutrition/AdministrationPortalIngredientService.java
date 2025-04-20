package com.uhu.saluhuddatabaseutils.services.administrationportal.nutrition;

import com.uhu.saluhuddatabaseutils.models.nutrition.Allergenic;
import com.uhu.saluhuddatabaseutils.models.nutrition.Ingredient;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.Valid;
import java.util.Set;
import com.uhu.saluhuddatabaseutils.repositories.administrationportal.nutrition.AdministrationPortalAllergenicRepository;
import com.uhu.saluhuddatabaseutils.repositories.administrationportal.nutrition.AdministrationPortalIngredientRepository;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * Service class for managing ingredients in the administration portal. Provides
 * methods for retrieving, searching, saving, updating, and deleting
 * ingredients. Also supports filtering ingredients by nutritional values and
 * retrieving allergens associated with an ingredient.
 *
 * @author Juan Alberto Dominguez Vazquez
 */
@Service
@Transactional(readOnly = true, transactionManager = "saluhudAdministrationPortalTransactionManager")
public class AdministrationPortalIngredientService
{

    @Autowired
    private AdministrationPortalIngredientRepository ingredientRepository;

    @Autowired
    private AdministrationPortalAllergenicRepository allergenicRepository;

    private static final Logger logger = Logger.getLogger(AdministrationPortalIngredientService.class.getName());

    /**
     * Retrieves all ingredients.
     *
     * @return A list of all available ingredients.
     */
    public List<Ingredient> findAllIngredients()
    {
        return this.ingredientRepository.findAll();
    }

    /**
     * Finds an ingredient by its ID.
     *
     * @param id The ID of the ingredient.
     * @return The ingredient if found, otherwise null.
     */
    public Ingredient findById(long id)
    {
        return this.ingredientRepository.findOne(id);
    }

    /**
     * Finds an ingredient by its name.
     *
     * @param name The name of the ingredient.
     * @return The ingredient if found, otherwise null.
     */
    public Ingredient findByName(String name)
    {
        return this.ingredientRepository.findByName(name);
    }

    /**
     * Retrieves an ingredient by its ID, handling potential errors.
     *
     * @param id The ID of the ingredient.
     * @return The ingredient if found, otherwise null.
     */
    public Ingredient getIngredientById(long id)
    {
        try
        {
            return this.ingredientRepository.findById(id).orElse(null);
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error retrieving ingredient by ID", e);
            throw e;
        }
    }

    /**
     * Finds an ingredient by its name with pagination support.
     *
     * @param name The name of the ingredient.
     * @param pageable Pagination parameters.
     * @return A paginated list of matching ingredients.
     */
    public Page<Ingredient> getIngredientByName(String name, Pageable pageable)
    {
        try
        {
            return this.ingredientRepository.findByNamePageable(name, pageable);
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error retrieving ingredient by name", e);
            throw e;
        }
    }

    /**
     * Finds ingredients with kilocalories less than or equal to a specified
     * value.
     *
     * @param maxKilocalories The maximum kilocalories allowed for the
     * ingredients.
     * @param pageable Pagination parameters to control the result set (page
     * number, size, etc.).
     * @return A paginated list of ingredients that have kilocalories less than
     * or equal to the specified value.
     */
    public Page<Ingredient> findByMaxKilocalories(int maxKilocalories, Pageable pageable)
    {
        return ingredientRepository.findByMaxKilocalories(maxKilocalories, pageable);
    }

    /**
     * Finds ingredients with a minimum amount of protein greater than or equal
     * to a specified value.
     *
     * @param minProteinAmount The minimum amount of protein (in grams) that the
     * ingredients should contain.
     * @param pageable Pagination parameters to control the result set (page
     * number, size, etc.).
     * @return A paginated list of ingredients that have protein content greater
     * than or equal to the specified value.
     */
    public Page<Ingredient> findByMinProteinAmount(int minProteinAmount, Pageable pageable)
    {
        return ingredientRepository.findByMinProteinAmount(minProteinAmount, pageable);
    }

    /**
     * Finds ingredients with a minimum amount of carbohydrates greater than or
     * equal to a specified value.
     *
     * @param minCarbohydratesAmount The minimum amount of carbohydrates (in
     * grams) that the ingredients should contain.
     * @param pageable Pagination parameters to control the result set (page
     * number, size, etc.).
     * @return A paginated list of ingredients that have carbohydrates content
     * greater than or equal to the specified value.
     */
    public Page<Ingredient> findByMinCarbohydratesAmount(int minCarbohydratesAmount, Pageable pageable)
    {
        return ingredientRepository.findByMinCarbohydratesAmount(minCarbohydratesAmount, pageable);
    }

    /**
     * Finds ingredients with a minimum amount of fat greater than or equal to a
     * specified value.
     *
     * @param minFatAmount The minimum amount of fat (in grams) that the
     * ingredients should contain.
     * @param pageable Pagination parameters to control the result set (page
     * number, size, etc.).
     * @return A paginated list of ingredients that have fat content greater
     * than or equal to the specified value.
     */
    public Page<Ingredient> findByMinFatAmount(int minFatAmount, Pageable pageable)
    {
        return ingredientRepository.findByMinFatAmount(minFatAmount, pageable);
    }

    /**
     * Finds ingredients within a specific range of kilocalories.
     *
     * @param minKilocalories The minimum number of kilocalories that the
     * ingredients should contain.
     * @param maxKilocalories The maximum number of kilocalories that the
     * ingredients should contain.
     * @param pageable Pagination parameters to control the result set (page
     * number, size, etc.).
     * @return A paginated list of ingredients that have kilocalories within the
     * specified range.
     */
    public Page<Ingredient> findByKilocaloriesRange(int minKilocalories, int maxKilocalories, Pageable pageable)
    {
        return ingredientRepository.findByKilocaloriesRange(minKilocalories, maxKilocalories, pageable);
    }

    /**
     * Finds ingredients within a specific range of protein content.
     *
     * @param minProtein The minimum amount of protein (in grams) that the
     * ingredients should contain.
     * @param maxProtein The maximum amount of protein (in grams) that the
     * ingredients should contain.
     * @param pageable Pagination parameters to control the result set (page
     * number, size, etc.).
     * @return A paginated list of ingredients that have protein content within
     * the specified range.
     */
    public Page<Ingredient> findByProteinRange(int minProtein, int maxProtein, Pageable pageable)
    {
        return ingredientRepository.findByProteinRange(minProtein, maxProtein, pageable);
    }

    /**
     * Saves a new ingredient.
     *
     * @param ingredient The ingredient to save.
     */
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    public void saveIngredient(@Valid Ingredient ingredient)
    {
        this.ingredientRepository.save(ingredient);
    }

    /**
     * Updates an existing ingredient with new values if present.
     *
     * @param ingredient The ingredient to update.
     */
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    public void updateIngredient(@Valid Ingredient ingredient)
    {
        try
        {
            Optional<Ingredient> result = this.ingredientRepository.findById(ingredient.getId());

            if (result.isPresent())
            {
                Ingredient existingIngredient = result.get();
                if (!ingredient.getName().isBlank())
                {
                    existingIngredient.setName(ingredient.getName());
                }
                if (ingredient.getKilocalories() > 0)
                {
                    existingIngredient.setKilocalories(ingredient.getKilocalories());
                }
                if (ingredient.getProteinAmount() > 0)
                {
                    existingIngredient.setProteinAmount(ingredient.getProteinAmount());
                }
                if (ingredient.getCarbohydratesAmount() > 0)
                {
                    existingIngredient.setCarbohydratesAmount(ingredient.getCarbohydratesAmount());
                }
                if (ingredient.getFatAmount() > 0)
                {
                    existingIngredient.setFatAmount(ingredient.getFatAmount());
                }
                if (ingredient.getAllergens() != null)
                {
                    Set<Allergenic> managedAllergens = ingredient.getAllergens().stream()
                            .map(a -> allergenicRepository.findById(a.getId()))
                            .filter(Optional::isPresent)
                            .map(Optional::get)
                            .collect(Collectors.toSet());

                    existingIngredient.getAllergens().clear();
                    existingIngredient.getAllergens().addAll(managedAllergens);
                }

                this.ingredientRepository.save(existingIngredient);
            }
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error updating Ingredient", e);
            throw e;
        }
    }

    /**
     * Deletes an ingredient if it exists in the repository.
     *
     * @param ingredient The ingredient to delete.
     */
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    public void deleteIngredient(@Valid Ingredient ingredient)
    {
        try
        {
            if (this.ingredientRepository.existsById(ingredient.getId()))
            {
                this.ingredientRepository.delete(ingredient);
            }

        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error deleting ingredient", e);
            throw e;
        }
    }

    /**
     * Retrieves allergens associated with a given ingredient.
     *
     * @param ingredient The ingredient to check for allergens.
     * @return A set of allergens linked to the ingredient.
     */
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    public Set<Allergenic> getAllergensForIngredient(Ingredient ingredient)
    {
        try
        {
            return allergenicRepository.findByIngredientId(ingredient.getId());
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error retrieving allergens for ingredient", e);
            throw e;
        }
    }

    /**
     * Retrieves a paginated list of ingredients.
     *
     * @param page The page number to retrieve (starting from 0).
     * @param size The number of items per page.
     * @return A {@link Page} containing a list of {@link Ingredient}.
     */
    public Page<Ingredient> getIngredients(int page, int size)
    {
        Pageable pageable = PageRequest.of(page, size);
        return ingredientRepository.findAll(pageable);
    }

    /**
     * Searches for ingredients by name with case-insensitive matching and
     * pagination.
     *
     * @param name The name of the ingredient to search for.
     * @param page The page number to retrieve (starting from 0).
     * @param size The number of items per page.
     * @return A {@link Page} containing a list of matching {@link Ingredient}.
     */
    public Page<Ingredient> searchByName(String name, int page, int size)
    {
        Pageable pageable = PageRequest.of(page, size);
        return ingredientRepository.findByNameContainingIgnoreCase(name, pageable);
    }

    /**
     * Searches for ingredients by starting name with case-insensitive matching
     * and pagination.
     *
     * @param name The name of the ingredient to search for.
     * @param page The page number to retrieve (starting from 0).
     * @param size The number of items per page.
     * @return A {@link Page} containing a list of matching {@link Ingredient}.
     */
    public Page<Ingredient> searchByStartName(String name, int page, int size)
    {
        Pageable pageable = PageRequest.of(page, size);
        return ingredientRepository.findByNameStartingWithIgnoreCase(name, pageable);
    }

}
