package com.uhu.saluhuddatabaseutils.test.service.fitness;

import com.uhu.saluhuddatabaseutils.models.user.BiologicalSex;
import com.uhu.saluhuddatabaseutils.models.user.BodyComposition;
import com.uhu.saluhuddatabaseutils.models.user.HarrisBenedictBMRActivityFactor;
import com.uhu.saluhuddatabaseutils.models.user.SaluhudUserFitnessData;
import com.uhu.saluhuddatabaseutils.service.fitness.BodyMassIndexCalculator;
import com.uhu.saluhuddatabaseutils.service.fitness.DailyStepsCalculator;
import com.uhu.saluhuddatabaseutils.service.fitness.HarrisBenedictBMRCalculator;
import com.uhu.saluhuddatabaseutils.service.fitness.SaluhudUserFitnessDataService;
import com.uhu.saluhuddatabaseutils.service.fitness.SleepHoursCalculator;
import com.uhu.saluhuddatabaseutils.service.fitness.WaterIntakeCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@SpringBootTest(classes = {SaluhudUserFitnessDataService.class, HarrisBenedictBMRCalculator.class, 
    BodyMassIndexCalculator.class, WaterIntakeCalculator.class, SleepHoursCalculator.class, DailyStepsCalculator.class})
public class SaluhudUserFitnessDataServiceTest 
{
    @Autowired
    private SaluhudUserFitnessDataService saluhudUserFitnessDataService;
    
    @Test
    public void buildSaluhudUserFitnessData_builds_instance_with_correct_values()
    {
        BodyComposition bodyComposition = new BodyComposition(70, 30, 21, 49);
        
        SaluhudUserFitnessData fitnessData = saluhudUserFitnessDataService.buildSaluhudUserFitnessData(70, 175, 20, BiologicalSex.MALE, 
                HarrisBenedictBMRActivityFactor.MODERATELY_ACTIVE, bodyComposition);
        
        Assertions.assertEquals(70, fitnessData.getWeight());
        Assertions.assertEquals(175, fitnessData.getHeight());
        Assertions.assertEquals(20, fitnessData.getAge());
        Assertions.assertTrue(HarrisBenedictBMRActivityFactor.MODERATELY_ACTIVE == fitnessData.getActivityFactor());
        Assertions.assertEquals(bodyComposition, fitnessData.getBodyComposition());
        Assertions.assertTrue(BiologicalSex.MALE == fitnessData.getBiologicalSex());
        Assertions.assertEquals(22.9, fitnessData.getBodyMassIndex());
        Assertions.assertEquals(2633, fitnessData.getDailyKilocaloriesObjective());
        Assertions.assertEquals(10000, fitnessData.getRecommendedDailySteps());
        Assertions.assertEquals(3, fitnessData.getRecommendedDailyWaterLiters());
        Assertions.assertEquals(8, fitnessData.getRecommendedSleepHours());
    }
}
