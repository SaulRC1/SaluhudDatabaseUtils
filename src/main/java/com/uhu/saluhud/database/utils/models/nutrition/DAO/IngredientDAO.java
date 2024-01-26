package com.uhu.saluhud.database.utils.models.nutrition.DAO;

import com.uhu.saluhud.database.utils.models.nutrition.Ingredient;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
public class IngredientDAO
{

    private final Session session;

    public IngredientDAO(Session session)
    {
        this.session = session;
    }

    public void saveIngredient(Ingredient ingredient)
    {
        Transaction tx = session.beginTransaction();
        try
        {
            session.save(ingredient);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
        }
    }

    public Ingredient getIngredientById(long id)
    {
        Ingredient selectedIngredient;
        Transaction tx = session.beginTransaction();
        try
        {
            selectedIngredient = session.get(Ingredient.class, id);
            tx.commit();
            return selectedIngredient;
        } catch (Exception e)
        {
            tx.rollback();
            return null;
        }
    }

    public Ingredient getIngredientByName(String name)
    {
        Ingredient selectedIngredient;
        Transaction tx = session.beginTransaction();
        try
        {
            Query query = this.session.createNativeQuery("SELECT * FROM INGREDIENT I WHERE I.name = :name", Ingredient.class);
            query.setParameter("name", name);
            selectedIngredient = (Ingredient) query.getSingleResult();
            tx.commit();
            if (selectedIngredient == null)
            {
                return null;
            } else
            {              
                return selectedIngredient;
            }

        } catch (Exception e)
        {
            tx.rollback();
            return null;
        }
    }

    public void updateIngredient(Ingredient ingredient)
    {
        Transaction tx = session.beginTransaction();
        try
        {
            session.update(ingredient);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
        }
    }

    public void deleteIngredient(Ingredient ingredient)
    {
        Transaction tx = session.beginTransaction();
        try
        {
            session.delete(ingredient);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
        }
    }
}
