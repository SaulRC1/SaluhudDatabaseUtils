package com.uhu.saluhuddatabaseutils.service.fitness;

import org.springframework.stereotype.Service;

/**
 * Sleep hours calculator based on the Sleep Foundation recommendation and guidelines.
 * 
 * @author Saúl Rodríguez Naranjo
 */
@Service
public class SleepHoursCalculator 
{
    /**
     * Calculates sleep hours based on the person's age.
     * 
     * @param age The person's age in years.
     * @return sleep hours recommended for this person.
     */
    public int calculateSleepHours(int age)
    {
        if(age < 1)
        {
            return 16;
        }
        
        if(age >= 1 && age <= 2)
        {
            return 14;
        }
        
        if(age >= 3 && age <= 5)
        {
            return 13;
        }
        
        if(age >= 6 && age <= 12)
        {
            return 12;
        }
        
        if(age >= 13 && age <= 18)
        {
            return 10;
        }
        
        return 8;
    }
}
