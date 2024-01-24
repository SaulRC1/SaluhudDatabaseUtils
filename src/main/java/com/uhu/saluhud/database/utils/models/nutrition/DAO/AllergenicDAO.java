package com.uhu.saluhud.database.utils.models.nutrition.DAO;

import com.uhu.saluhud.database.utils.models.nutrition.Allergenic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
public class AllergenicDAO
{

    private final SessionFactory sessionFactory;

    public AllergenicDAO(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    public void saveAllergenic(Allergenic allergenic)
    {
        try ( Session session = sessionFactory.openSession())
        {
            Transaction tx = session.beginTransaction();
            session.save(allergenic);
            tx.commit();
        }
    }

    public Allergenic getAllergenicById(long id)
    {
        try ( Session session = sessionFactory.openSession())
        {
            return session.get(Allergenic.class, id);
        }
    }

    public void updateAllergenic(Allergenic allergenic)
    {
        try ( Session session = sessionFactory.openSession())
        {
            Transaction tx = session.beginTransaction();
            session.update(allergenic);
            tx.commit();
        }
    }

    public void deleteAllergenic(Allergenic allergenic)
    {
        try ( Session session = sessionFactory.openSession())
        {
            Transaction tx = session.beginTransaction();
            session.delete(allergenic);
            tx.commit();
        }
    }
}
