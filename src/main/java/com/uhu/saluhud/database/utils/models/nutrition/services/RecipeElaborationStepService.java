package com.uhu.saluhud.database.utils.models.nutrition.services;

import com.uhu.saluhud.database.utils.models.nutrition.RecipeElaborationStep;
import com.uhu.saluhud.database.utils.models.repositories.nutrition.RecipeElaborationStepRepository;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing recipe elaboration steps.
 *
 * @author Juan Alberto Dominguez Vazquez
 */
@Service
public class RecipeElaborationStepService {

    @Autowired
    private RecipeElaborationStepRepository recipeElaborationStepRepository;

    private static final Logger logger = Logger.getLogger(RecipeElaborationStepService.class.getName());

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
    public void saveRecipeElaborationStep(RecipeElaborationStep step) {
        this.recipeElaborationStepRepository.save(step);
    }

    /**
     * Updates an existing recipe elaboration step.
     *
     * @param step The recipe elaboration step to update.
     */
    public void updateRecipeElaborationStep(RecipeElaborationStep step) {
        try {
            Optional<RecipeElaborationStep> result = this.recipeElaborationStepRepository.findById(step.getId());

            if (result.isPresent()) {
                RecipeElaborationStep existingStep = result.get();
                existingStep.setStepNumber(step.getStepNumber());
                existingStep.setStepDescription(step.getStepDescription());

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
    public void deleteRecipeElaborationStep(RecipeElaborationStep step) {
        try {
            this.recipeElaborationStepRepository.delete(step);
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
}
