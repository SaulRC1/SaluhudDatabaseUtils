package com.uhu.saluhuddatabaseutils.test.service.fitness;

import com.uhu.saluhuddatabaseutils.models.user.HarrisBenedictBMRActivityFactor;
import com.uhu.saluhuddatabaseutils.service.fitness.WaterIntakeCalculator;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
public class WaterIntakeCalculatorTest 
{
    private WaterIntakeCalculator waterIntakeCalculator = new WaterIntakeCalculator();
    
    @Test
    public void calculateDailyWaterIntake_return_correct_value_for_sedentary_activity_factor()
    {
        double dailyWaterIntake = waterIntakeCalculator.calculateDailyWaterIntake(70, HarrisBenedictBMRActivityFactor.SEDENTARY);
        
        Assertions.assertEquals(2.45, dailyWaterIntake);
    }
    
    @Test
    public void calculateDailyWaterIntake_return_correct_value_for_lightly_active_activity_factor()
    {
        double dailyWaterIntake = waterIntakeCalculator.calculateDailyWaterIntake(70, HarrisBenedictBMRActivityFactor.LIGHTLY_ACTIVE);
        double dailyWaterIntakeRounded = new BigDecimal(dailyWaterIntake).setScale(2, RoundingMode.HALF_UP).doubleValue();
        
        Assertions.assertEquals(2.65, dailyWaterIntakeRounded);
    }
    
    @Test
    public void calculateDailyWaterIntake_return_correct_value_for_moderately_active_activity_factor()
    {
        double dailyWaterIntake = waterIntakeCalculator.calculateDailyWaterIntake(70, HarrisBenedictBMRActivityFactor.MODERATELY_ACTIVE);
        double dailyWaterIntakeRounded = new BigDecimal(dailyWaterIntake).setScale(2, RoundingMode.HALF_UP).doubleValue();
        
        Assertions.assertEquals(2.85, dailyWaterIntakeRounded);
    }
    
    @Test
    public void calculateDailyWaterIntake_return_correct_value_for_very_active_activity_factor()
    {
        double dailyWaterIntake = waterIntakeCalculator.calculateDailyWaterIntake(70, HarrisBenedictBMRActivityFactor.VERY_ACTIVE);
        double dailyWaterIntakeRounded = new BigDecimal(dailyWaterIntake).setScale(2, RoundingMode.HALF_UP).doubleValue();
        
        Assertions.assertEquals(3.05, dailyWaterIntakeRounded);
    }
    
    @Test
    public void calculateDailyWaterIntake_return_correct_value_for_extra_active_activity_factor()
    {
        double dailyWaterIntake = waterIntakeCalculator.calculateDailyWaterIntake(70, HarrisBenedictBMRActivityFactor.EXTRA_ACTIVE);
        double dailyWaterIntakeRounded = new BigDecimal(dailyWaterIntake).setScale(2, RoundingMode.HALF_UP).doubleValue();
        
        Assertions.assertEquals(3.45, dailyWaterIntakeRounded);
    }
    
    @Test
    public void calculateDailyWaterIntake_return_correct_value_for_professional_athlete_activity_factor()
    {
        double dailyWaterIntake = waterIntakeCalculator.calculateDailyWaterIntake(70, HarrisBenedictBMRActivityFactor.PROFESSIONAL_ATHLETE);
        double dailyWaterIntakeRounded = new BigDecimal(dailyWaterIntake).setScale(2, RoundingMode.HALF_UP).doubleValue();
        
        Assertions.assertEquals(3.85, dailyWaterIntakeRounded);
    }
}
