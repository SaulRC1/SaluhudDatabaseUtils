package com.uhu.saluhud.database.utils.models.nutrition.DAO;

import com.uhu.saluhud.database.utils.models.nutrition.Allergenic;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
public class AllergenicDAO
{

    private final Session session;

    public AllergenicDAO(Session session)
    {
        this.session = session;
    }

    public void saveAllergenic(Allergenic allergenic)
    {
        Transaction tx = session.beginTransaction();
        try
        {
            session.save(allergenic);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
        }
    }

    public Allergenic getAllergenicById(long id)
    {
        Allergenic selectedAllergenic;
        Transaction tx = session.beginTransaction();
        try
        {
            selectedAllergenic = session.get(Allergenic.class, id);
            tx.commit();
            return selectedAllergenic;
        } catch (Exception e)
        {
            tx.rollback();
            return null;
        }
    }

    public void updateAllergenic(Allergenic allergenic)
    {
        Transaction tx = session.beginTransaction();
        try
        {
            session.update(allergenic);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
        }
    }

    public void deleteAllergenic(Allergenic allergenic)
    {
        Transaction tx = session.beginTransaction();
        try
        {
            session.delete(allergenic);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
        }
    }
}
