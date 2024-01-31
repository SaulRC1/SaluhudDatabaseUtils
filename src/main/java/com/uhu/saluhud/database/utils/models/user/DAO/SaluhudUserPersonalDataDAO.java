package com.uhu.saluhud.database.utils.models.user.DAO;

import com.uhu.saluhud.database.utils.models.user.SaluhudUserPersonalData;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
public class SaluhudUserPersonalDataDAO
{

    private final Session session;

    public SaluhudUserPersonalDataDAO(Session session)
    {
        this.session = session;
    }

    public void saveUser(SaluhudUserPersonalData user)
    {
        Transaction tx = session.beginTransaction();
        try
        {
            session.save(user);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
            Logger.getLogger(SaluhudUserPersonalDataDAO.class.getName()).log(Level.SEVERE, null, e.getCause());
        }
    }

    public void updateUser(SaluhudUserPersonalData user)
    {
        Transaction tx = session.beginTransaction();
        try
        {
            session.update(user);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
            Logger.getLogger(SaluhudUserPersonalDataDAO.class.getName()).log(Level.SEVERE, null, e.getCause());
        }
    }

    public void deleteUser(SaluhudUserPersonalData user)
    {
        Transaction tx = session.beginTransaction();
        try
        {
            session.delete(user);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
            Logger.getLogger(SaluhudUserPersonalDataDAO.class.getName()).log(Level.SEVERE, null, e.getCause());
        }
    }

    public SaluhudUserPersonalData getSaluhudUserFitnessDataById(long id)
    {
        SaluhudUserPersonalData selectedUser;
        Transaction tx = session.beginTransaction();
        try
        {
            selectedUser = session.get(SaluhudUserPersonalData.class, id);
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
            Logger.getLogger(SaluhudUserPersonalDataDAO.class.getName()).log(Level.SEVERE, null, e.getCause());
            return null;
        }
    }
}
