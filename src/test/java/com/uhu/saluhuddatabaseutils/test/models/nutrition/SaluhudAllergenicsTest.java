package com.uhu.saluhuddatabaseutils.test.models.nutrition;

import com.uhu.saluhuddatabaseutils.models.nutrition.Allergenic;
import com.uhu.saluhuddatabaseutils.services.administrationportal.nutrition.AdministrationPortalAllergenicService;
import com.uhu.saluhuddatabaseutils.test.configuration.BaseAdministrationPortalJpaTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
public class SaluhudAllergenicsTest extends BaseAdministrationPortalJpaTest {

    @Autowired
    private AdministrationPortalAllergenicService allergenicService;

    @Test
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    @Rollback
    public void testAllergenicCRUD() {
        Allergenic pescado = new Allergenic("Pescado");
        Allergenic leche = new Allergenic("Leche");

        allergenicService.saveAllergenic(pescado);
        allergenicService.saveAllergenic(leche);
        leche.setName("leche de vaca");
        allergenicService.updateAllergenic(leche);
        
        Assert.isTrue(this.allergenicService.findAllAllergenics().contains(pescado), "");
        Assert.isTrue(this.allergenicService.findById(leche.getId()).getName().equalsIgnoreCase("leche de vaca"), "");

    }
}
