package com.uhu.saluhuddatabaseutils.services.administrationportal.nutrition;

import com.uhu.saluhuddatabaseutils.models.nutrition.RecipeElaborationStep;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.validation.Valid;
import org.springframework.transaction.annotation.Transactional;
import com.uhu.saluhuddatabaseutils.repositories.administrationportal.nutrition.AdministrationPortalRecipeElaborationStepRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * Service class for managing recipe elaboration steps. This service provides
 * methods to perform CRUD (Create, Read, Update, Delete) operations on recipe
 * elaboration steps, such as saving, updating, deleting, and retrieving steps.
 * It also supports pagination for fetching large lists of recipe elaboration
 * steps.
 *
 * @author Juan Alberto Dominguez Vazquez
 */
@Service
@Transactional(readOnly = true, transactionManager = "saluhudAdministrationPortalTransactionManager")
public class AdministrationPortalRecipeElaborationStepService
{

    @Autowired
    private AdministrationPortalRecipeElaborationStepRepository recipeElaborationStepRepository;

    private static final Logger logger = Logger.getLogger(AdministrationPortalRecipeElaborationStepService.class.getName());

    /**
     * Finds all recipe elaboration steps. This method retrieves all recipe
     * elaboration steps stored in the repository.
     *
     * @return A list of all recipe elaboration steps.
     */
    public List<RecipeElaborationStep> findAllSteps()
    {
        return this.recipeElaborationStepRepository.findAll();
    }

    /**
     * Saves a new recipe elaboration step. This method saves a new recipe
     * elaboration step to the repository.
     *
     * @param step The recipe elaboration step to save.
     */
    @Transactional(readOnly = false, transactionManager = "saluhudAdministrationPortalTransactionManager")
    public void saveRecipeElaborationStep(@Valid RecipeElaborationStep step)
    {
        this.recipeElaborationStepRepository.save(step);
    }

    /**
     * Updates an existing recipe elaboration step. This method updates an
     * existing recipe elaboration step in the repository. If the step exists,
     * its number and description will be updated.
     *
     * @param step The recipe elaboration step to update.
     */
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    public void updateRecipeElaborationStep(@Valid RecipeElaborationStep step)
    {
        try
        {
            Optional<RecipeElaborationStep> result = this.recipeElaborationStepRepository.findById(step.getId());

            if (result.isPresent())
            {
                RecipeElaborationStep existingStep = result.get();
                if (step.getStepNumber() > 0)
                {
                    existingStep.setStepNumber(step.getStepNumber());
                }

                if (!step.getStepDescription().isBlank())
                {
                    existingStep.setStepDescription(step.getStepDescription());
                }

                this.recipeElaborationStepRepository.save(existingStep);
            }
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error updating RecipeElaborationStep", e);
            throw e;
        }
    }

    /**
     * Deletes a recipe elaboration step. This method deletes an existing recipe
     * elaboration step from the repository.
     *
     * @param step The recipe elaboration step to delete.
     */
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    public void deleteRecipeElaborationStep(@Valid RecipeElaborationStep step)
    {
        try
        {
            if (this.recipeElaborationStepRepository.existsById(step.getId()))
            {
                this.recipeElaborationStepRepository.delete(step);
            }

        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error deleting recipe elaboration step", e);
            throw e;
        }
    }

    /**
     * Gets a recipe elaboration step by its ID. This method retrieves a
     * specific recipe elaboration step from the repository using its ID. If the
     * step is not found, it returns null.
     *
     * @param id The ID of the recipe elaboration step.
     * @return The recipe elaboration step if found, or null if not found.
     */
    public RecipeElaborationStep getStepById(long id)
    {
        RecipeElaborationStep selectedStep;
        try
        {
            selectedStep = this.recipeElaborationStepRepository.findById(id).orElse(null);
            return selectedStep;
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error getting recipe elaboration step by ID", e);
            throw e;
        }
    }

    /**
     * Gets a recipe elaboration step by its step number. This method retrieves
     * a specific recipe elaboration step based on its step number. If the step
     * is not found, it returns null.
     *
     * @param number The step number of the recipe elaboration step.
     * @return The recipe elaboration step if found, or null if not found.
     */
    @Transactional(readOnly = true)
    public RecipeElaborationStep getStepByNumber(int number)
    {
        RecipeElaborationStep selectedStep;
        try
        {
            selectedStep = this.recipeElaborationStepRepository.findByStepNumber(number);
            return selectedStep;
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error getting recipe elaboration step by step number", e);
            throw e;
        }
    }

    /**
     * Retrieves a paginated list of recipe elaboration steps. This method
     * retrieves a paginated list of recipe elaboration steps from the
     * repository.
     *
     * @param page The page number to retrieve (starting from 0).
     * @param size The number of elements per page.
     * @return A {@link Page} object containing a paginated list of
     * {@link RecipeElaborationStep}.
     */
    public Page<RecipeElaborationStep> getElaborationSteps(int page, int size)
    {
        Pageable pageable = PageRequest.of(page, size);
        return recipeElaborationStepRepository.findAll(pageable);
    }
}
