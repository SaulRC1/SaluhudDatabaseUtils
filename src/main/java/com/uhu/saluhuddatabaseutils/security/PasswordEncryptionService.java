package com.uhu.saluhuddatabaseutils.security;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Service for encrypting passwords using a {@link PasswordEncoder}, defaulting
 * to {@link BCryptPasswordEncoder}. This service provides methods for password
 * encryption and password matching.
 *
 * @author Saúl Rodríguez Naranjo
 */
@Service
public class PasswordEncryptionService
{

    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor that initializes the password encoder. By default, this
     * service uses a BCryptPasswordEncoder.
     *
     * @param passwordEncoder the PasswordEncoder implementation to be used for
     * encrypting passwords
     */
    public PasswordEncryptionService(@Qualifier("bcryptPasswordEncoder") PasswordEncoder passwordEncoder)
    {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Encrypts the given password using the configured PasswordEncoder.
     *
     * @param password the plain text password to be encrypted
     * @return the encrypted password
     */
    public String encryptPassword(String password)
    {
        return this.passwordEncoder.encode(password);
    }

    /**
     * Compares a raw password with an encrypted password to check if they
     * match.
     *
     * @param rawPassword the plain text password to be checked
     * @param encryptedPassword the encrypted password to be compared with
     * @return true if the raw password matches the encrypted password, false
     * otherwise
     */
    public boolean matchPassword(String rawPassword, String encryptedPassword)
    {
        return passwordEncoder.matches(rawPassword, encryptedPassword);
    }
}
