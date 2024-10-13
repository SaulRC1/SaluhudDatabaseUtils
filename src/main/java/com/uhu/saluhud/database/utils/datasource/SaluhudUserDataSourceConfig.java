package com.uhu.saluhud.database.utils.datasource;

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
        basePackages = "com.uhu.saluhud.database.utils.repositories.saluhud.user",
        entityManagerFactoryRef = "saluhudUserEntityManagerFactory",
        transactionManagerRef = "saluhudUserTransactionManager"
)
@PropertySource("classpath:datasources/saluhud-user-datasource.properties")
public class SaluhudUserDataSourceConfig
{
    private static final Logger logger = Logger.getLogger(SaluhudUserDataSourceConfig.class.getName());
    
    @Bean(name = "saluhudUserEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean saluhudUserEntityManagerFactory()
    {
        LocalContainerEntityManagerFactoryBean saluhudUserEntityManagerFactoryBean
                = new LocalContainerEntityManagerFactoryBean();

        saluhudUserEntityManagerFactoryBean.setDataSource(buildSaluhudUserDataSource());
        saluhudUserEntityManagerFactoryBean.setPackagesToScan("com.uhu.saluhud.database.utils.models");
        saluhudUserEntityManagerFactoryBean.setPersistenceUnitName("saluhud_user_persistence_unit");

        JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();

        saluhudUserEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        
        return saluhudUserEntityManagerFactoryBean;
    }

    @Bean(name = "saluhudUserTransactionManager")
    public PlatformTransactionManager saluhudUserTransactionManager(
            @Qualifier("saluhudUserEntityManagerFactory") LocalContainerEntityManagerFactoryBean saluhudUserEntityManagerFactory)
    {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(saluhudUserEntityManagerFactory.getObject());

        return jpaTransactionManager;
    }

    private DataSource buildSaluhudUserDataSource()
    {
        try
        {
            Properties saluhudUserDataSourceProperties = new Properties();
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream stream = loader.getResourceAsStream("datasources/saluhud-user-datasource.properties");
            saluhudUserDataSourceProperties.load(stream);
            
            String url = (String) saluhudUserDataSourceProperties.get("spring.datasource.url");
            String username = (String) saluhudUserDataSourceProperties.get("spring.datasource.username");
            String password = (String) saluhudUserDataSourceProperties.get("spring.datasource.password");
            String driver = (String) saluhudUserDataSourceProperties.get("spring.datasource.driver-class-name");
            
            logger.log(Level.INFO, "Saluhud User DataSource URL: {0}", url);
            logger.log(Level.INFO, "Saluhud User DataSource Username: {0}", username);
            logger.log(Level.INFO, "Saluhud User DataSource Driver: {0}", driver);
            
            DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create().type(HikariDataSource.class);
            dataSourceBuilder.driverClassName(driver);
            dataSourceBuilder.url(url);
            dataSourceBuilder.username(username);
            dataSourceBuilder.password(password);
            
            return dataSourceBuilder.build();
            
        } catch (IOException ex)
        {
            Logger.getLogger(SaluhudUserDataSourceConfig.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        
        return null;
    }
}
