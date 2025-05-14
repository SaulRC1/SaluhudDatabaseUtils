package com.uhu.saluhuddatabaseutils.datasource;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
public enum DataSourceEnvironmentVariables 
{
    DATABASE_HOST("SALUHUD_DB_HOST"),
    DATABASE_PORT("SALUHUD_DB_PORT");
    
    private final String environmentVariableName;
    
    private DataSourceEnvironmentVariables(String environmentVariableName)
    {
        this.environmentVariableName = environmentVariableName;
    }

    public String getEnvironmentVariableName()
    {
        return environmentVariableName;
    }
}
