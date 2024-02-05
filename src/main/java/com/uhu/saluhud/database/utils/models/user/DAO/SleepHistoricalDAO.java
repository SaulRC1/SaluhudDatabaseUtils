package com.uhu.saluhud.database.utils.models.user.DAO;

import com.uhu.saluhud.database.utils.models.user.SleepHistorical;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
public class SleepHistoricalDAO
{
    private final Session session;

    public SleepHistoricalDAO(Session session)
    {
        this.session = session;
    }

    public void saveSleepHistorical(SleepHistorical sleepHistorical)
    {
        Transaction tx = session.beginTransaction();
        try
        {
            session.save(sleepHistorical);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
            Logger.getLogger(SleepHistoricalDAO.class.getName()).log(Level.SEVERE, null, e.getCause());
        }
    }

    public void updateSleepHistorical(SleepHistorical sleepHistorical)
    {
        Transaction tx = session.beginTransaction();
        try
        {
            session.update(sleepHistorical);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
            Logger.getLogger(SleepHistoricalDAO.class.getName()).log(Level.SEVERE, null, e.getCause());
        }
    }

    public void deleteSleepHistorical(SleepHistorical sleepHistorical)
    {
        Transaction tx = session.beginTransaction();
        try
        {
            session.delete(sleepHistorical);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
            Logger.getLogger(SleepHistoricalDAO.class.getName()).log(Level.SEVERE, null, e.getCause());
        }
    }

    public SleepHistorical getSleepHistoricalById(long id)
    {
        SleepHistorical selectedSleepHistorical;
        Transaction tx = session.beginTransaction();
        try
        {
            selectedSleepHistorical = session.get(SleepHistorical.class, id);
            tx.commit();
            if (selectedSleepHistorical == null)
            {
                return null;
            } else
            {
                return selectedSleepHistorical;
            }
        } catch (Exception e)
        {
            tx.rollback();
            Logger.getLogger(SleepHistoricalDAO.class.getName()).log(Level.SEVERE, null, e.getCause());
            return null;
        }
    }
}
