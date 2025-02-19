package com.uhu.saluhud.database.utils.test.services.saluhud.user.user;

import com.uhu.saluhud.database.utils.datasource.SaluhudAdministrationPortalDataSourceConfig;
import com.uhu.saluhud.database.utils.datasource.SaluhudMobileAppDataSourceConfig;
import com.uhu.saluhud.database.utils.models.user.SaluhudUser;
import com.uhu.saluhud.database.utils.services.saluhud.admin.user.SaluhudAdminUserService;
import com.uhu.saluhud.database.utils.services.saluhud.user.user.SaluhudUserUserService;
import jakarta.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
//@RunWith(SpringRunner.class)
@SpringBootTest(classes =
{
    SaluhudMobileAppDataSourceConfig.class,
    SaluhudAdministrationPortalDataSourceConfig.class
})
/*@TestPropertySource(locations =
{
    "classpath:datasources/saluhud-user-datasource.properties",
    "classpath:datasources/saluhud-admin-datasource.properties"
},
properties =
{
    "logging.level.org.hibernate.SQL=DEBUG",
    "logging.level.org.springframework.transaction=TRACE",
    "logging.level.org.hibernate.transaction=TRACE"
})*/
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages =
{
    "com.uhu.saluhud.database.utils.repositories.saluhud.user.user",
    "com.uhu.saluhud.database.utils.security",
    "com.uhu.saluhud.database.utils.services.saluhud.user.user",
    "com.uhu.saluhud.database.utils.repositories.saluhud.admin.user",
    "com.uhu.saluhud.database.utils.services.saluhud.admin.user"
})
/*@ContextConfiguration(classes = {
    SaluhudMobileAppDataSourceConfig.class,
    SaluhudAdministrationPortalDataSourceConfig.class
})*/
//@Transactional
public class SaluhudUserUserServiceTest
{

    @Autowired
    private SaluhudUserUserService saluhudUserUserService;

    @Autowired
    private SaluhudAdminUserService saluhudAdminUserService;
    
    private final List<SaluhudUser> previouslyPersistedUsers = new ArrayList<>();

    /**
     * Empties the previously executed query persisted data in the EntityManager
     * cache or in the database itself
     */
    /*@AfterEach
    public void deleteTestDataFromDB()
    {
        List<SaluhudUser> allUsers = saluhudAdminUserService.findAllUsers();

        for (SaluhudUser previouslyPersistedUser : previouslyPersistedUsers)
        {
            if(allUsers.contains(previouslyPersistedUser))
            {
                saluhudAdminUserService.deleteUser(previouslyPersistedUser);
            }

            previouslyPersistedUsers.remove(previouslyPersistedUser);
        }
    }*/

    @Test
    //To properly rollback all changes to the database when the test finishes,
    //it is needed to explicitly declare @Transactional in the test case
    @Transactional(transactionManager = "saluhudMobileAppTransactionManager") 
    @Rollback
    public void test_save_saluhud_user_with_valid_data()
    {
        SaluhudUser saluhudUser = new SaluhudUser("FitnessUserTest", "Testpassword9#", "test_user@gmail.com", "Fitness", "User", "+34 677868989");

        saluhudUserUserService.saveUser(saluhudUser);

        //previouslyPersistedUsers.add(saluhudUser);

        Assertions.assertTrue(saluhudUserUserService.findAll().contains(saluhudUser));
    }
    
    /*@Test
    public void test_save_saluhud_user_with_invalid_username()
    {
        SaluhudUser saluhudUser = new SaluhudUser("F", "Testpassword9#", "test_user@gmail.com", "Fitness", "User", "+34 677868989");

        previouslyPersistedUsers.add(saluhudUser);

        saluhudUserUserService.saveUser(saluhudUser);

        Assertions.assertThrows(ConstraintViolationException.class, () ->
        {
            saluhudUserUserService.saveUser(saluhudUser);
        });
    }*/
}
