package com.uhu.saluhud.database.utils.models.user;

import com.uhu.saluhud.database.utils.datasource.SaluhudAdminDataSourceConfig;
import com.uhu.saluhud.database.utils.services.saluhud.admin.user.SaluhudAdminUserService;
import com.uhu.saluhud.database.utils.services.saluhud.admin.user.SaluhudAdminSleepHistoricalEntryService;
import com.uhu.saluhud.database.utils.services.saluhud.admin.user.SaluhudAdminSleepHistoricalService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
@DataJpaTest
@TestPropertySource(locations = {"classpath:datasources/saluhud-admin-datasource.properties"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = "com.uhu.saluhud.database.utils.services.saluhud.admin.user")
@ContextConfiguration(classes = SaluhudAdminDataSourceConfig.class)
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
        
        SaluhudUser user2 = new SaluhudUser("Juan2k", "1235", "juan@gmail.com", "Juan");
        saluhudUserService.saveUser(user2);

        List<SleepHistoricalEntry> entries = new ArrayList<>();
        SleepHistoricalEntry entry = new SleepHistoricalEntry(now, 6, 23, sleepEvaluation, sleepHistorical);
        entries.add(entry);

        sleepHistorical.setEntries(entries);

        SaluhudUser user = saluhudUserService.getUserByUsername(user2.getUsername());
        sleepHistorical.setUser(user);

        saluhudUserService.saveUser(user);
        sleepHistoricalService.saveSleepHistorical(sleepHistorical);
        sleepHistoricalEntryService.saveSleepHistoricalEntry(entry);
    }
}
