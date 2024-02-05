package com.uhu.saluhud.database.utils.models.user;

import com.uhu.saluhud.database.utils.bootstrap.SaluhudAdministratorHibernateBootstrapper;
import com.uhu.saluhud.database.utils.bootstrap.SaluhudHibernateBootstrapper;
import com.uhu.saluhud.database.utils.models.user.DAO.SleepHistoricalDAO;
import com.uhu.saluhud.database.utils.models.user.DAO.SleepHistoricalEntryDAO;
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
public class SleepHistoricalTest
{

    @Test
    public void testSleepHistoricalCRUD()
    {
        SaluhudHibernateBootstrapper adminBootstrapper
                = SaluhudAdministratorHibernateBootstrapper.getSaluhudAdministratorHibernateBootstrapperInstance();

        SessionFactory adminSessionFactory = adminBootstrapper.getSessionFactory();
        try ( Session session = adminSessionFactory.openSession())
        {
            SleepHistoricalDAO sleepHistoricalDAO = new SleepHistoricalDAO(session);
            SleepHistoricalEntryDAO sleepHistoricalEntryDAO = new SleepHistoricalEntryDAO(session);
            
            SleepHistorical sleepHistorical = new SleepHistorical();
            LocalDate now = LocalDate.now();           
            HistoricalEvaluation sleepEvaluation = HistoricalEvaluation.WELL;
            
            List<SleepHistoricalEntry> entries = new ArrayList<>();           
            SleepHistoricalEntry entry = new SleepHistoricalEntry(now, 8, 30, sleepEvaluation, sleepHistorical);
            entries.add(entry);
            
            sleepHistorical.setEntries(entries);
                       
            sleepHistoricalDAO.saveSleepHistorical(sleepHistorical);
            sleepHistoricalEntryDAO.saveSleepHistoricalEntry(entry);
            adminBootstrapper.closeSessionFactory();
        }
    }
}
