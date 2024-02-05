package com.uhu.saluhud.database.utils.models.user.DAO;

import com.uhu.saluhud.database.utils.models.user.WeightHistoricalEntry;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
public class WeightHistoricalEntryDAO
{
    private final Session session;

    public WeightHistoricalEntryDAO(Session session)
    {
        this.session = session;
    }

    public void saveWeightHistoricalEntry(WeightHistoricalEntry weightHistoricalEntry)
    {
        Transaction tx = session.beginTransaction();
        try
        {
            session.save(weightHistoricalEntry);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
            Logger.getLogger(WeightHistoricalEntryDAO.class.getName()).log(Level.SEVERE, null, e.getCause());
        }
    }

    public void updateWeightHistoricalEntry(WeightHistoricalEntry weightHistoricalEntry)
    {
        Transaction tx = session.beginTransaction();
        try
        {
            session.update(weightHistoricalEntry);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
            Logger.getLogger(SleepHistoricalEntryDAO.class.getName()).log(Level.SEVERE, null, e.getCause());
        }
    }

    public void deleteWeightHistoricalEntry(WeightHistoricalEntry weightHistoricalEntry)
    {
        Transaction tx = session.beginTransaction();
        try
        {
            session.delete(weightHistoricalEntry);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
            Logger.getLogger(WeightHistoricalEntryDAO.class.getName()).log(Level.SEVERE, null, e.getCause());
        }
    }

    public WeightHistoricalEntry getWeightHistoricalEntryById(long id)
    {
        WeightHistoricalEntry selectedWeightHistoricalEntry;
        Transaction tx = session.beginTransaction();
        try
        {
            selectedWeightHistoricalEntry = session.get(WeightHistoricalEntry.class, id);
            tx.commit();
            if (selectedWeightHistoricalEntry == null)
            {
                return null;
            } else
            {
                return selectedWeightHistoricalEntry;
            }
        } catch (Exception e)
        {
            tx.rollback();
            Logger.getLogger(WeightHistoricalEntryDAO.class.getName()).log(Level.SEVERE, null, e.getCause());
            return null;
        }
    }
}
