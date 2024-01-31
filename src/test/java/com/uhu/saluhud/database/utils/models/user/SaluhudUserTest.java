package com.uhu.saluhud.database.utils.models.user;

import com.uhu.saluhud.database.utils.bootstrap.SaluhudAdministratorHibernateBootstrapper;
import com.uhu.saluhud.database.utils.bootstrap.SaluhudHibernateBootstrapper;
import com.uhu.saluhud.database.utils.models.user.DAO.SaluhudUserDAO;
import com.uhu.saluhud.database.utils.models.user.DAO.SaluhudUserFitnessDataDAO;
import com.uhu.saluhud.database.utils.models.user.DAO.SaluhudUserPersonalDataDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
public class SaluhudUserTest
{

    @Test
    public void testUserCRUD()
    {
        SaluhudHibernateBootstrapper adminBootstrapper
                = SaluhudAdministratorHibernateBootstrapper.getSaluhudAdministratorHibernateBootstrapperInstance();

        SessionFactory adminSessionFactory = adminBootstrapper.getSessionFactory();
        try ( Session session = adminSessionFactory.openSession())
        {
            SaluhudUserDAO saluhudUserDAO = new SaluhudUserDAO(session);
            SaluhudUserFitnessDataDAO saluhudUserFitnessDataDAO = new SaluhudUserFitnessDataDAO(session);
            SaluhudUserPersonalDataDAO saluhudPersonalDataDAO = new SaluhudUserPersonalDataDAO(session);
            
            SaluhudUserPersonalData userPersonalData = new SaluhudUserPersonalData("Saul", "Rodríguez", 123456789);
            SaluhudUserFitnessData userFitnessData = new SaluhudUserFitnessData(90, 170, "Men", 22, "Hectomorfo", 2, 8, 10000, 2100, "10%");
            SaluhudUser user = new SaluhudUser("SaulRC1", "1235", "saul@gmail.com", userPersonalData, userFitnessData);
            SaluhudUser user2 = new SaluhudUser("Juan2k", "1235", "juan@gmail.com");
                                       
            saluhudPersonalDataDAO.saveUser(userPersonalData);
            saluhudUserFitnessDataDAO.saveUser(userFitnessData);
            saluhudUserDAO.saveUser(user);
            saluhudUserDAO.saveUser(user2);
            saluhudUserDAO.deleteUser(saluhudUserDAO.getUserById(2));
            
            adminBootstrapper.closeSessionFactory();
        }
    }
}
