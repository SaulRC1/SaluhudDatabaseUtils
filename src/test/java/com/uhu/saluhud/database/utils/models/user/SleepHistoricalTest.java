package com.uhu.saluhud.database.utils.models.user;

import com.uhu.saluhud.database.utils.services.saluhud.admin.user.SaluhudAdminUserService;
import com.uhu.saluhud.database.utils.services.saluhud.admin.user.SaluhudAdminSleepHistoricalEntryService;
import com.uhu.saluhud.database.utils.services.saluhud.admin.user.SaluhudAdminSleepHistoricalService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
public class SleepHistoricalTest {

    @Autowired
    private SaluhudAdminSleepHistoricalService sleepHistoricalService;

    @Autowired
    private SaluhudAdminSleepHistoricalEntryService sleepHistoricalEntryService;

    @Autowired
    private SaluhudAdminUserService saluhudUserService;

    @Test
    public void testSleepHistoricalCRUD() {

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
        sleepHistoricalService.saveSleepHistorical(sleepHistorical);
        sleepHistoricalEntryService.saveSleepHistoricalEntry(entry);
    }
}
