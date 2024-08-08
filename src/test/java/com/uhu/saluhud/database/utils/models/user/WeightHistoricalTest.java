package com.uhu.saluhud.database.utils.models.user;

import com.uhu.saluhud.database.utils.models.user.services.SaluhudUserService;
import com.uhu.saluhud.database.utils.models.user.services.WeightHistoricalEntryService;
import com.uhu.saluhud.database.utils.models.user.services.WeightHistoricalService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
public class WeightHistoricalTest {

    @Autowired
    private WeightHistoricalService weightHistoricalService;

    @Autowired
    private WeightHistoricalEntryService weightHistoricalEntryService;

    @Autowired
    private SaluhudUserService saluhudUserService;

    @Test
    public void testWeightHistoricalCRUD() {

        WeightHistorical weightHistorical = new WeightHistorical();
        LocalDate now = LocalDate.now();

        List<WeightHistoricalEntry> entries = new ArrayList<>();
        WeightHistoricalEntry entry = new WeightHistoricalEntry(now, 75, 181, 2200, weightHistorical);
        entries.add(entry);

        weightHistorical.setEntries(entries);

        SaluhudUser user = saluhudUserService.getUserById(7);
        weightHistorical.setUser(user);

        saluhudUserService.saveUser(user);
        weightHistoricalService.saveWeightHistorical(weightHistorical);
        weightHistoricalEntryService.saveWeightHistoricalEntry(entry);
    }
}
