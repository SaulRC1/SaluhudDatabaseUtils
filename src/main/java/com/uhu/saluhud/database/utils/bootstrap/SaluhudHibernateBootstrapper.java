package com.uhu.saluhud.database.utils.bootstrap;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.persistence.Entity;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ConfigurationBuilder;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
public abstract class SaluhudHibernateBootstrapper
{

    private SessionFactory sessionFactory;

    private static final String DATABASE_MODEL_CLASSES_BASE_DIRECTORY
            = "src/main/java/com/uhu/saluhud/database/utils/models";

    private static final String DATABASE_MODEL_CLASSES_BASE_PACKAGE
            = "com.uhu.saluhud.database.utils.models";

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
        /*Set<String> databaseModelPackages = getDatabaseModelPackages();

        Set<Class> databaseModelClasses = new HashSet<>();

        for (String databaseModelPackage : databaseModelPackages)
        {
            Set<Class> packageModelClasses = getPackageDatabaseModelClasses(databaseModelPackage);

            databaseModelClasses.addAll(packageModelClasses);
        }

        for (Class databaseModelClass : databaseModelClasses)
        {
            if(databaseModelClass.isAnnotationPresent(javax.persistence.Entity.class))
            {
                metadataSources.addAnnotatedClass(databaseModelClass);
            }
        }*/
        Set<Class<?>> entityClasses = getEntityClasses();

        for (Class<?> entityClass : entityClasses)
        {
            metadataSources.addAnnotatedClass(entityClass);
        }
    }

    private Set<Class<?>> getEntityClasses()
    {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .forPackages(DATABASE_MODEL_CLASSES_BASE_PACKAGE)
                .addScanners(new TypeAnnotationsScanner(), new SubTypesScanner()));

        return reflections.getTypesAnnotatedWith(Entity.class);
    }

    private Class getClass(String className, String packageName)
    {
        try
        {
            return Class.forName(packageName + "." + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e)
        {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }

        return null;
    }

    public Set<String> getDatabaseModelPackages()
    {
        Set<String> databaseModelPackages = new HashSet<>();

        databaseModelPackages.add(DATABASE_MODEL_CLASSES_BASE_PACKAGE);

        File modelsDirectory = new File(DATABASE_MODEL_CLASSES_BASE_DIRECTORY);

        File[] modelsDirectoryFiles = modelsDirectory.listFiles();

        for (File modelDirectoryFile : modelsDirectoryFiles)
        {
            if (modelDirectoryFile.isDirectory())
            {
                databaseModelPackages.add(modelDirectoryFile.getPath().replaceAll("[/\\\\]", ".")
                        .replaceFirst("src.main.java.", ""));
            }
        }
        return databaseModelPackages;
    }

    private Set<Class> getPackageDatabaseModelClasses(String packageName)
    {
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));

        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        Set<Class> modelPackageClasses = reader.lines().filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toSet());

        return modelPackageClasses;
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
