package com.uhu.saluhud.database.utils.models.user.services;

import com.uhu.saluhud.database.utils.models.user.SaluhudUserFitnessData;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
public class SaluhudUserFitnessDataDAO
{
    private final Session session;

    public SaluhudUserFitnessDataDAO(Session session)
    {
        this.session = session;
    }

    public void saveUser(SaluhudUserFitnessData user)
    {
        Transaction tx = session.beginTransaction();
        try
        {
            session.save(user);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
            Logger.getLogger(SaluhudUserFitnessDataDAO.class.getName()).log(Level.SEVERE, null, e.getCause());
        }
    }

    public void updateUser(SaluhudUserFitnessData user)
    {
        Transaction tx = session.beginTransaction();
        try
        {
            session.update(user);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
            Logger.getLogger(SaluhudUserFitnessDataDAO.class.getName()).log(Level.SEVERE, null, e.getCause());
        }
    }

    public void deleteUser(SaluhudUserFitnessData user)
    {
        Transaction tx = session.beginTransaction();
        try
        {
            session.delete(user);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
            Logger.getLogger(SaluhudUserFitnessDataDAO.class.getName()).log(Level.SEVERE, null, e.getCause());
        }
    }

    public SaluhudUserFitnessData getSaluhudUserFitnessDataById(long id)
    {
        SaluhudUserFitnessData selectedUser;
        Transaction tx = session.beginTransaction();
        try
        {
            selectedUser = session.get(SaluhudUserFitnessData.class, id);
            tx.commit();
            if (selectedUser == null)
            {
                return null;
            } else
            {
                return selectedUser;
            }
        } catch (Exception e)
        {
            tx.rollback();
            Logger.getLogger(SaluhudUserFitnessDataDAO.class.getName()).log(Level.SEVERE, null, e.getCause());
            return null;
        }
    }
}
