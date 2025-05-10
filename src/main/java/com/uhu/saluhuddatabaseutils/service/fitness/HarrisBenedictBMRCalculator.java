package com.uhu.saluhuddatabaseutils.service.fitness;

import com.uhu.saluhuddatabaseutils.models.user.BiologicalSex;
import static com.uhu.saluhuddatabaseutils.models.user.BiologicalSex.FEMALE;
import static com.uhu.saluhuddatabaseutils.models.user.BiologicalSex.MALE;
import com.uhu.saluhuddatabaseutils.models.user.HarrisBenedictBMRActivityFactor;
import org.springframework.stereotype.Service;

/**
 * Calculates BMR (Basal Metabolic Rate) with the Harris-Benedict formula.
 * 
 * @author Saúl Rodríguez Naranjo
 */
@Service
public class HarrisBenedictBMRCalculator 
{
    /**
     * Calculates the BMR (Basal Metabolic Rate) using the Harris-Benedict formula.
     * 
     * @param weight Weight in kilograms (kg).
     * @param height Height in centimeters (cm).
     * @param age Age in years.
     * @param biologicalSex Biological sex, either male or female.
     * @return The BMR, which is the daily energy expenditure in kilocalories the 
     * body needs to function in a rest state.
     */
    public double calculateBasalMetabolicRate(float weight, float height, int age, BiologicalSex biologicalSex)
    {
        switch(biologicalSex)
        {
            case MALE:
                return calculateBasalMetabolicRateMale(weight, height, age);
            case FEMALE:
                return calculateBasalMetabolicRateFemale(weight, height, age);
            default:
                throw new IllegalArgumentException("Biological sex should be either male or female.");
        }
    }
    
    private double calculateBasalMetabolicRateMale(float weight, float height, int age)
    {
        return ((10 * weight) + (6.25 * height) - (5 * age) + 5);
    }
    
    private double calculateBasalMetabolicRateFemale(float weight, float height, int age)
    {
        return ((10 * weight) + (6.25 * height) - (5 * age) - 161);
    }
    
    /**
     * Calculates the total daily energy expenditure in kilocalories that the person
     * needs to perform body functions and also daily physical activity may it be
     * sport, work, or both.
     * 
     * @param weight Weight in kilograms (kg).
     * @param height Height in centimeters (cm).
     * @param age Age in years.
     * @param biologicalSex Biological sex, either male or female.
     * @param activityFactor Harris-Benedict activity factor.
     * @return Total daily energy expenditure in kilocalories.
     */
    public int calculateDailyEnergyExpenditure(float weight, float height, int age, 
            BiologicalSex biologicalSex, HarrisBenedictBMRActivityFactor activityFactor)
    {
        double bmr = calculateBasalMetabolicRate(weight, height, age, biologicalSex);
        
        return (int) Math.round(bmr * activityFactor.getActivityFactorValue());
    }
}
