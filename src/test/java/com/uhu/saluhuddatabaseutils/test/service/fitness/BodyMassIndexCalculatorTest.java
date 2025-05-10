package com.uhu.saluhuddatabaseutils.test.service.fitness;

import com.uhu.saluhuddatabaseutils.service.fitness.BodyMassIndexCalculator;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
public class BodyMassIndexCalculatorTest 
{
    private BodyMassIndexCalculator bodyMassIndexCalculator = new BodyMassIndexCalculator();
    
    @Test
    public void calculateBodyMassIndex_returns_correct_value()
    {
        double bmi = bodyMassIndexCalculator.calculateBodyMassIndex(70, 1.75);
        
        BigDecimal bmiRounded = new BigDecimal(bmi).setScale(1, RoundingMode.HALF_UP);
        
        Assertions.assertEquals(22.9, bmiRounded.doubleValue());
    }
}
