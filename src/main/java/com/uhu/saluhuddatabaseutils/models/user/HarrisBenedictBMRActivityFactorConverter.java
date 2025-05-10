package com.uhu.saluhuddatabaseutils.models.user;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@Converter(autoApply = false)
public class HarrisBenedictBMRActivityFactorConverter implements AttributeConverter<HarrisBenedictBMRActivityFactor, Float>
{

    @Override
    public Float convertToDatabaseColumn(HarrisBenedictBMRActivityFactor activityFactor)
    {
        return activityFactor != null ? activityFactor.getActivityFactorValue() : null;
    }

    @Override
    public HarrisBenedictBMRActivityFactor convertToEntityAttribute(Float activityFactorValue)
    {
        return activityFactorValue != null ? HarrisBenedictBMRActivityFactor.fromActivityFactorValue(activityFactorValue) : null;
    }

}
