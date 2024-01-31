package com.uhu.saluhud.database.utils.models.user.DAO;

import com.uhu.saluhud.database.utils.models.user.SaluhudUser;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
public class SaluhudUserDAO
{

    private final Session session;

    public SaluhudUserDAO(Session session)
    {
        this.session = session;
    }

    public void saveUser(SaluhudUser user)
    {
        Transaction tx = session.beginTransaction();
        try
        {
            session.save(user);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
            Logger.getLogger(SaluhudUserDAO.class.getName()).log(Level.SEVERE, null, e.getCause());
        }
    }

    public void updateUser(SaluhudUser user)
    {
        Transaction tx = session.beginTransaction();
        try
        {
            session.update(user);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
            Logger.getLogger(SaluhudUserDAO.class.getName()).log(Level.SEVERE, null, e.getCause());
        }
    }

    public void deleteUser(SaluhudUser user)
    {
        Transaction tx = session.beginTransaction();
        try
        {
            session.delete(user);
            tx.commit();
        } catch (Exception e)
        {
            tx.rollback();
            Logger.getLogger(SaluhudUserDAO.class.getName()).log(Level.SEVERE, null, e.getCause());
        }
    }

    public SaluhudUser getUserById(long id)
    {
        SaluhudUser selectedUser;
        Transaction tx = session.beginTransaction();
        try
        {
            selectedUser = session.get(SaluhudUser.class, id);
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
            Logger.getLogger(SaluhudUserDAO.class.getName()).log(Level.SEVERE, null, e.getCause());
            return null;
        }
    }
}
