package com.uhu.saluhuddatabaseutils.models.nutrition;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
public enum WeekDayEnum 
{
    MONDAY("MONDAY"),
    TUESDAY("TUESDAY"),
    WEDNESDAY("WEDNESDAY"),
    THURSDAY("THURSDAY"),
    FRIDAY("FRIDAY"),
    SATURDAY("SATURDAY"),
    SUNDAY("SUNDAY");
    
    private final String weekDayName;

    private WeekDayEnum(String weekDayName)
    {
        this.weekDayName = weekDayName;
    }

    public String getWeekDayName()
    {
        return weekDayName;
    }
    
    public static WeekDayEnum fromWeekDayName(String weekDayName)
    {
        for (WeekDayEnum weekDay : WeekDayEnum.values())
        {
            if(weekDay.getWeekDayName().equals(weekDayName))
            {
                return weekDay;
            }
        }
        
        return null;
    }
}
