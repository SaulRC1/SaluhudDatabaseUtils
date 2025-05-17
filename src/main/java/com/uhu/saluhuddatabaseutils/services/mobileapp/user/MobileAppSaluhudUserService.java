package com.uhu.saluhuddatabaseutils.services.mobileapp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uhu.saluhuddatabaseutils.models.user.SaluhudUser;
import com.uhu.saluhuddatabaseutils.models.user.SaluhudUserFitnessData;
import com.uhu.saluhuddatabaseutils.security.PasswordEncryptionService;
import jakarta.validation.Valid;
import java.util.List;
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
    
    /**
     * Registers a new {@link SaluhudUser} into the database. This method will assume
     * that the user's password is not encrypted and thus encrypt it before persisting
     * it into the database.
     * 
     * @param user The user to be registered.
     * @return The registered user.
     */
    @Transactional(transactionManager = "saluhudMobileAppTransactionManager")
    public SaluhudUser registerSaluhudUser(@Valid SaluhudUser user)
    { 
        //Encrypt user's raw password before persisting into the database
        String encryptedPassword = passwordEncryptionService.encryptPassword(user.getPassword());
        
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
        if(phoneNumber == null || phoneNumber.isBlank())
        {
            return false;
        }
        
        return saluhudUserRepository.existsByPhoneNumber(phoneNumber);
    }
    
    public Optional<SaluhudUser> findByUsername(String username)
    {
        return saluhudUserRepository.findByUsername(username);
    }
    
    public Optional<SaluhudUserFitnessData> getSaluhudUserFitnessData(String username)
    {
        Optional<SaluhudUser> saluhudUserOptional = this.saluhudUserRepository.findByUsername(username);
        
        return saluhudUserOptional.map(SaluhudUser::getFitnessData);
    }
    
    @Transactional(transactionManager = "saluhudMobileAppTransactionManager")
    public SaluhudUser updateSaluhudUser(@Valid SaluhudUser saluhudUser)
    {
        return saluhudUserRepository.save(saluhudUser);
    }
}
