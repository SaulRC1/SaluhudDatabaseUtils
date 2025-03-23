package com.uhu.saluhuddatabaseutils.test.repositories.mobileapp.user;

import com.uhu.saluhuddatabaseutils.datasource.SaluhudMobileAppDataSourceConfig;
import com.uhu.saluhuddatabaseutils.models.user.SaluhudUser;
import com.uhu.saluhuddatabaseutils.security.PasswordEncryptionService;
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
import com.uhu.saluhuddatabaseutils.repositories.mobileapp.user.MobileAppSaluhudUserRepository;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@DataJpaTest
@TestPropertySource(locations = {"classpath:datasources/saluhud-mobile-app-datasource.properties"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = {
    "com.uhu.saluhuddatabaseutils.repositories.mobileapp.user",
    "com.uhu.saluhuddatabaseutils.security"
})
@ContextConfiguration(classes = SaluhudMobileAppDataSourceConfig.class)
public class MobileAppSaluhudUserRepositoryTest 
{
    private static Logger logger = Logger.getLogger(MobileAppSaluhudUserRepositoryTest.class.getName());
    
    @Autowired
    private MobileAppSaluhudUserRepository mobileAppSaluhudUserRepository;
    
    @Autowired
    private PasswordEncryptionService passwordEncryptionService;
    
    @Test
    @Transactional(transactionManager = "saluhudMobileAppTransactionManager")
    @Rollback
    public void test_saving_saluhud_user_with_valid_data()
    {
        SaluhudUser saluhudUser = new SaluhudUser("FitnessUserTest", "Testpassword9#", "test_user@gmail.com", "Fitness", "User", "+34 677868989");
        
        String encryptedPassword = passwordEncryptionService.encryptPassword(saluhudUser.getPassword());
        
        saluhudUser.setPassword(encryptedPassword);
        
        logger.log(Logger.Level.INFO, "Encrypted password: " + encryptedPassword);
        
        mobileAppSaluhudUserRepository.save(saluhudUser);
    }
}
