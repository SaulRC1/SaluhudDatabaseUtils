package com.uhu.saluhud.database.utils.bootstrap;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
public abstract class SaluhudHibernateBootstrapper
{
    private StandardServiceRegistryBuilder standardServiceRegistryBuilder;
    private StandardServiceRegistry standardServiceRegistry;
    
    private Metadata metadata;
    
    private SessionFactory sessionFactory;
    
    public abstract SessionFactory getSessionFactoryInstance();    
}
