package com.uhu.saluhud.database.utils.models.nutrition.services;

import com.uhu.saluhud.database.utils.models.nutrition.RecipeElaborationStep;
import com.uhu.saluhud.database.utils.models.repositories.nutrition.RecipeElaborationStepRepository;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
@Service
public class RecipeElaborationStepService {

    @Autowired
    private RecipeElaborationStepRepository recipeElaborationStepRepository;
    
    private final Session session;

    public RecipeElaborationStepService(Session session) {
        this.session = session;
    }
    
    public List<RecipeElaborationStep> findAllSteps() {
        return this.recipeElaborationStepRepository.findAll();
    }

    public void saveRecipeElaborationStep(RecipeElaborationStep step) {
        Transaction tx = session.beginTransaction();
        try {
            this.recipeElaborationStepRepository.save(step);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
    }

    public void updateRecipeElaborationStep(RecipeElaborationStep step) {
        Transaction tx = session.beginTransaction();
        try {
            this.recipeElaborationStepRepository.save(step);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
    }

    public void deleteRecipeElaborationStep(RecipeElaborationStep step) {
        Transaction tx = session.beginTransaction();
        try {
            this.recipeElaborationStepRepository.delete(step);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
    }
    
    public RecipeElaborationStep getStepById(long id) {
        RecipeElaborationStep selectedStep;
        Transaction tx = session.beginTransaction();
        try {
            selectedStep = this.recipeElaborationStepRepository.findOne(id);
            tx.commit();
            if (selectedStep == null) {
                return null;
            } else {
                return selectedStep;
            }

        } catch (Exception e) {
            tx.rollback();
            return null;
        }
    }

    public RecipeElaborationStep getStepByNumber(int number) {
        RecipeElaborationStep selectedStep;
        Transaction tx = session.beginTransaction();
        try {
            selectedStep = this.recipeElaborationStepRepository.findByStepNumber(number);
            tx.commit();
            if (selectedStep == null) {
                return null;
            } else {
                return selectedStep;
            }

        } catch (Exception e) {
            tx.rollback();
            return null;
        }
    }
}
