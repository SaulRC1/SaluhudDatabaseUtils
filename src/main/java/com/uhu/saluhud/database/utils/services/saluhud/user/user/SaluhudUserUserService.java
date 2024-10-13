package com.uhu.saluhud.database.utils.services.saluhud.user.user;

import com.uhu.saluhud.database.utils.repositories.saluhud.user.user.SaluhudUserUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uhu.saluhud.database.utils.models.user.SaluhudUser;
import com.uhu.saluhud.database.utils.security.PasswordEncryptionService;
import jakarta.persistence.EntityManagerFactory;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for operations regarding {@link SaluhudUser}.
 * 
 * @author Saúl Rodríguez Naranjo
 */
@Service
//By default all methods will have readonly = true since most methods will be read-only
@Transactional(readOnly = true, transactionManager = "saluhudUserTransactionManager")
public class SaluhudUserUserService 
{
    
    @Autowired
    private SaluhudUserUserRepository saluhudUserRepository;
    
    @Autowired
    private PasswordEncryptionService passwordEncryptionService;
    
    @Transactional(transactionManager = "saluhudUserTransactionManager")
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
    
}
