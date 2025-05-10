package com.uhu.saluhuddatabaseutils.test.service.fitness;

import com.uhu.saluhuddatabaseutils.models.user.BiologicalSex;
import com.uhu.saluhuddatabaseutils.models.user.HarrisBenedictBMRActivityFactor;
import com.uhu.saluhuddatabaseutils.service.fitness.HarrisBenedictBMRCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
public class HarrisBenedictBMRCalculatorTest 
{
    private HarrisBenedictBMRCalculator harrisBenedictBMRCalculator = new HarrisBenedictBMRCalculator();
    
    @Test
    public void calculate_male_bmr_return_right_value()
    {
        double bmr = harrisBenedictBMRCalculator.calculateBasalMetabolicRate(70, 175, 20, BiologicalSex.MALE);
        
        Assertions.assertEquals(1698.75, bmr);
    }
    
    @Test
    public void calculate_female_bmr_return_right_value()
    {
        double bmr = harrisBenedictBMRCalculator.calculateBasalMetabolicRate(60, 165, 20, BiologicalSex.FEMALE);
        
        Assertions.assertEquals(1370.25, bmr);
    }
    
    @Test
    public void calculate_male_daily_energy_expenditure_return_right_value()
    {
        int dailyEnergyExpenditure = harrisBenedictBMRCalculator
                .calculateDailyEnergyExpenditure(70, 175, 20, BiologicalSex.MALE, HarrisBenedictBMRActivityFactor.MODERATELY_ACTIVE);
        
        Assertions.assertEquals(2633, dailyEnergyExpenditure);
    }
    
    @Test
    public void calculate_female_daily_energy_expenditure_return_right_value()
    {
        int dailyEnergyExpenditure = harrisBenedictBMRCalculator
                .calculateDailyEnergyExpenditure(60, 165, 20, BiologicalSex.FEMALE, HarrisBenedictBMRActivityFactor.MODERATELY_ACTIVE);
        
        Assertions.assertEquals(2124, dailyEnergyExpenditure);
    }
}
