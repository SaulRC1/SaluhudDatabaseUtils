package com.uhu.saluhuddatabaseutils.test.configuration;

import com.uhu.saluhuddatabaseutils.datasource.SaluhudAdministrationPortalDataSourceConfig;
import com.uhu.saluhuddatabaseutils.datasource.SaluhudMobileAppDataSourceConfig;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

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
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ComponentScan(basePackages =
{
    "com.uhu.saluhuddatabaseutils.repositories.mobileapp.nutrition",
    "com.uhu.saluhuddatabaseutils.repositories.mobileapp.user",
    "com.uhu.saluhuddatabaseutils.services.mobileapp.nutrition",
    "com.uhu.saluhuddatabaseutils.services.mobileapp.user",
    "com.uhu.saluhuddatabaseutils.repositories.administrationportal.nutrition",
    "com.uhu.saluhuddatabaseutils.repositories.administrationportal.user",
    "com.uhu.saluhuddatabaseutils.repositories.administrationportal.security",
    "com.uhu.saluhuddatabaseutils.services.administrationportal.nutrition",
    "com.uhu.saluhuddatabaseutils.services.administrationportal.user",
    "com.uhu.saluhuddatabaseutils.services.administrationportal.security",
    "com.uhu.saluhuddatabaseutils.localization",
    "com.uhu.saluhuddatabaseutils.service",
    "com.uhu.saluhuddatabaseutils.security"
})
public abstract class BaseTest {

}
