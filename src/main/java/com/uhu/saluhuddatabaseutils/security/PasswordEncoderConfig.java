package com.uhu.saluhuddatabaseutils.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

/**
 * Configuration class for defining and configuring different
 * {@link PasswordEncoder} beans. This class provides beans for BCrypt and
 * PBKDF2 password encoding algorithms.
 *
 * @author Saúl Rodríguez Naranjo
 */
@Configuration
public class PasswordEncoderConfig
{

    /**
     * Bean definition for {@link BCryptPasswordEncoder}. This encoder uses the
     * BCrypt hashing algorithm to encode passwords. It is marked as the primary
     * bean, which means it will be used by default when multiple encoders are
     * available.
     *
     * @return a {@link PasswordEncoder} implementation using the BCrypt
     * algorithm
     */
    @Bean
    @Primary
    public PasswordEncoder bcryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    /**
     * Bean definition for {@link Pbkdf2PasswordEncoder}. This encoder uses the
     * PBKDF2 hashing algorithm with specified parameters for password encoding.
     *
     * @return a {@link PasswordEncoder} implementation using the PBKDF2
     * algorithm
     */
    @Bean
    public PasswordEncoder pbkdf2PasswordEncoder()
    {
        return new Pbkdf2PasswordEncoder("pepper", 16, 310000, Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);
    }

}
