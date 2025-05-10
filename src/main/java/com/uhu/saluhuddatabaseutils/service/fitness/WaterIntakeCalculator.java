package com.uhu.saluhuddatabaseutils.service.fitness;

import com.uhu.saluhuddatabaseutils.models.user.HarrisBenedictBMRActivityFactor;
import org.springframework.stereotype.Service;

/**
 * Water intake calculator based on the European Food Safety Authority (EFSA),
 * World Health Organization (WHO), American Council on Exercise (ACE) and
 * National Academies of Sciences, Engineering, and Medicine (NASEM)
 * recommendations and guidelines.
 *
 * @author Saúl Rodríguez Naranjo
 */
@Service
public class WaterIntakeCalculator
{    
    /**
     * Calculates the daily water intake in liters (L) depending on the person's 
     * weight and Harris-Benedict activity factor.
     * 
     * @param weight Weight in kg.
     * @param activityFactor Activity factor.
     * @return Daily water intake expressed in liters.
     */
    public double calculateDailyWaterIntake(double weight, HarrisBenedictBMRActivityFactor activityFactor)
    {
        double dailyWaterLiters = (35 * weight) / 1000;
        
        //The recommended water amount per 30 minutes of exercise is 350 ml. A day
        //of exercise based on Harris-Benedict activity factor will be considered
        //as an average of 1 hour of sport.
        double waterAmountPer30MinutesExercise = 0.35; //Liters (L)
        double dailyWaterLitersAdjusted = dailyWaterLiters;
        
        switch(activityFactor)
        {
            case SEDENTARY:
                //Do nothing
                break;
            case LIGHTLY_ACTIVE:
                //We will take as an average 2 days of exercise per week with 1
                //hour duration, which is equivalent to 4 sections of 30 mins and
                //and distributed along the 7 days of the week
                dailyWaterLitersAdjusted += (waterAmountPer30MinutesExercise * 4) / 7;
                break;
            case MODERATELY_ACTIVE:
                //We will take as an average 4 days of exercise per week with 1
                //hour duration.
                dailyWaterLitersAdjusted += (waterAmountPer30MinutesExercise * 8) / 7;
                break;
            case VERY_ACTIVE:
                //We will take as an average 6 days of exercise per week with 1
                //hour duration.
                dailyWaterLitersAdjusted += (waterAmountPer30MinutesExercise * 12) / 7;
                break;
            case EXTRA_ACTIVE:
                //We will take as an average 6 days of exercise per week with 1
                //hour duration and a relatively physical job of 8 hours per day
                //which translates into 4 hours of exercise.
                dailyWaterLitersAdjusted += (waterAmountPer30MinutesExercise * 20) / 7;
                break;
            case PROFESSIONAL_ATHLETE:
                //We will take as an average 7 days of exercise per week with 2
                //hour duration.
                dailyWaterLitersAdjusted += (waterAmountPer30MinutesExercise * 28) / 7;
                break;
            default:
                //Do nothing
                break;
        }
        
        return dailyWaterLitersAdjusted;
    }
}
