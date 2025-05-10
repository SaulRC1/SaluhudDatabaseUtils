package com.uhu.saluhuddatabaseutils.test.service.fitness;

import com.uhu.saluhuddatabaseutils.models.user.HarrisBenedictBMRActivityFactor;
import com.uhu.saluhuddatabaseutils.service.fitness.DailyStepsCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
public class DailyStepsCalculatorTest 
{
    private DailyStepsCalculator dailyStepsCalculator = new DailyStepsCalculator();
    
    @Test
    public void calculateDailySteps_returns_correct_value()
    {
        int dailySteps = dailyStepsCalculator.calculateDailySteps(HarrisBenedictBMRActivityFactor.LIGHTLY_ACTIVE);
        
        Assertions.assertEquals(7500, dailySteps);
    }
}
