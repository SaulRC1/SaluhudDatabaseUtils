package com.uhu.saluhud.database.utils.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Service for encrypting passwords using {@link BCryptPasswordEncoder} by default.
 * 
 * @author Saúl Rodríguez Naranjo
 */
@Service
public class PasswordEncryptionService 
{
    
    private final PasswordEncoder passwordEncoder;

    public PasswordEncryptionService(@Qualifier("bcryptPasswordEncoder") PasswordEncoder passwordEncoder)
    {
        this.passwordEncoder = passwordEncoder;
    }
    
    public String encryptPassword(String password)
    {
        return this.passwordEncoder.encode(password);
    }
    
    public boolean matchPassword(String rawPassword, String encryptedPassword)
    {
        return passwordEncoder.matches(rawPassword, encryptedPassword);
    }
}
