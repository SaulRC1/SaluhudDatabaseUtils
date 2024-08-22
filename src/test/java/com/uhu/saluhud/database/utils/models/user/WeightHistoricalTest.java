package com.uhu.saluhud.database.utils.models.user;

import com.uhu.saluhud.database.utils.datasource.SaluhudAdminDataSourceConfig;
import com.uhu.saluhud.database.utils.services.saluhud.admin.user.SaluhudAdminUserService;
import com.uhu.saluhud.database.utils.services.saluhud.admin.user.SaluhudAdminWeightHistoricalEntryService;
import com.uhu.saluhud.database.utils.services.saluhud.admin.user.SaluhudAdminWeightHistoricalService;
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
public class WeightHistoricalTest {

    @Autowired
    private SaluhudAdminWeightHistoricalService weightHistoricalService;

    @Autowired
    private SaluhudAdminWeightHistoricalEntryService weightHistoricalEntryService;

    @Autowired
    private SaluhudAdminUserService saluhudUserService;

    @Test
    public void testWeightHistoricalCRUD() {

        WeightHistorical weightHistorical = new WeightHistorical();
        LocalDate now = LocalDate.now();

        List<WeightHistoricalEntry> entries = new ArrayList<>();
        WeightHistoricalEntry entry = new WeightHistoricalEntry(now, 75, 181, 2200, weightHistorical);
        entries.add(entry);

        weightHistorical.setEntries(entries);

        SaluhudUser user2 = new SaluhudUser("Juan2k", "1235", "juan@gmail.com", "Juan");
        saluhudUserService.saveUser(user2);
        weightHistorical.setUser(user2);

        saluhudUserService.saveUser(user2);
        weightHistoricalService.saveWeightHistorical(weightHistorical);
        weightHistoricalEntryService.saveWeightHistoricalEntry(entry);
    }
}
