package com.uhu.saluhuddatabaseutils.models.user;

/**
 * Harris-Benedict BMR (Basal Metabolic Rate) formula's activity factors. These
 * are used alongside BMR to calculate the daily kilocalorie expenditure of a person.
 * 
 * @author Saúl Rodríguez Naranjo
 */
public enum HarrisBenedictBMRActivityFactor 
{
    /**
     * Sedentary, little to no exercise.
     */
    SEDENTARY(1.2f),
    
    /**
     * Light exercise. Sports 1-3 days a week.
     */
    LIGHTLY_ACTIVE(1.375f),
    
    /**
     * Moderate exercise. Sports 3-5 days a week.
     */
    MODERATELY_ACTIVE(1.55f),
    
    /**
     * Hard exercise. Sports 6-7 days a week.
     */
    VERY_ACTIVE(1.725f),
    
    /**
     * Very hard exercise. Sports and a physical job.
     */
    EXTRA_ACTIVE(1.9f),
    
    /**
     * Professional athlete training.
     */
    PROFESSIONAL_ATHLETE(2.3f);
    
    private float activityFactorValue;

    private HarrisBenedictBMRActivityFactor(float activityFactorValue)
    {
        this.activityFactorValue = activityFactorValue;
    }

    public float getActivityFactorValue()
    {
        return activityFactorValue;
    }
    
    public static HarrisBenedictBMRActivityFactor fromActivityFactorValue(float value)
    {
        HarrisBenedictBMRActivityFactor[] activityFactors = HarrisBenedictBMRActivityFactor.values();
        
        for (HarrisBenedictBMRActivityFactor activityFactor : activityFactors)
        {
            if(activityFactor.getActivityFactorValue() == value)
            {
                return activityFactor;
            }
        }
        
        return null;
    }
}
