package com.uhu.saluhuddatabaseutils.datasource;

import com.zaxxer.hikari.HikariDataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.jdbc.metadata.HikariDataSourcePoolMetadata;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
        basePackages = "com.uhu.saluhuddatabaseutils.repositories.administrationportal",
        entityManagerFactoryRef = "saluhudAdministrationPortalEntityManagerFactory",
        transactionManagerRef = "saluhudAdministrationPortalTransactionManager"
)
@PropertySource("classpath:datasources/saluhud-administration-portal-datasource.properties")
public class SaluhudAdministrationPortalDataSourceConfig
{
    private static Logger logger = Logger.getLogger(SaluhudAdministrationPortalDataSourceConfig.class.getName());
    /*@Primary
    @Bean(name = "saluhudAdminDataSourceProperties")
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties saluhudAdminDataSourceProperties()
    {
        return new DataSourceProperties();
    }
    
    @Primary
    @Bean(name = "saluhudAdminDataSource")
    @ConfigurationProperties("spring.datasource")
    public DataSource saluhudAdminDataSource(DataSourceProperties properties)
    {
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }*/
    @Primary
    @Bean(name = "saluhudAdministrationPortalEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean saluhudAdministrationPortalEntityManagerFactory()
    {
        LocalContainerEntityManagerFactoryBean saluhudAdministrationPortalEntityManagerFactoryBean
                = new LocalContainerEntityManagerFactoryBean();

        saluhudAdministrationPortalEntityManagerFactoryBean.setDataSource(buildSaluhudAdministrationPortalDataSource());
        saluhudAdministrationPortalEntityManagerFactoryBean.setPackagesToScan("com.uhu.saluhuddatabaseutils.models");
        saluhudAdministrationPortalEntityManagerFactoryBean.setPersistenceUnitName("saluhud_administration_portal_persistence_unit");

        JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();

        saluhudAdministrationPortalEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);

        /*Map<String,String> props = new HashMap<>();
        props.put("hibernate.dialect","org.hibernate.dialect.PostgreSQLDialect");
        saluhudAdminEntityManagerFactoryBean.setJpaPropertyMap(props);*/
        
        return saluhudAdministrationPortalEntityManagerFactoryBean;
    }

    //@Primary
    @Bean(name = "saluhudAdministrationPortalTransactionManager")
    public PlatformTransactionManager saluhudAdministrationPortalTransactionManager()
    {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(saluhudAdministrationPortalEntityManagerFactory().getObject());

        return jpaTransactionManager;
    }

    private DataSource buildSaluhudAdministrationPortalDataSource()
    {
        try
        {
            Properties saluhudAdminDataSourceProperties = new Properties();
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream stream = loader.getResourceAsStream("datasources/saluhud-administration-portal-datasource.properties");
            saluhudAdminDataSourceProperties.load(stream);
            
            String url = (String) saluhudAdminDataSourceProperties.get("spring.datasource.url");
            String username = (String) saluhudAdminDataSourceProperties.get("spring.datasource.username");
            String password = (String) saluhudAdminDataSourceProperties.get("spring.datasource.password");
            String driver = (String) saluhudAdminDataSourceProperties.get("spring.datasource.driver-class-name");
            
            logger.log(Level.INFO, "Saluhud Administration Portal DataSource URL: {0}", url);
            logger.log(Level.INFO, "Saluhud Administration Portal DataSource Username: {0}", username);
            logger.log(Level.INFO, "Saluhud Administration Portal DataSource Driver: {0}", driver);
            
            DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create().type(HikariDataSource.class);
            dataSourceBuilder.driverClassName(driver);
            dataSourceBuilder.url(url);
            dataSourceBuilder.username(username);
            dataSourceBuilder.password(password);
            
            return dataSourceBuilder.build();
            
        } catch (IOException ex)
        {
            Logger.getLogger(SaluhudAdministrationPortalDataSourceConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
