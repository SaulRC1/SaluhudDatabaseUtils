package com.uhu.saluhud.database.utils.models.user;

import com.uhu.saluhud.database.utils.bootstrap.SaluhudAdministratorHibernateBootstrapper;
import com.uhu.saluhud.database.utils.bootstrap.SaluhudHibernateBootstrapper;
import com.uhu.saluhud.database.utils.models.user.DAO.WeightHistoricalDAO;
import com.uhu.saluhud.database.utils.models.user.DAO.WeightHistoricalEntryDAO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
public class WeightHistoricalTest
{

    @Test
    public void testWeightHistoricalCRUD()
    {
        SaluhudHibernateBootstrapper adminBootstrapper
                = SaluhudAdministratorHibernateBootstrapper.getSaluhudAdministratorHibernateBootstrapperInstance();

        SessionFactory adminSessionFactory = adminBootstrapper.getSessionFactory();
        try ( Session session = adminSessionFactory.openSession())
        {
            WeightHistoricalDAO weightHistoricalDAO = new WeightHistoricalDAO(session);
            WeightHistoricalEntryDAO weightHistoricalEntryDAO = new WeightHistoricalEntryDAO(session);
            
            WeightHistorical weightHistorical = new WeightHistorical();
            LocalDate now = LocalDate.now(); 
            
            List<WeightHistoricalEntry> entries = new ArrayList<>();
            WeightHistoricalEntry entry = new WeightHistoricalEntry(now, 75, 181, 2200, weightHistorical);
            entries.add(entry);
            
            weightHistorical.setEntries(entries);
            
            weightHistoricalDAO.saveWeightHistorical(weightHistorical);
            weightHistoricalEntryDAO.saveWeightHistoricalEntry(entry);
            adminBootstrapper.closeSessionFactory();
        }
    }
}
