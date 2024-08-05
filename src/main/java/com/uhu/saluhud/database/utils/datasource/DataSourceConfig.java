package com.uhu.saluhud.database.utils.datasource;

import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@Configuration(proxyBeanMethods = false)
@PropertySource("classpath:datasources/saluhud-admin-datasource.properties")
public class DataSourceConfig
{

    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource saluhudAdminDataSource()
    {
        return DataSourceBuilder.create().build();
    }
    
}
