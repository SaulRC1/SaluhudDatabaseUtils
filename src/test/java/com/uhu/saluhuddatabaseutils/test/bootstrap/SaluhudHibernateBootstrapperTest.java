package com.uhu.saluhuddatabaseutils.test.bootstrap;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
/*
public class SaluhudHibernateBootstrapperTest
{
    @Test
    public void testBootstrappingWithAdminCredentials()
    {
        SaluhudHibernateBootstrapper adminBootstrapper
                = SaluhudAdministratorHibernateBootstrapper.getSaluhudAdministratorHibernateBootstrapperInstance();

        SessionFactory adminSessionFactory = adminBootstrapper.getSessionFactory();

        try ( Session session = adminSessionFactory.openSession())
        {
            session.beginTransaction();

            session.createNativeQuery("SELECT CURRENT_DATE").getResultList().get(0);
        } catch (Exception e)
        {
            fail();
        }
    }

    @Test
    public void testBootstrappingWithUserCredentials()
    {
        SaluhudHibernateBootstrapper userBootstrapper
                = SaluhudUserHibernateBootstrapper.getSaluhudUserHibernateBootstrapperInstance();

        SessionFactory adminSessionFactory = userBootstrapper.getSessionFactory();

        try ( Session session = adminSessionFactory.openSession())
        {
            session.beginTransaction();

            session.createNativeQuery("SELECT CURRENT_DATE").getResultList().get(0);
        } catch (Exception e)
        {
            fail();
        }
    }
}
*/