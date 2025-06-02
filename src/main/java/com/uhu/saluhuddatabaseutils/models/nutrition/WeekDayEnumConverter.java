package com.uhu.saluhuddatabaseutils.models.nutrition;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@Converter(autoApply = false)
public class WeekDayEnumConverter implements AttributeConverter<WeekDayEnum, String>
{

    @Override
    public String convertToDatabaseColumn(WeekDayEnum weekDay)
    {
        return weekDay != null ? weekDay.getWeekDayName() : null;
    }

    @Override
    public WeekDayEnum convertToEntityAttribute(String weekDayName)
    {
        return weekDayName != null ? WeekDayEnum.fromWeekDayName(weekDayName) : null;
    }

}