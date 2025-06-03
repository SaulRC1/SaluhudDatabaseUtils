package com.uhu.saluhuddatabaseutils.models.user;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@Converter(autoApply = false)
public class FitnessTargetEnumConverter implements AttributeConverter<FitnessTargetEnum, String>
{

    @Override
    public String convertToDatabaseColumn(FitnessTargetEnum fitnessTarget)
    {
        return fitnessTarget != null ? fitnessTarget.getFitnessTargetName() : null;
    }

    @Override
    public FitnessTargetEnum convertToEntityAttribute(String fitnessTargetName)
    {
        return fitnessTargetName != null ? FitnessTargetEnum.fromFitnessTargetName(fitnessTargetName) : null;
    }

}
