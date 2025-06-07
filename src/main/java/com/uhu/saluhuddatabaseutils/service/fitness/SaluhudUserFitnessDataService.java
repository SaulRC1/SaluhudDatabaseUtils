package com.uhu.saluhuddatabaseutils.service.fitness;

import com.uhu.saluhuddatabaseutils.models.user.BiologicalSex;
import com.uhu.saluhuddatabaseutils.models.user.BodyComposition;
import com.uhu.saluhuddatabaseutils.models.user.FitnessTargetEnum;
import com.uhu.saluhuddatabaseutils.models.user.HarrisBenedictBMRActivityFactor;
import com.uhu.saluhuddatabaseutils.models.user.SaluhudUserFitnessData;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@Service
public class SaluhudUserFitnessDataService 
{
    @Autowired
    private HarrisBenedictBMRCalculator harrisBenedictBMRCalculator;
    
    @Autowired
    private BodyMassIndexCalculator bodyMassIndexCalculator;
    
    @Autowired
    private WaterIntakeCalculator waterIntakeCalculator;
    
    @Autowired
    private SleepHoursCalculator sleepHoursCalculator;
    
    @Autowired
    private DailyStepsCalculator dailyStepsCalculator;
    
    /**
     * Builds a {@link SaluhudUserFitnessData} instance with the given data.
     * 
     * @param weight The person's weight in kilograms (kg).
     * @param height The person's height in centimeters (cm).
     * @param age The person's age in years.
     * @param biologicalSex The person's sex, either male or female.
     * @param activityFactor The person's Harris-Benedict activity factor given 
     * its lifestyle.
     * @param bodyComposition The person's body composition.
     * @param fitnessTarget The person's fitness target.
     * @return {@link SaluhudUserFitnessData} instance with the given data.
     */
    public SaluhudUserFitnessData buildSaluhudUserFitnessData(float weight, float height, 
            int age, BiologicalSex biologicalSex, HarrisBenedictBMRActivityFactor activityFactor, 
            BodyComposition bodyComposition, FitnessTargetEnum fitnessTarget)
    {
        SaluhudUserFitnessData fitnessData = new SaluhudUserFitnessData();
        fitnessData.setAge(age);
        fitnessData.setBiologicalSex(biologicalSex);
        fitnessData.setHeight(height);
        fitnessData.setWeight(weight);
        fitnessData.setBodyComposition(bodyComposition);
        fitnessData.setActivityFactor(activityFactor);
        fitnessData.setFitnessTarget(fitnessTarget);
        
        fitnessData.setMaintenanceDailyKilocalories(
                harrisBenedictBMRCalculator.calculateDailyEnergyExpenditure(weight, 
                        height, age, biologicalSex, activityFactor));
        
        switch(fitnessTarget)
        {
            case WEIGHT_GAIN:
                fitnessData.setFitnessTargetRecommendedKilocalories(fitnessData.getMaintenanceDailyKilocalories() + 300);
                break;
            case MAINTENANCE:
                fitnessData.setFitnessTargetRecommendedKilocalories(fitnessData.getMaintenanceDailyKilocalories());
                break;
            case WEIGHT_LOSS:
                fitnessData.setFitnessTargetRecommendedKilocalories(fitnessData.getMaintenanceDailyKilocalories() - 300);
                break;
        }
        
        fitnessData.setRecommendedDailySteps(dailyStepsCalculator.calculateDailySteps(activityFactor));
        fitnessData.setRecommendedDailyWaterLiters((int) Math.round(waterIntakeCalculator.calculateDailyWaterIntake(weight, activityFactor)));
        fitnessData.setRecommendedSleepHours(sleepHoursCalculator.calculateSleepHours(age));
        
        double bmi = bodyMassIndexCalculator.calculateBodyMassIndex(weight, (height / 100));
        double bmiRounded = BigDecimal.valueOf(bmi).setScale(1, RoundingMode.HALF_UP).doubleValue();
        fitnessData.setBodyMassIndex(bmiRounded);
        
        return fitnessData;
    }
    
    
}
