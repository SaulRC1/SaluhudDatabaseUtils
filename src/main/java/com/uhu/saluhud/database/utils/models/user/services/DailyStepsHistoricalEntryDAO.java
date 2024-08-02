package com.uhu.saluhud.database.utils.models.user.services;

import com.uhu.saluhud.database.utils.models.user.DailyStepsHistoricalEntry;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
public class DailyStepsHistoricalEntryDAO
{

    private final Session session;

    public DailyStepsHistoricalEntryDAO(Session session)
    {
        this.session = session;
    }

    public void saveDailyStepsHistoricalEntry(DailyStepsHistoricalEntry dailyStepsHistoricalEntry)
    {
        Transaction tx = session.beginTransaction();
        try
        {
            session.save(dailyStepsHistoricalEntry);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
            Logger.getLogger(DailyStepsHistoricalEntryDAO.class.getName()).log(Level.SEVERE, null, e.getCause());
        }
    }

    public void updateDailyStepsHistoricalEntry(DailyStepsHistoricalEntry dailyStepsHistoricalEntry)
    {
        Transaction tx = session.beginTransaction();
        try
        {
            session.update(dailyStepsHistoricalEntry);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
            Logger.getLogger(DailyStepsHistoricalEntryDAO.class.getName()).log(Level.SEVERE, null, e.getCause());
        }
    }

    public void deleteDailyStepsHistoricalEntry(DailyStepsHistoricalEntry dailyStepsHistoricalEntry)
    {
        Transaction tx = session.beginTransaction();
        try
        {
            session.delete(dailyStepsHistoricalEntry);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
            Logger.getLogger(DailyStepsHistoricalEntryDAO.class.getName()).log(Level.SEVERE, null, e.getCause());
        }
    }

    public DailyStepsHistoricalEntry getDailyStepsHistoricalEntryById(long id)
    {
        DailyStepsHistoricalEntry selectedDailyStepsHistoricalEntry;
        Transaction tx = session.beginTransaction();
        try
        {
            selectedDailyStepsHistoricalEntry = session.get(DailyStepsHistoricalEntry.class, id);
            tx.commit();
            if (selectedDailyStepsHistoricalEntry == null)
            {
                return null;
            } else
            {
                return selectedDailyStepsHistoricalEntry;
            }
        } catch (Exception e)
        {
            tx.rollback();
            Logger.getLogger(DailyStepsHistoricalEntryDAO.class.getName()).log(Level.SEVERE, null, e.getCause());
            return null;
        }
    }
}
