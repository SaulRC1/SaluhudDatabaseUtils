package com.uhu.saluhud.database.utils.models.user;

import com.uhu.saluhud.database.utils.bootstrap.SaluhudAdministratorHibernateBootstrapper;
import com.uhu.saluhud.database.utils.bootstrap.SaluhudHibernateBootstrapper;
import com.uhu.saluhud.database.utils.models.user.services.SaluhudUserService;
import com.uhu.saluhud.database.utils.models.user.services.SleepHistoricalEntryService;
import com.uhu.saluhud.database.utils.models.user.services.SleepHistoricalService;
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
public class SleepHistoricalTest {

    @Test
    public void testSleepHistoricalCRUD() {

        SleepHistoricalService sleepHistoricalDAO = new SleepHistoricalService();
        SleepHistoricalEntryService sleepHistoricalEntryDAO = new SleepHistoricalEntryService();
        SaluhudUserService saluhudUserService = new SaluhudUserService();

        SleepHistorical sleepHistorical = new SleepHistorical();
        LocalDate now = LocalDate.now();
        HistoricalEvaluation sleepEvaluation = HistoricalEvaluation.MINIMUM;

        List<SleepHistoricalEntry> entries = new ArrayList<>();
        SleepHistoricalEntry entry = new SleepHistoricalEntry(now, 6, 23, sleepEvaluation, sleepHistorical);
        entries.add(entry);

        sleepHistorical.setEntries(entries);

        SaluhudUser user = saluhudUserService.getUserById(7);
        sleepHistorical.setUser(user);

        saluhudUserService.saveUser(user);
        sleepHistoricalDAO.saveSleepHistorical(sleepHistorical);
        sleepHistoricalEntryDAO.saveSleepHistoricalEntry(entry);
    }
}
