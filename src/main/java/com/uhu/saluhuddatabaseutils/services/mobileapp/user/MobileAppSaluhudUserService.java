package com.uhu.saluhuddatabaseutils.services.mobileapp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uhu.saluhuddatabaseutils.models.user.SaluhudUser;
import com.uhu.saluhuddatabaseutils.security.PasswordEncryptionService;
import jakarta.persistence.EntityManagerFactory;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import com.uhu.saluhuddatabaseutils.repositories.mobileapp.user.MobileAppSaluhudUserRepository;
import java.util.Optional;

/**
 * Service class for operations regarding {@link SaluhudUser}.
 * 
 * @author Saúl Rodríguez Naranjo
 */
@Service
//By default all methods will have readonly = true since most methods will be read-only
@Transactional(readOnly = true, transactionManager = "saluhudMobileAppTransactionManager")
public class MobileAppSaluhudUserService 
{
    
    @Autowired
    private MobileAppSaluhudUserRepository saluhudUserRepository;
    
    @Autowired
    private PasswordEncryptionService passwordEncryptionService;
    
    @Transactional(transactionManager = "saluhudMobileAppTransactionManager")
    public <S extends SaluhudUser> S saveUser(@Valid S user)
    { 
        //Encrypt user's raw password before persisting into the database
        String encryptedPassword = passwordEncryptionService.encryptPassword(user.getRawPassword());
        
        user.setPassword(encryptedPassword);
        
        return saluhudUserRepository.save(user);
    }
    
    public List<SaluhudUser> findAll()
    {
        return saluhudUserRepository.findAll();
    }
    
    public boolean existsById(long id)
    {
        return saluhudUserRepository.existsById(id);
    }
    
    public boolean existsByUsername(String username)
    {
        return saluhudUserRepository.existsByUsername(username);
    }
    
    public boolean existsByEmailIgnoreCase(String email)
    {
        return saluhudUserRepository.existsByEmailIgnoreCase(email);
    }
    
    public boolean existsByPhoneNumber(String phoneNumber)
    {
        return saluhudUserRepository.existsByPhoneNumber(phoneNumber);
    }
    
    public Optional<SaluhudUser> findByUsername(String username)
    {
        return saluhudUserRepository.findByUsername(username);
    }
}
