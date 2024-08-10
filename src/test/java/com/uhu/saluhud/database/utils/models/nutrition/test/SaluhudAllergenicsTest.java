package com.uhu.saluhud.database.utils.models.nutrition.test;

import com.uhu.saluhud.database.utils.models.nutrition.Allergenic;
import com.uhu.saluhud.database.utils.services.saluhud.admin.nutrition.AllergenicService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
public class SaluhudAllergenicsTest {

    @Autowired
    private AllergenicService allergenicService;

    @Test
    public void testAllergenicCRUD() {
        Allergenic pescado = new Allergenic("Pescado");
        Allergenic leche = new Allergenic("Leche");

        allergenicService.saveAllergenic(pescado);
        allergenicService.saveAllergenic(leche);
        allergenicService.deleteAllergenic(leche);

    }
}
