/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.uhu.saluhuddatabaseutils.test.configuration;

import com.uhu.saluhuddatabaseutils.datasource.SaluhudMobileAppDataSourceConfig;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@DataJpaTest
@TestPropertySource(locations = {"classpath:datasources/saluhud-mobile-app-datasource.properties"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = {
    "com.uhu.saluhuddatabaseutils.repositories.mobileapp.user",
    "com.uhu.saluhuddatabaseutils.security"
})
@ContextConfiguration(classes = SaluhudMobileAppDataSourceConfig.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseMobileAppJpaTest {

}
