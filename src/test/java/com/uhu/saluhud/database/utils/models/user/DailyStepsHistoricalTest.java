package com.uhu.saluhud.database.utils.models.user;

import com.uhu.saluhud.database.utils.services.saluhud.admin.user.SaluhudAdminDailyStepsHistoricalService;
import com.uhu.saluhud.database.utils.services.saluhud.admin.user.SaluhudAdminDailyStepsHistoricalEntryService;
import com.uhu.saluhud.database.utils.services.saluhud.admin.user.SaluhudAdminUserService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
public class DailyStepsHistoricalTest {

    @Autowired
    private SaluhudAdminDailyStepsHistoricalService dailyStepsHistoricalService;

    @Autowired
    private SaluhudAdminDailyStepsHistoricalEntryService dailyStepsHistoricalEntryService;

    @Autowired
    private SaluhudAdminUserService saluhudUserService;

    @Test
    public void testDailyStepsHistoricalCRUD() {
        LocalDate now = LocalDate.now();

        HistoricalEvaluation stepsEvaluation = HistoricalEvaluation.EXCELLENT;
        DailyStepsHistorical dailyStepsHistorical = new DailyStepsHistorical();

        List<DailyStepsHistoricalEntry> historicalEntries = new ArrayList<>();
        DailyStepsHistoricalEntry entry = new DailyStepsHistoricalEntry(now, 9000, 400, stepsEvaluation, dailyStepsHistorical);
        historicalEntries.add(entry);

        dailyStepsHistorical.setEntries(historicalEntries);

        SaluhudUser user = new SaluhudUser("Juan2k2", "1235", "juan2@gmail.com", "Juan");
        dailyStepsHistorical.setUser(user);

        saluhudUserService.saveUser(user);
        dailyStepsHistoricalService.saveDailyStepsHistorical(dailyStepsHistorical);
        dailyStepsHistoricalEntryService.saveDailyStepsHistoricalEntry(entry);
    }
}
