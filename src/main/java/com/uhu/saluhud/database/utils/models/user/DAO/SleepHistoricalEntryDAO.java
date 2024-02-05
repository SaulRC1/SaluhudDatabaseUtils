package com.uhu.saluhud.database.utils.models.user.DAO;

import com.uhu.saluhud.database.utils.models.user.SleepHistoricalEntry;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
public class SleepHistoricalEntryDAO
{

    private final Session session;

    public SleepHistoricalEntryDAO(Session session)
    {
        this.session = session;
    }

    public void saveSleepHistoricalEntry(SleepHistoricalEntry sleepHistoricalEntry)
    {
        Transaction tx = session.beginTransaction();
        try
        {
            session.save(sleepHistoricalEntry);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
            Logger.getLogger(SleepHistoricalEntryDAO.class.getName()).log(Level.SEVERE, null, e.getCause());
        }
    }

    public void updateSleepHistoricalEntry(SleepHistoricalEntry sleepHistoricalEntry)
    {
        Transaction tx = session.beginTransaction();
        try
        {
            session.update(sleepHistoricalEntry);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
            Logger.getLogger(SleepHistoricalEntryDAO.class.getName()).log(Level.SEVERE, null, e.getCause());
        }
    }

    public void deleteSleepHistoricalEntry(SleepHistoricalEntry sleepHistoricalEntry)
    {
        Transaction tx = session.beginTransaction();
        try
        {
            session.delete(sleepHistoricalEntry);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
            Logger.getLogger(SleepHistoricalEntryDAO.class.getName()).log(Level.SEVERE, null, e.getCause());
        }
    }

    public SleepHistoricalEntry getSleepHistoricalEntryById(long id)
    {
        SleepHistoricalEntry selectedSleepHistoricalEntry;
        Transaction tx = session.beginTransaction();
        try
        {
            selectedSleepHistoricalEntry = session.get(SleepHistoricalEntry.class, id);
            tx.commit();
            if (selectedSleepHistoricalEntry == null)
            {
                return null;
            } else
            {
                return selectedSleepHistoricalEntry;
            }
        } catch (Exception e)
        {
            tx.rollback();
            Logger.getLogger(SleepHistoricalEntryDAO.class.getName()).log(Level.SEVERE, null, e.getCause());
            return null;
        }
    }
}
