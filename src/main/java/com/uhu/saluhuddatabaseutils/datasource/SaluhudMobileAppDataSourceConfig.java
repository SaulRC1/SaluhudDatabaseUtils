package com.uhu.saluhuddatabaseutils.datasource;

import com.zaxxer.hikari.HikariDataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@Configuration(proxyBeanMethods = false)
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.uhu.saluhuddatabaseutils.repositories.mobileapp",
        entityManagerFactoryRef = "saluhudMobileAppEntityManagerFactory",
        transactionManagerRef = "saluhudMobileAppTransactionManager"
)
@PropertySource("classpath:datasources/saluhud-mobile-app-datasource.properties")
public class SaluhudMobileAppDataSourceConfig
{
    private static final Logger logger = Logger.getLogger(SaluhudMobileAppDataSourceConfig.class.getName());
    
    @Bean(name = "saluhudMobileAppEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean saluhudMobileAppEntityManagerFactory()
    {
        LocalContainerEntityManagerFactoryBean saluhudMobileAppEntityManagerFactoryBean
                = new LocalContainerEntityManagerFactoryBean();

        saluhudMobileAppEntityManagerFactoryBean.setDataSource(buildSaluhudMobileAppDataSource());
        saluhudMobileAppEntityManagerFactoryBean.setPackagesToScan("com.uhu.saluhuddatabaseutils.models");
        saluhudMobileAppEntityManagerFactoryBean.setPersistenceUnitName("saluhud_mobile_app_persistence_unit");

        JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();

        saluhudMobileAppEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        
        return saluhudMobileAppEntityManagerFactoryBean;
    }

    @Bean(name = "saluhudMobileAppTransactionManager")
    public PlatformTransactionManager saluhudMobileAppTransactionManager(
            @Qualifier("saluhudMobileAppEntityManagerFactory") LocalContainerEntityManagerFactoryBean saluhudMobileAppEntityManagerFactory)
    {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(saluhudMobileAppEntityManagerFactory.getObject());

        return jpaTransactionManager;
    }

    private DataSource buildSaluhudMobileAppDataSource()
    {
        try
        {
            Properties saluhudUserDataSourceProperties = new Properties();
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream stream = loader.getResourceAsStream("datasources/saluhud-mobile-app-datasource.properties");
            saluhudUserDataSourceProperties.load(stream);
            
            String url = (String) saluhudUserDataSourceProperties.get("spring.datasource.url");
            String username = (String) saluhudUserDataSourceProperties.get("spring.datasource.username");
            String password = (String) saluhudUserDataSourceProperties.get("spring.datasource.password");
            String driver = (String) saluhudUserDataSourceProperties.get("spring.datasource.driver-class-name");
            
            logger.log(Level.INFO, "Saluhud Mobile App DataSource URL: {0}", url);
            logger.log(Level.INFO, "Saluhud Mobile App DataSource Username: {0}", username);
            logger.log(Level.INFO, "Saluhud Mobile App DataSource Driver: {0}", driver);
            
            DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create().type(HikariDataSource.class);
            dataSourceBuilder.driverClassName(driver);
            dataSourceBuilder.url(url);
            dataSourceBuilder.username(username);
            dataSourceBuilder.password(password);
            
            return dataSourceBuilder.build();
            
        } catch (IOException ex)
        {
            Logger.getLogger(SaluhudMobileAppDataSourceConfig.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        
        return null;
    }
}
