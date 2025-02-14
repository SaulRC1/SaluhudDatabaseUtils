package com.uhu.saluhud.database.utils.models.security;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 *
 * @author juald
 */
public class Credentials
{

    public Credentials()
    {
        super();
    }

    @Size(min = 5, max = 32)
    @NotBlank
    private String username;
    
    @Size(min = 5, max = 32)
    @NotBlank
    private String password;

    
    public String getUsername()
    {
        return this.username;
    }

    public void setUsername(final String username)
    {
        this.username = username;
    }

    
    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(final String password)
    {
        this.password = password;
    }

}
