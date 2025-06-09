package com.uhu.saluhuddatabaseutils.service.historical;

import com.uhu.saluhuddatabaseutils.models.user.HistoricalEvaluation;
import com.uhu.saluhuddatabaseutils.models.user.SleepHistorical;
import com.uhu.saluhuddatabaseutils.models.user.SleepHistoricalEntry;
import java.time.LocalDate;
import org.springframework.stereotype.Service;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@Service
public class SleepHistoricalBusinessLogicService {
    
    public SleepHistoricalEntry buildEntry(SleepHistorical parentHistorical, LocalDate entryDate, Integer hoursSlept, Integer minutesSlept)
    {
        SleepHistoricalEntry entry = new SleepHistoricalEntry();
        
        entry.setEntryDate(entryDate);
        entry.setHoursSlept(hoursSlept);
        entry.setMinutesSlept(minutesSlept);
        entry.setSleepHistorical(parentHistorical);
        
        int totalMinutesSlept = (hoursSlept * 60) + minutesSlept;
        
        if(totalMinutesSlept >= 480)
        {
            entry.setSleepEvaluation(HistoricalEvaluation.EXCELLENT);
        }
        else if(totalMinutesSlept >= 420 && totalMinutesSlept < 480)
        {
            entry.setSleepEvaluation(HistoricalEvaluation.WELL);
        }
        else if(totalMinutesSlept >= 360 && totalMinutesSlept < 420)
        {
            entry.setSleepEvaluation(HistoricalEvaluation.MINIMUM);
        }
        else
        {
            entry.setSleepEvaluation(HistoricalEvaluation.FAILED);
        }
        
        return entry;
    }

}
