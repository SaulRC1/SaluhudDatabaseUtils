package com.uhu.saluhud.database.utils.models.nutrition.test;

import com.uhu.saluhud.database.utils.bootstrap.SaluhudAdministratorHibernateBootstrapper;
import com.uhu.saluhud.database.utils.bootstrap.SaluhudHibernateBootstrapper;
import com.uhu.saluhud.database.utils.models.nutrition.Allergenic;
import com.uhu.saluhud.database.utils.models.nutrition.services.AllergenicService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
public class SaluhudAllergenicsTest
{

    @Test
    public void testAllergenicCRUD()
    {
        SaluhudHibernateBootstrapper adminBootstrapper
                = SaluhudAdministratorHibernateBootstrapper.getSaluhudAdministratorHibernateBootstrapperInstance();

        SessionFactory adminSessionFactory = adminBootstrapper.getSessionFactory();
        try ( Session session = adminSessionFactory.openSession())
        {
            AllergenicService allergenicService = new AllergenicService();
            Allergenic pescado = new Allergenic("Pescado");
            Allergenic leche = new Allergenic("Leche");
            
            allergenicService.saveAllergenic(pescado);
            allergenicService.saveAllergenic(leche);
            allergenicService.deleteAllergenic(leche);
            adminBootstrapper.closeSessionFactory();
        }
    }
}
