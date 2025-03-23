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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * Service class for managing ingredients.
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
     * Finds all ingredients.
     *
     * @return A list of all ingredients.
     */
    public List<Ingredient> findAllIngredients()
    {
        return this.ingredientRepository.findAll();
    }

    /**
     * Find an ingredient by his ID
     * 
     * @param id the id of the ingredient
     * @return an ingredient if exists
     */
    public Ingredient findById(long id)
    {
        return this.ingredientRepository.findOne(id);
    }

    /**
     * Find an ingredient by his name
     *
     * @param name the name of the ingredient
     * @return the ingredient if exists
     */
    public Ingredient findByName(String name)
    {
        return this.ingredientRepository.findByName(name);
    }

    /**
     * Gets an ingredient by its ID.
     *
     * @param id The ID of the ingredient.
     * @return The ingredient if found, or null if not found.
     */
    public Ingredient getIngredientById(long id)
    {
        Ingredient selectedIngredient;
        try
        {
            selectedIngredient = this.ingredientRepository.findById(id).orElse(null);
            return selectedIngredient;
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error getting ingredient by ID", e);
            throw e;
        }
    }

    /**
     * Finds an ingredient by its name.
     *
     * @param name The name of the ingredient.
     * @param pageable
     * @return The ingredient if found, or null if not found.
     */
    public Page<Ingredient> getIngredientByName(String name, Pageable pageable)
    {
        Page<Ingredient> selectedIngredient;
        try
        {
            selectedIngredient = this.ingredientRepository.findByNamePageable(name, pageable);
            return selectedIngredient;
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error getting ingredient by name", e);
            throw e;
        }
    }

    /**
     * Finds ingredients with kilocalories less than or equal to a specific
     * value.
     *
     * @param maxKilocalories The maximum number of kilocalories.
     * @param pageable
     * @return A list of ingredients that meet the criteria.
     */
    public Page<Ingredient> findByMaxKilocalories(int maxKilocalories, Pageable pageable)
    {
        return ingredientRepository.findByMaxKilocalories(maxKilocalories, pageable);
    }

    /**
     * Finds ingredients with a minimum amount of protein.
     *
     * @param minProteinAmount The minimum amount of protein.
     * @param pageable
     * @return A list of ingredients that meet the criteria.
     */
    public Page<Ingredient> findByMinProteinAmount(int minProteinAmount, Pageable pageable)
    {
        return ingredientRepository.findByMinProteinAmount(minProteinAmount, pageable);
    }

    /**
     * Finds ingredients with a minimum amount of carbohydrates.
     *
     * @param minCarbohydratesAmount The minimum amount of carbohydrates.
     * @param pageable
     * @return A list of ingredients that meet the criteria.
     */
    public Page<Ingredient> findByMinCarbohydratesAmount(int minCarbohydratesAmount, Pageable pageable)
    {
        return ingredientRepository.findByMinCarbohydratesAmount(minCarbohydratesAmount, pageable);
    }

    /**
     * Finds ingredients with a minimum amount of fat.
     *
     * @param minFatAmount The minimum amount of fat.
     * @param pageable
     * @return A list of ingredients that meet the criteria.
     */
    public Page<Ingredient> findByMinFatAmount(int minFatAmount, Pageable pageable)
    {
        return ingredientRepository.findByMinFatAmount(minFatAmount, pageable);
    }

    /**
     * Finds ingredients within a specific range of kilocalories.
     *
     * @param minKilocalories The minimum number of kilocalories.
     * @param maxKilocalories The maximum number of kilocalories.
     * @param pageable
     * @return A list of ingredients that meet the criteria.
     */
    public Page<Ingredient> findByKilocaloriesRange(int minKilocalories, int maxKilocalories, Pageable pageable)
    {
        return ingredientRepository.findByKilocaloriesRange(minKilocalories, maxKilocalories, pageable);
    }

    /**
     * Finds ingredients within a specific range of protein.
     *
     * @param minProtein The minimum amount of protein.
     * @param maxProtein The maximum amount of protein.
     * @param pageable
     * @return A list of ingredients that meet the criteria.
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
     * Updates an existing ingredient.
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

                this.ingredientRepository.save(existingIngredient);
            }
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error updating Ingredient", e);
            throw e;
        }
    }

    /**
     * Deletes an ingredient.
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
     * Finds allergens associated with a given ingredient.
     *
     * @param ingredient The ingredient for which to find allergens.
     * @return A set of allergens associated with the ingredient.
     */
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    public Set<Allergenic> getAllergensForIngredient(Ingredient ingredient)
    {
        Set<Allergenic> allergenics;
        try
        {
            allergenics = allergenicRepository.findByIngredientId(ingredient.getId());
            return allergenics;
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error getting allergens for ingredient", e);
            throw e;
        }
    }

    /**
     * Recupera una página de ingredientes almacenados en el repositorio.
     *
     * @param page el número de página a recuperar (comenzando desde 0).
     * @param size la cantidad de elementos por página.
     * @return un objeto {@link Page} que contiene una lista paginada de
     * {@link Ingredient}.
     */
    public Page<Ingredient> getIngredients(int page, int size)
    {
        Pageable pageable = PageRequest.of(page, size);
        return ingredientRepository.findAll(pageable);
    }

    /**
     * Search a paginable ingredient by his name ignoring case
     *
     * @param name the name of the ingredient
     * @param page the minimun number of pages to retrieve
     * @param size maximum number of pages to retrieve
     * @return an object {@link Page} that contains a paginable list of
     * {@link Ingredient}
     */
    public Page<Ingredient> searchByName(String name, int page, int size)
    {
        Pageable pageable = PageRequest.of(page, size);
        return ingredientRepository.findByNameContainingIgnoreCase(name, pageable);
    }
}
