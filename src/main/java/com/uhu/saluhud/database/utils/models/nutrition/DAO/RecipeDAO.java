package com.uhu.saluhud.database.utils.models.nutrition.DAO;

import com.uhu.saluhud.database.utils.models.nutrition.Recipe;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
public class RecipeDAO
{

    private final Session session;

    public RecipeDAO(Session session)
    {
        this.session = session;
    }

    public void saveRecipe(Recipe recipe)
    {
        Transaction tx = session.beginTransaction();
        try
        {
            session.save(recipe);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
        }
    }

    public void updateRecipe(Recipe recipe)
    {
        Transaction tx = session.beginTransaction();
        try
        {
            session.update(recipe);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
        }
    }

    public void deleteRecipe(Recipe recipe)
    {
        Transaction tx = session.beginTransaction();
        try
        {
            session.delete(recipe);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
        }
    }

    public Recipe getRecipeById(long id)
    {
        Recipe selectedRecipe;
        Transaction tx = session.beginTransaction();
        try
        {
            selectedRecipe = session.get(Recipe.class, id);
            tx.commit();
            if (selectedRecipe == null)
            {
                return null;
            } else
            {
                return selectedRecipe;
            }
        } catch (Exception e)
        {
            tx.rollback();
            return null;
        }
    }
}
