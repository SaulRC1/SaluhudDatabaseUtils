package com.uhu.saluhud.database.utils.models.user;

import com.uhu.saluhud.database.utils.bootstrap.SaluhudAdministratorHibernateBootstrapper;
import com.uhu.saluhud.database.utils.bootstrap.SaluhudHibernateBootstrapper;
import com.uhu.saluhud.database.utils.models.user.services.DailyStepsHistoricalService;
import com.uhu.saluhud.database.utils.models.user.services.DailyStepsHistoricalEntryService;
import com.uhu.saluhud.database.utils.models.user.services.SaluhudUserDAO;
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
            DailyStepsHistoricalService dailyStepsHistoricalService = new DailyStepsHistoricalService();
            DailyStepsHistoricalEntryService dailyStepsHistoricalEntryService = new DailyStepsHistoricalEntryService();
            SaluhudUserDAO saluhudUserDAO = new SaluhudUserDAO(session);
            LocalDate now = LocalDate.now();
            
            HistoricalEvaluation stepsEvaluation = HistoricalEvaluation.EXCELLENT;
            DailyStepsHistorical dailyStepsHistorical = new DailyStepsHistorical();
            
            List<DailyStepsHistoricalEntry> historicalEntries = new ArrayList<>();
            DailyStepsHistoricalEntry entry = new DailyStepsHistoricalEntry(now, 9000, 400, stepsEvaluation, dailyStepsHistorical);
            historicalEntries.add(entry);   
            
            dailyStepsHistorical.setEntries(historicalEntries);
            
            SaluhudUser user = new SaluhudUser("Juan2k2", "1235", "juan2@gmail.com");
            dailyStepsHistorical.setUser(user);
                       
            saluhudUserDAO.saveUser(user);
            dailyStepsHistoricalService.saveDailyStepsHistorical(dailyStepsHistorical);
            dailyStepsHistoricalEntryService.saveDailyStepsHistoricalEntry(entry);
                      
            adminBootstrapper.closeSessionFactory();
        }
    }
}
