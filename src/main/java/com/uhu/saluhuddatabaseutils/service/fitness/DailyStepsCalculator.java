package com.uhu.saluhuddatabaseutils.service.fitness;

import com.uhu.saluhuddatabaseutils.models.user.HarrisBenedictBMRActivityFactor;
import org.springframework.stereotype.Service;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@Service
public class DailyStepsCalculator 
{
    /**
     * Calculates daily steps based on Harris-Benedict's activity factor.
     * 
     * @param activityFactor The person's activity factor.
     * @return Daily steps recommended for this person.
     */
    public int calculateDailySteps(HarrisBenedictBMRActivityFactor activityFactor)
    {
        switch(activityFactor)
        {
            case SEDENTARY:
                return 5000;
            case LIGHTLY_ACTIVE:
                return 7500;
            case MODERATELY_ACTIVE:
                return 10000;
            case VERY_ACTIVE:
                return 12500;
            case EXTRA_ACTIVE:
                return 15000;
            case PROFESSIONAL_ATHLETE:
                return 17500;
            default:
                return 5000;
        }
    }
}
