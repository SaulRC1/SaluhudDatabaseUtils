package com.uhu.saluhud.database.utils.models.nutrition.DAO;

import com.uhu.saluhud.database.utils.models.nutrition.Recipe;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
public class RecipeDAO
{

    private final SessionFactory sessionFactory;

    public RecipeDAO(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    public void saveRecipe(Recipe recipe)
    {
        try ( Session session = sessionFactory.openSession())
        {
            Transaction tx = session.beginTransaction();
            session.save(recipe);
            tx.commit();
        }
    }

    public void updateRecipe(Recipe recipe)
    {
        try ( Session session = sessionFactory.openSession())
        {
            Transaction tx = session.beginTransaction();
            session.update(recipe);
            tx.commit();
        }
    }

    public void deleteRecipe(Recipe recipe)
    {
        try ( Session session = sessionFactory.openSession())
        {
            Transaction tx = session.beginTransaction();
            session.delete(recipe);
            tx.commit();
        }
    }
    
    public Recipe getRecipeById(long id)
    {
        try ( Session session = sessionFactory.openSession())
        {
            return session.get(Recipe.class, id);
        }
    }
}
