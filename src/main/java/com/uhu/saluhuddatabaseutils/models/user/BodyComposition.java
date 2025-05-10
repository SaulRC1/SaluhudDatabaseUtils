package com.uhu.saluhuddatabaseutils.models.user;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@Embeddable
public class BodyComposition 
{
    @Column(name = "lean_body_mass_percentage")
    private float leanBodyMassPercentage;
    
    @Column(name = "body_fat_percentage")
    private float bodyFatPercentage;
    
    @Column(name = "body_fat_weight")
    private float bodyFatWeight;
    
    @Column(name = "lean_body_mass_weight")
    private float leanBodyMassWeight;

    public BodyComposition()
    {
    }

    public BodyComposition(float leanBodyMassPercentage, float bodyFatPercentage, float bodyFatWeight, float leanBodyMassWeight)
    {
        this.leanBodyMassPercentage = leanBodyMassPercentage;
        this.bodyFatPercentage = bodyFatPercentage;
        this.bodyFatWeight = bodyFatWeight;
        this.leanBodyMassWeight = leanBodyMassWeight;
    }

    public float getLeanBodyMassPercentage()
    {
        return leanBodyMassPercentage;
    }

    public void setLeanBodyMassPercentage(float leanBodyMassPercentage)
    {
        this.leanBodyMassPercentage = leanBodyMassPercentage;
    }

    public float getBodyFatPercentage()
    {
        return bodyFatPercentage;
    }

    public void setBodyFatPercentage(float bodyFatPercentage)
    {
        this.bodyFatPercentage = bodyFatPercentage;
    }

    public float getBodyFatWeight()
    {
        return bodyFatWeight;
    }

    public void setBodyFatWeight(float bodyFatWeight)
    {
        this.bodyFatWeight = bodyFatWeight;
    }

    public float getLeanBodyMassWeight()
    {
        return leanBodyMassWeight;
    }

    public void setLeanBodyMassWeight(float leanBodyMassWeight)
    {
        this.leanBodyMassWeight = leanBodyMassWeight;
    }    

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 23 * hash + Float.floatToIntBits(this.leanBodyMassPercentage);
        hash = 23 * hash + Float.floatToIntBits(this.bodyFatPercentage);
        hash = 23 * hash + Float.floatToIntBits(this.bodyFatWeight);
        hash = 23 * hash + Float.floatToIntBits(this.leanBodyMassWeight);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final BodyComposition other = (BodyComposition) obj;
        if (Float.floatToIntBits(this.leanBodyMassPercentage) != Float.floatToIntBits(other.leanBodyMassPercentage))
        {
            return false;
        }
        if (Float.floatToIntBits(this.bodyFatPercentage) != Float.floatToIntBits(other.bodyFatPercentage))
        {
            return false;
        }
        if (Float.floatToIntBits(this.bodyFatWeight) != Float.floatToIntBits(other.bodyFatWeight))
        {
            return false;
        }
        return Float.floatToIntBits(this.leanBodyMassWeight) == Float.floatToIntBits(other.leanBodyMassWeight);
    }
    
}
