package com.uhu.saluhud.database.utils.models.user;

import com.uhu.saluhud.database.utils.datasource.SaluhudAdminDataSourceConfig;
import com.uhu.saluhud.database.utils.services.saluhud.admin.user.SaluhudAdminDailyStepsHistoricalService;
import com.uhu.saluhud.database.utils.services.saluhud.admin.user.SaluhudAdminDailyStepsHistoricalEntryService;
import com.uhu.saluhud.database.utils.services.saluhud.admin.user.SaluhudAdminUserService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
@DataJpaTest
@TestPropertySource(locations = {"classpath:datasources/saluhud-admin-datasource.properties"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = "com.uhu.saluhud.database.utils.services.saluhud.admin.user")
@ContextConfiguration(classes = SaluhudAdminDataSourceConfig.class)
public class DailyStepsHistoricalTest {

    @Autowired
    private SaluhudAdminDailyStepsHistoricalService dailyStepsHistoricalService;

    @Autowired
    private SaluhudAdminDailyStepsHistoricalEntryService dailyStepsHistoricalEntryService;

    @Autowired
    private SaluhudAdminUserService saluhudUserService;

    @Test
    @Transactional(transactionManager = "saluhudAdminTransactionManager")
    @Rollback
    public void testDailyStepsHistoricalCRUD() {
        LocalDate now = LocalDate.now();

        HistoricalEvaluation stepsEvaluation = HistoricalEvaluation.EXCELLENT;
        DailyStepsHistorical dailyStepsHistorical = new DailyStepsHistorical();

        List<DailyStepsHistoricalEntry> historicalEntries = new ArrayList<>();
        DailyStepsHistoricalEntry entry = new DailyStepsHistoricalEntry(now, 9000, 400, stepsEvaluation, dailyStepsHistorical);
        historicalEntries.add(entry);

        dailyStepsHistorical.setEntries(historicalEntries);

        SaluhudUser user = new SaluhudUser("Juan2k2", "1235Password%%", "juan2@gmail.com", "Juan");
        user.setPassword(user.getRawPassword());
        dailyStepsHistorical.setUser(user);

        saluhudUserService.saveUser(user);
        dailyStepsHistoricalService.saveDailyStepsHistorical(dailyStepsHistorical);
        dailyStepsHistoricalEntryService.saveDailyStepsHistoricalEntry(entry);
    }
}
