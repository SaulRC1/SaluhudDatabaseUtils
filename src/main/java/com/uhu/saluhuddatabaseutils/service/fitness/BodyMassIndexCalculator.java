package com.uhu.saluhuddatabaseutils.service.fitness;

import org.springframework.stereotype.Service;

/**
 * Calculator for BMI (Body Mass Index) using the standard formula [weight (kg) / (height (meters))^2] 
 * developed by Adolphe Quetelet.
 * 
 * @author Saúl Rodríguez Naranjo
 */
@Service
public class BodyMassIndexCalculator 
{
    /**
     * Calculates the BMI (Body Mass Index) using the standard formula developed
     * by Adolphe Quetelet.
     * 
     * @param weight The person's weight in kilograms (kg).
     * @param height The person's height in meters (m).
     * @return The BMI expressed in kg/m^2 as intended by the International System of Units.
     */
    public double calculateBodyMassIndex(double weight, double height)
    {
        return (weight / Math.pow(height, 2));
    }
}
