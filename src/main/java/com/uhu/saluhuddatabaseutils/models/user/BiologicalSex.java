package com.uhu.saluhuddatabaseutils.models.user;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
public enum BiologicalSex
{
    MALE("MALE"),
    FEMALE("FEMALE");
    
    private final String sexName;
    
    private BiologicalSex(String sexName)
    {
        this.sexName = sexName;
    }

    public String getSexName()
    {
        return sexName;
    }
    
    public static BiologicalSex fromSexName(String sexName)
    {
        switch(sexName)
        {
            case "MALE":
                return MALE;
            case "FEMALE":
                return FEMALE;
            default:
                return null;
        }
    }
}
