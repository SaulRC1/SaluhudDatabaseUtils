package com.uhu.saluhud.database.utils.bootstrap;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
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
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream("com.uhu.saluhud.database.utils.models".replaceAll("[.]", "/"));
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        
        Set<Class> modelPackageClasses = reader.lines().filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, "com.uhu.saluhud.database.utils.models"))
                .collect(Collectors.toSet());
        
        for (Class modelPackageClass : modelPackageClasses)
        {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Model Package Classes: " + modelPackageClass);
        }
    }
    
    private Class getClass(String className, String packageName)
    {
        try
        {
            return Class.forName(packageName + "." + className.substring(0, className.lastIndexOf('.')));
        }
        catch (ClassNotFoundException e)
        {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
        
        return null;
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
