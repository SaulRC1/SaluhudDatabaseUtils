package com.uhu.saluhuddatabaseutils.test.security;

import com.uhu.saluhuddatabaseutils.datasource.SaluhudAdministrationPortalDataSourceConfig;
import com.uhu.saluhuddatabaseutils.security.PasswordEncryptionService;
import com.uhu.saluhuddatabaseutils.test.configuration.BaseTest;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

/**
 *
 * @author juald
 */
public class PasswordEncryptionServiceTest extends BaseTest
{
    @Autowired
    private PasswordEncryptionService passwordEncryptionService;

    @Test
    public void testEncryptPassword() {
        // Contraseña sin codificar
        String rawPassword = "adminUHU495";

        // Codificamos la contraseña
        String encodedPassword = passwordEncryptionService.encryptPassword(rawPassword);

        // Imprimimos la contraseña codificada (para referencia)
        System.out.println("Contraseña codificada: " + encodedPassword);

        // Verificamos que la contraseña codificada no sea igual a la contraseña sin codificar
        assertTrue(!encodedPassword.equals(rawPassword), "La contraseña codificada no debe ser igual a la original");
    }

    @Test
    public void testMatchPassword() {
        // Contraseña sin codificar y codificada
        String rawPassword = "adminUHU495";
        String encodedPassword = passwordEncryptionService.encryptPassword(rawPassword);

        // Verificamos si la contraseña sin codificar coincide con la codificada
        assertTrue(passwordEncryptionService.matchPassword(rawPassword, encodedPassword), "Las contraseñas deben coincidir");
    }
}
