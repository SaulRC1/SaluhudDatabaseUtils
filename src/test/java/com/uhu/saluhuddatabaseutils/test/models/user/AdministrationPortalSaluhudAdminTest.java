package com.uhu.saluhuddatabaseutils.test.models.user;

import com.uhu.saluhuddatabaseutils.datasource.SaluhudAdministrationPortalDataSourceConfig;
import com.uhu.saluhuddatabaseutils.models.user.SaluhudUser;
import com.uhu.saluhuddatabaseutils.security.PasswordEncryptionService;
import com.uhu.saluhuddatabaseutils.services.administrationportal.user.AdministrationPortalSaluhudUserService;
import com.uhu.saluhuddatabaseutils.test.configuration.BaseAdministrationPortalJpaTest;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
public class AdministrationPortalSaluhudAdminTest extends BaseAdministrationPortalJpaTest {

    @Autowired
    private AdministrationPortalSaluhudUserService saluhudUserService;
    
    @Autowired
    private PasswordEncryptionService passwordEncryptionService;
    
    @Test
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    @Rollback
    public void testUserCRUD() {

        SaluhudUser admin = new SaluhudUser();
        admin.setName("Juan Alberto");
        admin.setEmail("correoDePrueba@gmail.com");
        admin.setUsername("Juan2k");

        String encryptedPassword = passwordEncryptionService.encryptPassword("Testpassword9#");      
        admin.setPassword(encryptedPassword);
        
        SaluhudUser admin2 = new SaluhudUser();
        admin2.setName("Saul");
        admin2.setEmail("correoDePrueba2@gmail.com");
        admin2.setUsername("SaulRC1");
        
        String encryptedPassword2 = passwordEncryptionService.encryptPassword("Testpassword8#");      
        admin2.setPassword(encryptedPassword2);

        saluhudUserService.saveUser(admin);      
        saluhudUserService.saveUser(admin2);
        saluhudUserService.deleteUser(admin2);
        admin.setUsername("Juan2k2");
        saluhudUserService.updateUser(admin);

        List<SaluhudUser> users = this.saluhudUserService.findAllUsers();
        Assert.isTrue(users.contains(admin), "");
    }
}
