package com.uhu.saluhud.database.utils.models.nutrition.DAO;

import com.uhu.saluhud.database.utils.models.nutrition.Ingredient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
public class IngredientDAO
{

    private final SessionFactory sessionFactory;

    public IngredientDAO(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    public void saveIngredient(Ingredient ingredient)
    {
        try ( Session session = sessionFactory.openSession())
        {
            Transaction tx = session.beginTransaction();
            session.save(ingredient);
            tx.commit();
        }
    }

    public Ingredient getIngredientById(long id)
    {
        try ( Session session = sessionFactory.openSession())
        {
            return session.get(Ingredient.class, id);
        }
    }

    public void updateIngredient(Ingredient ingredient)
    {
        try ( Session session = sessionFactory.openSession())
        {
            Transaction tx = session.beginTransaction();
            session.update(ingredient);
            tx.commit();
        }
    }

    public void deleteIngredient(Ingredient ingredient)
    {
        try ( Session session = sessionFactory.openSession())
        {
            Transaction tx = session.beginTransaction();
            session.delete(ingredient);
            tx.commit();
        }
    }
}
