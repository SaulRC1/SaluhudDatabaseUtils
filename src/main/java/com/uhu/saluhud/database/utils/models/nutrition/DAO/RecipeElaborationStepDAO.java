package com.uhu.saluhud.database.utils.models.nutrition.DAO;

import com.uhu.saluhud.database.utils.models.nutrition.RecipeElaborationStep;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
public class RecipeElaborationStepDAO
{

    private final SessionFactory sessionFactory;

    public RecipeElaborationStepDAO(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    public void saveRecipeElaborationStep(RecipeElaborationStep step)
    {
        try ( Session session = sessionFactory.openSession())
        {
            Transaction tx = session.beginTransaction();
            session.save(step);
            tx.commit();
        }
    }

    public RecipeElaborationStep getRecipeElaborationStepById(long id)
    {
        try ( Session session = sessionFactory.openSession())
        {
            return session.get(RecipeElaborationStep.class, id);
        }
    }

    public void updateRecipeElaborationStep(RecipeElaborationStep step)
    {
        try ( Session session = sessionFactory.openSession())
        {
            Transaction tx = session.beginTransaction();
            session.update(step);
            tx.commit();
        }
    }

    public void deleteRecipeElaborationStep(RecipeElaborationStep step)
    {
        try ( Session session = sessionFactory.openSession())
        {
            Transaction tx = session.beginTransaction();
            session.delete(step);
            tx.commit();
        }
    }
}
