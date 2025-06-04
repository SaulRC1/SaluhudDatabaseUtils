package com.uhu.saluhuddatabaseutils.models.nutrition;

import java.time.DayOfWeek;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;

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
    
    public static WeekDayEnum fromDayOfWeek(DayOfWeek dayOfWeek)
    {
        switch(dayOfWeek)
        {
            case MONDAY:
                return WeekDayEnum.MONDAY;
            case TUESDAY:
                return WeekDayEnum.TUESDAY;
            case WEDNESDAY:
                return WeekDayEnum.WEDNESDAY;
            case THURSDAY:
                return WeekDayEnum.THURSDAY;
            case FRIDAY:
                return WeekDayEnum.FRIDAY;
            case SATURDAY:
                return WeekDayEnum.SATURDAY;
            case SUNDAY:
                return WeekDayEnum.SUNDAY;
            default:
                return null;
        }
    }
}
