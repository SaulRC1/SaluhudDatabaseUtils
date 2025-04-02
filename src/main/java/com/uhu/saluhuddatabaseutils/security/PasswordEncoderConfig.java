package com.uhu.saluhuddatabaseutils.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

/**
 * Configuration class for defining and configuring 
 *
 * @author Saúl Rodríguez Naranjo
 */
@Configuration
public class PasswordEncoderConfig
{

    @Bean
    @Primary
    public PasswordEncoder bcryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PasswordEncoder pbkdf2PasswordEncoder()
    {
        return new Pbkdf2PasswordEncoder("pepper", 16, 310000, Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);
    }

}
