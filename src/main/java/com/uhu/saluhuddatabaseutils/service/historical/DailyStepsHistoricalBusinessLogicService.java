package com.uhu.saluhuddatabaseutils.service.historical;

import com.uhu.saluhuddatabaseutils.models.user.DailyStepsHistorical;
import com.uhu.saluhuddatabaseutils.models.user.DailyStepsHistoricalEntry;
import com.uhu.saluhuddatabaseutils.models.user.HistoricalEvaluation;
import java.time.LocalDate;
import org.springframework.stereotype.Service;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@Service
public class DailyStepsHistoricalBusinessLogicService 
{
    public DailyStepsHistoricalEntry buildEntry(DailyStepsHistorical parentHistorical, Integer numberOfSteps, LocalDate entryDate, 
            Integer targetNumberOfSteps)
    {
        DailyStepsHistoricalEntry entry = new DailyStepsHistoricalEntry();
        
        entry.setDailyStepsHistorical(parentHistorical);
        entry.setDoneSteps(numberOfSteps);
        entry.setEntryDate(entryDate);
        
        //A step normally burns 0.04 kcal
        entry.setKiloCaloriesBurned(numberOfSteps * 0.04);
        
        if(numberOfSteps >= targetNumberOfSteps)
        {
            entry.setStepEvaluation(HistoricalEvaluation.EXCELLENT);
        }
        else
        {
            int stepsDifference = targetNumberOfSteps - numberOfSteps;
            
            if(stepsDifference <= 1000)
            {
                entry.setStepEvaluation(HistoricalEvaluation.WELL);
            }
            else if(stepsDifference > 1000 && stepsDifference <= 3000)
            {
                entry.setStepEvaluation(HistoricalEvaluation.MINIMUM);
            }
            else
            {
                entry.setStepEvaluation(HistoricalEvaluation.FAILED);
            }
        }
        
        return entry;
    }
}
