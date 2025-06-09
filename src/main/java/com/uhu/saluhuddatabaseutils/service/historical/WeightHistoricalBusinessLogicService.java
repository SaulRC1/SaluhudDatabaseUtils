package com.uhu.saluhuddatabaseutils.service.historical;

import com.uhu.saluhuddatabaseutils.models.user.WeightHistorical;
import com.uhu.saluhuddatabaseutils.models.user.WeightHistoricalEntry;
import java.time.LocalDate;
import org.springframework.stereotype.Service;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@Service
public class WeightHistoricalBusinessLogicService 
{
    public WeightHistoricalEntry buildEntry(WeightHistorical parentHistorical, LocalDate entryDate, Double weight, Double height, 
            Integer kilocaloriesObjective)
    {
        WeightHistoricalEntry entry = new WeightHistoricalEntry();
        
        entry.setEntryDate(entryDate);
        entry.setWeightEntry(weight);
        entry.setHeightEntry(height);
        entry.setKilocaloriesObjectiveEntry(kilocaloriesObjective);
        entry.setWeightHistorical(parentHistorical);
        
        return entry;
    }
}
