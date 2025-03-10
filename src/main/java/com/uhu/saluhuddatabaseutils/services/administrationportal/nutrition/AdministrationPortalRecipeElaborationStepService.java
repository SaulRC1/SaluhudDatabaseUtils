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
 * Service class for managing recipe elaboration steps.
 *
 * @author Juan Alberto Dominguez Vazquez
 */
@Service
@Transactional(readOnly = true, transactionManager = "saluhudAdministrationPortalTransactionManager")
public class AdministrationPortalRecipeElaborationStepService {

    @Autowired
    private AdministrationPortalRecipeElaborationStepRepository recipeElaborationStepRepository;

    private static final Logger logger = Logger.getLogger(AdministrationPortalRecipeElaborationStepService.class.getName());

    /**
     * Finds all recipe elaboration steps.
     *
     * @return A list of all recipe elaboration steps.
     */
    public List<RecipeElaborationStep> findAllSteps() {
        return this.recipeElaborationStepRepository.findAll();
    }

    /**
     * Saves a new recipe elaboration step.
     *
     * @param step The recipe elaboration step to save.
     */
    @Transactional(readOnly = false, transactionManager = "saluhudAdministrationPortalTransactionManager")
    public void saveRecipeElaborationStep(@Valid RecipeElaborationStep step) {
        this.recipeElaborationStepRepository.save(step);
    }

    /**
     * Updates an existing recipe elaboration step.
     *
     * @param step The recipe elaboration step to update.
     */
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    public void updateRecipeElaborationStep(@Valid RecipeElaborationStep step) {
        try {
            Optional<RecipeElaborationStep> result = this.recipeElaborationStepRepository.findById(step.getId());

            if (result.isPresent()) {
                RecipeElaborationStep existingStep = result.get();
                if (step.getStepNumber() > 0) {
                    existingStep.setStepNumber(step.getStepNumber());
                }

                if (!step.getStepDescription().isBlank()) {
                    existingStep.setStepDescription(step.getStepDescription());
                }

                this.recipeElaborationStepRepository.save(existingStep);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating RecipeElaborationStep", e);
            throw e;
        }
    }

    /**
     * Deletes a recipe elaboration step.
     *
     * @param step The recipe elaboration step to delete.
     */
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    public void deleteRecipeElaborationStep(@Valid RecipeElaborationStep step) {
        try {
            if (this.recipeElaborationStepRepository.existsById(step.getId())) {
                this.recipeElaborationStepRepository.delete(step);
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting recipe elaboration step", e);
            throw e;
        }
    }

    /**
     * Gets a recipe elaboration step by its ID.
     *
     * @param id The ID of the recipe elaboration step.
     * @return The recipe elaboration step if found, or null if not found.
     */
    public RecipeElaborationStep getStepById(long id) {
        RecipeElaborationStep selectedStep;
        try {
            selectedStep = this.recipeElaborationStepRepository.findById(id).orElse(null);
            return selectedStep;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error getting recipe elaboration step by ID", e);
            throw e;
        }
    }

    /**
     * Gets a recipe elaboration step by its step number.
     *
     * @param number The step number of the recipe elaboration step.
     * @return The recipe elaboration step if found, or null if not found.
     */
    @Transactional(readOnly = true)
    public RecipeElaborationStep getStepByNumber(int number) {
        RecipeElaborationStep selectedStep;
        try {
            selectedStep = this.recipeElaborationStepRepository.findByStepNumber(number);
            return selectedStep;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error getting recipe elaboration step by step number", e);
            throw e;
        }
    }
    
    /**
     * Recupera una página de pasos de recetas almacenados en el repositorio.
     *
     * @param page el número de página a recuperar (comenzando desde 0).
     * @param size la cantidad de elementos por página.
     * @return un objeto {@link Page} que contiene una lista paginada de
     * {@link RecipeElaborationStep}.
     */
    public Page<RecipeElaborationStep> getElaborationSteps(int page, int size)
    {
        Pageable pageable = PageRequest.of(page, size);
        return recipeElaborationStepRepository.findAll(pageable);
    }
}
