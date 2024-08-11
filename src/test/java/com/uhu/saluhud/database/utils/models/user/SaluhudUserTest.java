package com.uhu.saluhud.database.utils.models.user;

import com.uhu.saluhud.database.utils.services.saluhud.admin.user.SaluhudAdminUserFitnessDataService;
import com.uhu.saluhud.database.utils.services.saluhud.admin.user.SaluhudAdminUserPersonalDataService;
import com.uhu.saluhud.database.utils.services.saluhud.admin.user.SaluhudAdminUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
public class SaluhudUserTest {

    @Autowired
    private SaluhudAdminUserService saluhudUserService;

    @Autowired
    private SaluhudAdminUserFitnessDataService saluhudUserFitnessDataService;

    @Autowired
    private SaluhudAdminUserPersonalDataService saluhudPersonalDataService;

    @Test
    public void testUserCRUD() {

        SaluhudUserPersonalData userPersonalData = new SaluhudUserPersonalData("Saul", "Rodríguez", 123456789);
        SaluhudUserFitnessData userFitnessData = new SaluhudUserFitnessData(90, 170, "Men", 22, "Hectomorfo", 2, 8, 10000, 2100, "10%");
        SaluhudUser user = new SaluhudUser("SaulRC1", "1235", "saul@gmail.com", userPersonalData, userFitnessData);
        SaluhudUser user2 = new SaluhudUser("Juan2k", "1235", "juan@gmail.com");

        saluhudPersonalDataService.savePersonalData(userPersonalData);
        saluhudUserFitnessDataService.saveFitnessData(userFitnessData);
        saluhudUserService.saveUser(user);
        saluhudUserService.saveUser(user2);
        saluhudUserService.deleteUser(saluhudUserService.getUserById(2));
    }
}
