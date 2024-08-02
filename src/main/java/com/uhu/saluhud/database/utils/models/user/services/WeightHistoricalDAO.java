package com.uhu.saluhud.database.utils.models.user.services;

import com.uhu.saluhud.database.utils.models.user.WeightHistorical;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
public class WeightHistoricalDAO
{
    private final Session session;

    public WeightHistoricalDAO(Session session)
    {
        this.session = session;
    }

    public void saveWeightHistorical(WeightHistorical weightHistorical)
    {
        Transaction tx = session.beginTransaction();
        try
        {
            session.save(weightHistorical);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
            Logger.getLogger(WeightHistoricalDAO.class.getName()).log(Level.SEVERE, null, e.getCause());
        }
    }

    public void updateWeightHistorical(WeightHistorical weightHistorical)
    {
        Transaction tx = session.beginTransaction();
        try
        {
            session.update(weightHistorical);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
            Logger.getLogger(WeightHistoricalDAO.class.getName()).log(Level.SEVERE, null, e.getCause());
        }
    }

    public void deleteWeightHistorical(WeightHistorical weightHistorical)
    {
        Transaction tx = session.beginTransaction();
        try
        {
            session.delete(weightHistorical);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
            Logger.getLogger(WeightHistoricalDAO.class.getName()).log(Level.SEVERE, null, e.getCause());
        }
    }

    public WeightHistorical getWeightHistoricalById(long id)
    {
        WeightHistorical selectedWeightHistorical;
        Transaction tx = session.beginTransaction();
        try
        {
            selectedWeightHistorical = session.get(WeightHistorical.class, id);
            tx.commit();
            if (selectedWeightHistorical == null)
            {
                return null;
            } else
            {
                return selectedWeightHistorical;
            }
        } catch (Exception e)
        {
            tx.rollback();
            Logger.getLogger(WeightHistoricalDAO.class.getName()).log(Level.SEVERE, null, e.getCause());
            return null;
        }
    }
}
