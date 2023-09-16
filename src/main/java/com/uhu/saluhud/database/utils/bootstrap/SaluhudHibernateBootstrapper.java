package com.uhu.saluhud.database.utils.bootstrap;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
public abstract class SaluhudHibernateBootstrapper
{    
    private SessionFactory sessionFactory;
    
    public abstract void closeSessionFactory();
    
    public abstract void configureHibernateMetadata(MetadataSources metadataSources);

    /**
     * Will add all annotated classes to Hibernate's metadata.
     * 
     * <p>
     * Annotated classes will always be inside the 
     * {@link com.uhu.saluhud.database.utils.models} package and subpackages.
     * </p>
     * 
     * @param metadataSources Hibernate's {@link MetadataSources}
     */
    protected void addAllAnnotatedClasses(MetadataSources metadataSources)
    {
        
    }

    public SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    protected void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }
    
}
