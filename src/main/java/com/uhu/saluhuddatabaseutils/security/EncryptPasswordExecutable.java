package com.uhu.saluhuddatabaseutils.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author SaulRC1
 */
public class EncryptPasswordExecutable
{
    private static PasswordEncryptionService passwordEncryptionService = new PasswordEncryptionService(new BCryptPasswordEncoder());

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        System.out.println(passwordEncryptionService.encryptPassword("User_test_4%"));
    }
    
}
