package com.uhu.saluhud.database.utils.bootstrap;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
public class SaluhudHibernateBootstrapperTest 
{
    @AfterAll
    public static void cleanUp()
    {
        SaluhudHibernateBootstrapper adminBootstrapper =
                SaluhudAdministratorHibernateBootstrapper.getSaluhudAdministratorHibernateBootstrapperInstance();
        
        adminBootstrapper.closeSessionFactory();
        
        /*SaluhudHibernateBootstrapper userBootstrapper =
                SaluhudUserHibernateBootstrapper*/
    }
    
    @Test
    public void test1()
    {
        SaluhudHibernateBootstrapper adminBootstrapper = 
                SaluhudAdministratorHibernateBootstrapper.getSaluhudAdministratorHibernateBootstrapperInstance();
        
        SessionFactory adminSessionFactory = adminBootstrapper.getSessionFactory();
        
        try (Session session = adminSessionFactory.openSession())
        {   
            session.beginTransaction();
            
            session.createNativeQuery("SELECT CURRENT_DATE").getResultList().get(0);
        }
        catch(Exception e)
        {
            fail();
        }
    }
    
    @Test
    public void test2()
    {
        System.out.println("test2");
    }
}
