package com.uhu.saluhud.database.utils.bootstrap;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
public final class SaluhudAdministratorHibernateBootstrapper extends SaluhudHibernateBootstrapper
{
    private static final SaluhudAdministratorHibernateBootstrapper saluhudAdministratorHibernateBootstrapper 
            = new SaluhudAdministratorHibernateBootstrapper();
    
    private SaluhudAdministratorHibernateBootstrapper()
    {
        StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder()
                .configure(this.getClass().getResource("hibernate_saluhud_admin.cfg.xml")).build();
        
        MetadataSources metadataSources = new MetadataSources(standardServiceRegistry);
        
        this.configureHibernateMetadata(metadataSources);
        
        Metadata metadata = metadataSources.buildMetadata();
        
        SessionFactory sessionFactory = metadata.buildSessionFactory();
        
        this.setSessionFactory(sessionFactory);
    }

    @Override
    public void closeSessionFactory()
    {
        this.getSessionFactory().close();
    }

    @Override
    public void configureHibernateMetadata(MetadataSources metadataSources)
    {
        this.addAllAnnotatedClasses(metadataSources);
    }

    public static SaluhudHibernateBootstrapper getSaluhudAdministratorHibernateBootstrapperInstance()
    {
        return saluhudAdministratorHibernateBootstrapper;
    }
    
}
