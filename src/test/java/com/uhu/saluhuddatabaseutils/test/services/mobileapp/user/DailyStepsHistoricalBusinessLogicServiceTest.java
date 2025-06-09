package com.uhu.saluhuddatabaseutils.test.services.mobileapp.user;

import com.uhu.saluhuddatabaseutils.models.user.DailyStepsHistorical;
import com.uhu.saluhuddatabaseutils.models.user.DailyStepsHistoricalEntry;
import com.uhu.saluhuddatabaseutils.models.user.HistoricalEvaluation;
import com.uhu.saluhuddatabaseutils.service.historical.DailyStepsHistoricalBusinessLogicService;
import com.uhu.saluhuddatabaseutils.test.configuration.BaseTest;
import java.time.LocalDate;
import java.time.Month;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
public class DailyStepsHistoricalBusinessLogicServiceTest extends BaseTest 
{
    @Autowired
    private DailyStepsHistoricalBusinessLogicService businessLogicService;
            
    @Test
    void test_buildEntry_with_excellent_evaluation()
    {
        DailyStepsHistorical parentHistorical = getDailyStepsHistoricalMock();
        
        DailyStepsHistoricalEntry expectedEntry = new DailyStepsHistoricalEntry();
        expectedEntry.setDailyStepsHistorical(parentHistorical);
        expectedEntry.setDoneSteps(10000);
        expectedEntry.setEntryDate(LocalDate.of(2025, Month.JUNE, 8));
        expectedEntry.setKiloCaloriesBurned(10000 * 0.04);
        expectedEntry.setStepEvaluation(HistoricalEvaluation.EXCELLENT);
        
        DailyStepsHistoricalEntry entry = this.businessLogicService.buildEntry(parentHistorical, 10000, LocalDate.of(2025, Month.JUNE, 8), 10000);
        
        Assertions.assertEquals(expectedEntry.getDoneSteps(), entry.getDoneSteps());
        Assertions.assertEquals(expectedEntry.getDailyStepsHistorical().getId(), entry.getDailyStepsHistorical().getId());
        Assertions.assertEquals(expectedEntry.getKiloCaloriesBurned(), entry.getKiloCaloriesBurned());
        Assertions.assertEquals(expectedEntry.getEntryDate(), entry.getEntryDate());
        Assertions.assertEquals(expectedEntry.getStepEvaluation(), entry.getStepEvaluation());
    }
    
    @Test
    void test_buildEntry_with_well_evaluation_minus_500_steps()
    {
        DailyStepsHistorical parentHistorical = getDailyStepsHistoricalMock();
        
        DailyStepsHistoricalEntry expectedEntry = new DailyStepsHistoricalEntry();
        expectedEntry.setDailyStepsHistorical(parentHistorical);
        expectedEntry.setDoneSteps(9500);
        expectedEntry.setEntryDate(LocalDate.of(2025, Month.JUNE, 8));
        expectedEntry.setKiloCaloriesBurned(9500 * 0.04);
        expectedEntry.setStepEvaluation(HistoricalEvaluation.WELL);
        
        DailyStepsHistoricalEntry entry = this.businessLogicService.buildEntry(parentHistorical, 9500, LocalDate.of(2025, Month.JUNE, 8), 10000);
        
        Assertions.assertEquals(expectedEntry.getDoneSteps(), entry.getDoneSteps());
        Assertions.assertEquals(expectedEntry.getDailyStepsHistorical().getId(), entry.getDailyStepsHistorical().getId());
        Assertions.assertEquals(expectedEntry.getKiloCaloriesBurned(), entry.getKiloCaloriesBurned());
        Assertions.assertEquals(expectedEntry.getEntryDate(), entry.getEntryDate());
        Assertions.assertEquals(expectedEntry.getStepEvaluation(), entry.getStepEvaluation());
    }
    
    @Test
    void test_buildEntry_with_well_evaluation_minus_1000_steps()
    {
        DailyStepsHistorical parentHistorical = getDailyStepsHistoricalMock();
        
        DailyStepsHistoricalEntry expectedEntry = new DailyStepsHistoricalEntry();
        expectedEntry.setDailyStepsHistorical(parentHistorical);
        expectedEntry.setDoneSteps(9000);
        expectedEntry.setEntryDate(LocalDate.of(2025, Month.JUNE, 8));
        expectedEntry.setKiloCaloriesBurned(9000 * 0.04);
        expectedEntry.setStepEvaluation(HistoricalEvaluation.WELL);
        
        DailyStepsHistoricalEntry entry = this.businessLogicService.buildEntry(parentHistorical, 9000, LocalDate.of(2025, Month.JUNE, 8), 10000);
        
        Assertions.assertEquals(expectedEntry.getDoneSteps(), entry.getDoneSteps());
        Assertions.assertEquals(expectedEntry.getDailyStepsHistorical().getId(), entry.getDailyStepsHistorical().getId());
        Assertions.assertEquals(expectedEntry.getKiloCaloriesBurned(), entry.getKiloCaloriesBurned());
        Assertions.assertEquals(expectedEntry.getEntryDate(), entry.getEntryDate());
        Assertions.assertEquals(expectedEntry.getStepEvaluation(), entry.getStepEvaluation());
    }
    
    @Test
    void test_buildEntry_with_minimum_evaluation_minus_1001_steps()
    {
        DailyStepsHistorical parentHistorical = getDailyStepsHistoricalMock();
        
        DailyStepsHistoricalEntry expectedEntry = new DailyStepsHistoricalEntry();
        expectedEntry.setDailyStepsHistorical(parentHistorical);
        expectedEntry.setDoneSteps(8999);
        expectedEntry.setEntryDate(LocalDate.of(2025, Month.JUNE, 8));
        expectedEntry.setKiloCaloriesBurned(8999 * 0.04);
        expectedEntry.setStepEvaluation(HistoricalEvaluation.MINIMUM);
        
        DailyStepsHistoricalEntry entry = this.businessLogicService.buildEntry(parentHistorical, 8999, LocalDate.of(2025, Month.JUNE, 8), 10000);
        
        Assertions.assertEquals(expectedEntry.getDoneSteps(), entry.getDoneSteps());
        Assertions.assertEquals(expectedEntry.getDailyStepsHistorical().getId(), entry.getDailyStepsHistorical().getId());
        Assertions.assertEquals(expectedEntry.getKiloCaloriesBurned(), entry.getKiloCaloriesBurned());
        Assertions.assertEquals(expectedEntry.getEntryDate(), entry.getEntryDate());
        Assertions.assertEquals(expectedEntry.getStepEvaluation(), entry.getStepEvaluation());
    }
    
    @Test
    void test_buildEntry_with_minimum_evaluation_minus_2000_steps()
    {
        DailyStepsHistorical parentHistorical = getDailyStepsHistoricalMock();
        
        DailyStepsHistoricalEntry expectedEntry = new DailyStepsHistoricalEntry();
        expectedEntry.setDailyStepsHistorical(parentHistorical);
        expectedEntry.setDoneSteps(8000);
        expectedEntry.setEntryDate(LocalDate.of(2025, Month.JUNE, 8));
        expectedEntry.setKiloCaloriesBurned(8000 * 0.04);
        expectedEntry.setStepEvaluation(HistoricalEvaluation.MINIMUM);
        
        DailyStepsHistoricalEntry entry = this.businessLogicService.buildEntry(parentHistorical, 8000, LocalDate.of(2025, Month.JUNE, 8), 10000);
        
        Assertions.assertEquals(expectedEntry.getDoneSteps(), entry.getDoneSteps());
        Assertions.assertEquals(expectedEntry.getDailyStepsHistorical().getId(), entry.getDailyStepsHistorical().getId());
        Assertions.assertEquals(expectedEntry.getKiloCaloriesBurned(), entry.getKiloCaloriesBurned());
        Assertions.assertEquals(expectedEntry.getEntryDate(), entry.getEntryDate());
        Assertions.assertEquals(expectedEntry.getStepEvaluation(), entry.getStepEvaluation());
    }
    
    @Test
    void test_buildEntry_with_minimum_evaluation_minus_3000_steps()
    {
        DailyStepsHistorical parentHistorical = getDailyStepsHistoricalMock();
        
        DailyStepsHistoricalEntry expectedEntry = new DailyStepsHistoricalEntry();
        expectedEntry.setDailyStepsHistorical(parentHistorical);
        expectedEntry.setDoneSteps(7000);
        expectedEntry.setEntryDate(LocalDate.of(2025, Month.JUNE, 8));
        expectedEntry.setKiloCaloriesBurned(7000 * 0.04);
        expectedEntry.setStepEvaluation(HistoricalEvaluation.MINIMUM);
        
        DailyStepsHistoricalEntry entry = this.businessLogicService.buildEntry(parentHistorical, 7000, LocalDate.of(2025, Month.JUNE, 8), 10000);
        
        Assertions.assertEquals(expectedEntry.getDoneSteps(), entry.getDoneSteps());
        Assertions.assertEquals(expectedEntry.getDailyStepsHistorical().getId(), entry.getDailyStepsHistorical().getId());
        Assertions.assertEquals(expectedEntry.getKiloCaloriesBurned(), entry.getKiloCaloriesBurned());
        Assertions.assertEquals(expectedEntry.getEntryDate(), entry.getEntryDate());
        Assertions.assertEquals(expectedEntry.getStepEvaluation(), entry.getStepEvaluation());
    }
    
    @Test
    void test_buildEntry_with_failed_evaluation_minus_3001_steps()
    {
        DailyStepsHistorical parentHistorical = getDailyStepsHistoricalMock();
        
        DailyStepsHistoricalEntry expectedEntry = new DailyStepsHistoricalEntry();
        expectedEntry.setDailyStepsHistorical(parentHistorical);
        expectedEntry.setDoneSteps(6999);
        expectedEntry.setEntryDate(LocalDate.of(2025, Month.JUNE, 8));
        expectedEntry.setKiloCaloriesBurned(6999 * 0.04);
        expectedEntry.setStepEvaluation(HistoricalEvaluation.FAILED);
        
        DailyStepsHistoricalEntry entry = this.businessLogicService.buildEntry(parentHistorical, 6999, LocalDate.of(2025, Month.JUNE, 8), 10000);
        
        Assertions.assertEquals(expectedEntry.getDoneSteps(), entry.getDoneSteps());
        Assertions.assertEquals(expectedEntry.getDailyStepsHistorical().getId(), entry.getDailyStepsHistorical().getId());
        Assertions.assertEquals(expectedEntry.getKiloCaloriesBurned(), entry.getKiloCaloriesBurned());
        Assertions.assertEquals(expectedEntry.getEntryDate(), entry.getEntryDate());
        Assertions.assertEquals(expectedEntry.getStepEvaluation(), entry.getStepEvaluation());
    }
    
    @Test
    void test_buildEntry_with_failed_evaluation_minus_4000_steps()
    {
        DailyStepsHistorical parentHistorical = getDailyStepsHistoricalMock();
        
        DailyStepsHistoricalEntry expectedEntry = new DailyStepsHistoricalEntry();
        expectedEntry.setDailyStepsHistorical(parentHistorical);
        expectedEntry.setDoneSteps(6000);
        expectedEntry.setEntryDate(LocalDate.of(2025, Month.JUNE, 8));
        expectedEntry.setKiloCaloriesBurned(6000 * 0.04);
        expectedEntry.setStepEvaluation(HistoricalEvaluation.FAILED);
        
        DailyStepsHistoricalEntry entry = this.businessLogicService.buildEntry(parentHistorical, 6000, LocalDate.of(2025, Month.JUNE, 8), 10000);
        
        Assertions.assertEquals(expectedEntry.getDoneSteps(), entry.getDoneSteps());
        Assertions.assertEquals(expectedEntry.getDailyStepsHistorical().getId(), entry.getDailyStepsHistorical().getId());
        Assertions.assertEquals(expectedEntry.getKiloCaloriesBurned(), entry.getKiloCaloriesBurned());
        Assertions.assertEquals(expectedEntry.getEntryDate(), entry.getEntryDate());
        Assertions.assertEquals(expectedEntry.getStepEvaluation(), entry.getStepEvaluation());
    }
    
    private static DailyStepsHistorical getDailyStepsHistoricalMock()
    {
        DailyStepsHistorical historical = new DailyStepsHistorical();
        
        historical.setId(20);
        
        return historical;
    }
}
