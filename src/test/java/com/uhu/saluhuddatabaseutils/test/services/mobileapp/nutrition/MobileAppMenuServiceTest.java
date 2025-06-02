package com.uhu.saluhuddatabaseutils.test.services.mobileapp.nutrition;

import com.uhu.saluhuddatabaseutils.datasource.SaluhudAdministrationPortalDataSourceConfig;
import com.uhu.saluhuddatabaseutils.datasource.SaluhudMobileAppDataSourceConfig;
import com.uhu.saluhuddatabaseutils.models.nutrition.Menu;
import com.uhu.saluhuddatabaseutils.services.mobileapp.nutrition.MobileAppMenuService;
import com.uhu.saluhuddatabaseutils.test.configuration.GeneralTestConfiguration;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@SpringBootTest(classes =
{
    SaluhudMobileAppDataSourceConfig.class,
    SaluhudAdministrationPortalDataSourceConfig.class,
    GeneralTestConfiguration.class
})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages =
{
    "com.uhu.saluhuddatabaseutils.repositories.mobileapp.nutrition",
    "com.uhu.saluhuddatabaseutils.services.mobileapp.nutrition",
    "com.uhu.saluhuddatabaseutils.services.mobileapp.user",
    "com.uhu.saluhuddatabaseutils.security",
    "com.uhu.saluhuddatabaseutils.localization"
})
public class MobileAppMenuServiceTest 
{
    @Autowired
    private MobileAppMenuService mobileAppMenuService;
    
    @Test
    @Transactional(transactionManager = "saluhudMobileAppTransactionManager") 
    @Rollback
    public void test_findByUsername()
    {
        List<Menu> menus = mobileAppMenuService.findByUsername("user_test_1");
        
        Assertions.assertFalse(menus.isEmpty());
        
        boolean containsMenu1 = false;
        boolean containsMenu2 = false;
        
        for (Menu menu : menus)
        {
            if(menu.getName().equals("Menu 1"))
            {
                containsMenu1 = true;
            }
            
            if(menu.getName().equals("Menu 2"))
            {
                containsMenu2 = true;
            }
        }
        
        Assertions.assertTrue(containsMenu1);
        Assertions.assertTrue(containsMenu2);
    }
}
