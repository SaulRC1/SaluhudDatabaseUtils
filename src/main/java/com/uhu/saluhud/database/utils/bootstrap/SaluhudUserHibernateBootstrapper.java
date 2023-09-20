package com.uhu.saluhud.database.utils.bootstrap;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
public final class SaluhudUserHibernateBootstrapper extends SaluhudHibernateBootstrapper
{
    private static final SaluhudUserHibernateBootstrapper saluhudUserHibernateBootstrapper 
            = new SaluhudUserHibernateBootstrapper();
    
    private SaluhudUserHibernateBootstrapper()
    {
        StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate_saluhud_user.cfg.xml").build();
        
        MetadataSources metadataSources = new MetadataSources(standardServiceRegistry);
        
        this.configureHibernateMetadata(metadataSources);
        
        Metadata metadata = metadataSources.buildMetadata();
        
        SessionFactory sessionFactory = metadata.buildSessionFactory();
        
        this.setSessionFactory(sessionFactory);
    }

    @Override
    public void closeSessionFactory()
    {
        try
        {
            Logger.getLogger(SaluhudAdministratorHibernateBootstrapper.class.getName())
                    .log(Level.INFO, "Closing {0} SessionFactory...", SaluhudUserHibernateBootstrapper.class.getName());
            
            this.getSessionFactory().close();
            
            Logger.getLogger(SaluhudAdministratorHibernateBootstrapper.class.getName())
                    .log(Level.INFO, "{0} SessionFactory closed.", SaluhudUserHibernateBootstrapper.class.getName());
        }
        catch (HibernateException e)
        {
            Logger.getLogger(SaluhudAdministratorHibernateBootstrapper.class.getName())
                    .log(Level.SEVERE, e.getMessage(), e);
        }
    }

    @Override
    public void configureHibernateMetadata(MetadataSources metadataSources)
    {
        this.addAllAnnotatedClasses(metadataSources);
    }
    
    public static SaluhudHibernateBootstrapper getSaluhudUserHibernateBootstrapperInstance()
    {
        return saluhudUserHibernateBootstrapper;
    }

}
