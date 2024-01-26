package com.uhu.saluhud.database.utils.models.nutrition.DAO;

import com.uhu.saluhud.database.utils.models.nutrition.RecipeElaborationStep;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
public class RecipeElaborationStepDAO
{

    private final Session session;

    public RecipeElaborationStepDAO(Session session)
    {
        this.session = session;
    }

    public void saveRecipeElaborationStep(RecipeElaborationStep step)
    {
        Transaction tx = session.beginTransaction();
        try
        {
            session.save(step);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
        }
    }

    public RecipeElaborationStep getRecipeElaborationStepById(long id)
    {
        RecipeElaborationStep selectedRecipeElaborationStep;
        Transaction tx = session.beginTransaction();
        try
        {
            selectedRecipeElaborationStep = session.get(RecipeElaborationStep.class, id);
            tx.commit();
            if (selectedRecipeElaborationStep == null)
            {
                return null;
            } else
            {
                return selectedRecipeElaborationStep;
            }
        } catch (Exception e)
        {
            tx.rollback();
            return null;
        }
    }

    public void updateRecipeElaborationStep(RecipeElaborationStep step)
    {
        Transaction tx = session.beginTransaction();
        try
        {
            session.update(step);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
        }
    }

    public void deleteRecipeElaborationStep(RecipeElaborationStep step)
    {
        Transaction tx = session.beginTransaction();
        try
        {
            session.delete(step);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
        }
    }
}
