package com.uhu.saluhuddatabaseutils.models.user;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
public enum FitnessTargetEnum 
{
    WEIGHT_LOSS("WEIGHT_LOSS"),
    WEIGHT_GAIN("WEIGHT_GAIN"),
    MAINTENANCE("MAINTENANCE");
    
    private String fitnessTargetName;
    
    private FitnessTargetEnum(String fitnessTargetName)
    {
        this.fitnessTargetName = fitnessTargetName;
    }

    public String getFitnessTargetName()
    {
        return fitnessTargetName;
    }
    
    public static FitnessTargetEnum fromFitnessTargetName(String fitnessTargetName)
    {
        FitnessTargetEnum[] fitnessTargets = FitnessTargetEnum.values();
        
        for (FitnessTargetEnum fitnessTarget : fitnessTargets)
        {
            if(fitnessTarget.getFitnessTargetName().equals(fitnessTargetName))
            {
                return fitnessTarget;
            }
        }
        
        return null;
    }
}