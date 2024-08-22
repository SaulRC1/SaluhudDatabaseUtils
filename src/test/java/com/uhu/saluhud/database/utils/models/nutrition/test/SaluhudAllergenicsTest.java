package com.uhu.saluhud.database.utils.models.nutrition.test;

import com.uhu.saluhud.database.utils.datasource.SaluhudAdminDataSourceConfig;
import com.uhu.saluhud.database.utils.models.nutrition.Allergenic;
import com.uhu.saluhud.database.utils.services.saluhud.admin.nutrition.SaluhudAdminAllergenicService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.Assert;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
@DataJpaTest
@TestPropertySource(locations = { "classpath:datasources/saluhud-admin-datasource.properties" })
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ComponentScan(basePackages = "com.uhu.saluhud.database.utils.services.saluhud.admin.nutrition")
@ContextConfiguration(classes = SaluhudAdminDataSourceConfig.class)
public class SaluhudAllergenicsTest {

    @Autowired
    private SaluhudAdminAllergenicService allergenicService;

    @Test
    public void testAllergenicCRUD() {
        Allergenic pescado = new Allergenic("Pescado");
        Allergenic leche = new Allergenic("Leche");

        allergenicService.saveAllergenic(pescado);
        allergenicService.saveAllergenic(leche);
        allergenicService.deleteAllergenic(leche);
        
        Assert.isTrue(this.allergenicService.findAllAllergenics().contains(pescado), "");
        Assert.isTrue(!this.allergenicService.findAllAllergenics().contains(leche), "");

    }
}
