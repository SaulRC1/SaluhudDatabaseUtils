package com.uhu.saluhud.database.utils.models.user.services;

import com.uhu.saluhud.database.utils.models.user.DailyStepsHistorical;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
public class DailyStepsHistoricalDAO
{

    private final Session session;

    public DailyStepsHistoricalDAO(Session session)
    {
        this.session = session;
    }

    public void saveDailyStepsHistorical(DailyStepsHistorical dailyStepsHistorical)
    {
        Transaction tx = session.beginTransaction();
        try
        {
            session.save(dailyStepsHistorical);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
            Logger.getLogger(DailyStepsHistoricalDAO.class.getName()).log(Level.SEVERE, null, e.getCause());
        }
    }

    public void updateDailyStepsHistorical(DailyStepsHistorical dailyStepsHistorical)
    {
        Transaction tx = session.beginTransaction();
        try
        {
            session.update(dailyStepsHistorical);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
            Logger.getLogger(DailyStepsHistoricalDAO.class.getName()).log(Level.SEVERE, null, e.getCause());
        }
    }

    public void deleteDailyStepsHistorical(DailyStepsHistorical dailyStepsHistorical)
    {
        Transaction tx = session.beginTransaction();
        try
        {
            session.delete(dailyStepsHistorical);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
            Logger.getLogger(DailyStepsHistoricalDAO.class.getName()).log(Level.SEVERE, null, e.getCause());
        }
    }

    public DailyStepsHistorical getDailyStepsHistoricalById(long id)
    {
        DailyStepsHistorical selectedDailyStepsHistorical;
        Transaction tx = session.beginTransaction();
        try
        {
            selectedDailyStepsHistorical = session.get(DailyStepsHistorical.class, id);
            tx.commit();
            if (selectedDailyStepsHistorical == null)
            {
                return null;
            } else
            {
                return selectedDailyStepsHistorical;
            }
        } catch (Exception e)
        {
            tx.rollback();
            Logger.getLogger(DailyStepsHistoricalDAO.class.getName()).log(Level.SEVERE, null, e.getCause());
            return null;
        }
    }
}
