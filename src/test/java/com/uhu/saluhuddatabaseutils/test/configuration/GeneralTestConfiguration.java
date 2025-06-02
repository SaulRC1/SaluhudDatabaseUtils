package com.uhu.saluhuddatabaseutils.test.configuration;

import com.uhu.saluhuddatabaseutils.localization.NutritionLocaleProvider;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@TestConfiguration
public class GeneralTestConfiguration 
{
    @Bean
    public NutritionLocaleProvider nutritionLocaleProvider() throws Exception {
        return new NutritionLocaleProvider(System.getenv("SALUHUD_NUTRITION_TRANSLATIONS"));
    }
}
