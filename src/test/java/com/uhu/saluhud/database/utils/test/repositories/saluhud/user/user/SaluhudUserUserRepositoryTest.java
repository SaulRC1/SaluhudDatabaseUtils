package com.uhu.saluhud.database.utils.test.repositories.saluhud.user.user;

import com.uhu.saluhud.database.utils.datasource.SaluhudUserDataSourceConfig;
import com.uhu.saluhud.database.utils.models.user.SaluhudUser;
import com.uhu.saluhud.database.utils.repositories.saluhud.user.user.SaluhudUserUserRepository;
import com.uhu.saluhud.database.utils.security.PasswordEncryptionService;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@DataJpaTest
@TestPropertySource(locations = {"classpath:datasources/saluhud-user-datasource.properties"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = {
    "com.uhu.saluhud.database.utils.repositories.saluhud.user.user",
    "com.uhu.saluhud.database.utils.security"
})
@ContextConfiguration(classes = SaluhudUserDataSourceConfig.class)
public class SaluhudUserUserRepositoryTest 
{
    private static Logger logger = Logger.getLogger(SaluhudUserUserRepositoryTest.class.getName());
    
    @Autowired
    private SaluhudUserUserRepository saluhudUserUserRepository;
    
    @Autowired
    private PasswordEncryptionService passwordEncryptionService;
    
    @Test
    @Transactional(transactionManager = "saluhudUserTransactionManager")
    @Rollback
    public void test_saving_saluhud_user_with_valid_data()
    {
        SaluhudUser saluhudUser = new SaluhudUser("FitnessUserTest", "Testpassword9#", "test_user@gmail.com", "Fitness", "User", "+34 677868989");
        
        String encryptedPassword = passwordEncryptionService.encryptPassword(saluhudUser.getRawPassword());
        
        saluhudUser.setPassword(encryptedPassword);
        
        logger.log(Logger.Level.INFO, "Encrypted password: " + encryptedPassword);
        
        saluhudUserUserRepository.save(saluhudUser);
    }
}
