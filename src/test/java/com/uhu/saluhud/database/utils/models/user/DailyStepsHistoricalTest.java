package com.uhu.saluhud.database.utils.models.user;

import com.uhu.saluhud.database.utils.bootstrap.SaluhudAdministratorHibernateBootstrapper;
import com.uhu.saluhud.database.utils.bootstrap.SaluhudHibernateBootstrapper;
import com.uhu.saluhud.database.utils.models.user.DAO.DailyStepsHistoricalDAO;
import com.uhu.saluhud.database.utils.models.user.DAO.DailyStepsHistoricalEntryDAO;
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
public class DailyStepsHistoricalTest
{

    @Test
    public void testDailyStepsHistoricalCRUD()
    {
        SaluhudHibernateBootstrapper adminBootstrapper
                = SaluhudAdministratorHibernateBootstrapper.getSaluhudAdministratorHibernateBootstrapperInstance();

        SessionFactory adminSessionFactory = adminBootstrapper.getSessionFactory();
        try ( Session session = adminSessionFactory.openSession())
        {
            DailyStepsHistoricalDAO dailyStepsHistoricalDAO = new DailyStepsHistoricalDAO(session);
            DailyStepsHistoricalEntryDAO dailyStepsHistoricalEntryDAO = new DailyStepsHistoricalEntryDAO(session);
            LocalDate now = LocalDate.now();
            
            HistoricalEvaluation stepsEvaluation = HistoricalEvaluation.EXCELLENT;
            DailyStepsHistorical dailyStepsHistorical = new DailyStepsHistorical();
            
            List<DailyStepsHistoricalEntry> historicalEntries = new ArrayList<>();
            DailyStepsHistoricalEntry entry = new DailyStepsHistoricalEntry(now, 6000, 200, stepsEvaluation, dailyStepsHistorical);
            historicalEntries.add(entry);   
            
            dailyStepsHistorical.setEntries(historicalEntries);
                                          
            dailyStepsHistoricalDAO.saveDailyStepsHistorical(dailyStepsHistorical);
            dailyStepsHistoricalEntryDAO.saveDailyStepsHistoricalEntry(entry);
            
            adminBootstrapper.closeSessionFactory();
        }
    }
}
