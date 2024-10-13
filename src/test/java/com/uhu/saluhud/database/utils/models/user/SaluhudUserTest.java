package com.uhu.saluhud.database.utils.models.user;

import com.uhu.saluhud.database.utils.datasource.SaluhudAdminDataSourceConfig;
import com.uhu.saluhud.database.utils.services.saluhud.admin.user.SaluhudAdminUserFitnessDataService;
import com.uhu.saluhud.database.utils.services.saluhud.admin.user.SaluhudAdminUserService;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
@DataJpaTest
@TestPropertySource(locations = {"classpath:datasources/saluhud-admin-datasource.properties"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = "com.uhu.saluhud.database.utils.services.saluhud.admin.user")
@ContextConfiguration(classes = SaluhudAdminDataSourceConfig.class)
public class SaluhudUserTest {

    @Autowired
    private SaluhudAdminUserService saluhudUserService;

    @Autowired
    private SaluhudAdminUserFitnessDataService saluhudUserFitnessDataService;
    
    @Test
    @Transactional(transactionManager = "saluhudAdminTransactionManager")
    @Rollback
    public void testUserCRUD() {

        SaluhudUserFitnessData userFitnessData = new SaluhudUserFitnessData(90, 170, "Men", 22, "Hectomorfo", 2, 8, 10000, 2100, "10%");
        SaluhudUser user = new SaluhudUser("SaulRC1", "1235Password%%", "saul@gmail.com", "Saul", "Rodriguez", "+34 12345678", userFitnessData);
        user.setPassword(user.getRawPassword());
        SaluhudUser user2 = new SaluhudUser("Juan2k", "1235Password%%", "juan@gmail.com", "Juan");
        user2.setPassword(user2.getRawPassword());

        saluhudUserService.saveUser(user);
        saluhudUserFitnessDataService.saveFitnessData(userFitnessData);
        
        saluhudUserService.saveUser(user2);
        saluhudUserService.deleteUser(user2);

        List<SaluhudUser> users = this.saluhudUserService.findAllUsers();
        Assert.isTrue(users.contains(user), "");
    }
}
