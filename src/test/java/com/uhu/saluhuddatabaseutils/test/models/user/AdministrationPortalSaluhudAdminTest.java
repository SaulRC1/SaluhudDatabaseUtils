package com.uhu.saluhuddatabaseutils.test.models.user;

import com.uhu.saluhuddatabaseutils.datasource.SaluhudAdministrationPortalDataSourceConfig;
import com.uhu.saluhuddatabaseutils.models.security.Authority;
import com.uhu.saluhuddatabaseutils.models.security.UserAccount;
import com.uhu.saluhuddatabaseutils.models.user.SaluhudAdmin;
import com.uhu.saluhuddatabaseutils.security.PasswordEncryptionService;
import com.uhu.saluhuddatabaseutils.services.administrationportal.user.AdministrationPortalSaluhudAdminService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
@TestPropertySource(locations = {"classpath:datasources/saluhud-administration-portal-datasource.properties"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = {
    "com.uhu.saluhud.database.utils.services.saluhud.admin.user",
    "com.uhu.saluhud.database.utils.security"
})
@ContextConfiguration(classes = SaluhudAdministrationPortalDataSourceConfig.class)
public class AdministrationPortalSaluhudAdminTest {

    @Autowired
    private AdministrationPortalSaluhudAdminService saluhudAdminService;
    
    @Autowired
    private PasswordEncryptionService passwordEncryptionService;
    
    @Test
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    @Rollback
    public void testUserCRUD() {
        
        Collection<Authority> authorities = new ArrayList<>();
        Authority auth = new Authority();
        auth.setAuthority("ADMIN");
        
        UserAccount cuenta1 = new UserAccount("SaulRC1", "1235Password%%", authorities);
        cuenta1.setAuthorities(authorities);
        
        UserAccount cuenta2 = new UserAccount("Juan2k", "1235Password%%", authorities);
        cuenta2.setAuthorities(authorities);
        

        SaluhudAdmin admin = new SaluhudAdmin();
        admin.setName("Juan Alberto");
        String encryptedPassword = passwordEncryptionService.encryptPassword(cuenta1.getRawPassword());
        cuenta1.setPassword(encryptedPassword);
        admin.setUserAccount(cuenta1);
        
        SaluhudAdmin admin2 = new SaluhudAdmin();
        admin2.setName("Saul");
        String encryptedPassword2 = passwordEncryptionService.encryptPassword(cuenta2.getRawPassword());
        cuenta2.setPassword(encryptedPassword2);
        admin2.setUserAccount(cuenta2);

        saluhudAdminService.saveUser(admin);
        
        saluhudAdminService.saveUser(admin2);
        saluhudAdminService.deleteUser(admin2);

        List<SaluhudAdmin> users = this.saluhudAdminService.findAllUsers();
        Assert.isTrue(users.contains(admin), "");
    }
}
