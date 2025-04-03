package com.uhu.saluhuddatabaseutils.models.security;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Represents the credentials for a user, including the username and password.
 * Used for authentication purposes in the system.
 *
 * @author Juan Alberto Domínguez Vázquez
 */
public class Credentials
{

    /**
     * Default constructor for the {@link Credentials} class. Initializes a new
     * instance of the class with no parameters.
     */
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

    /**
     * Returns the username associated with these credentials. The username is
     * used for identifying the user during authentication.
     *
     * @return the username as a {@link String}
     */
    public String getUsername()
    {
        return this.username;
    }

    /**
     * Sets the username for these credentials. The username is used for
     * identifying the user during authentication.
     *
     * @param username the username to set
     */
    public void setUsername(final String username)
    {
        this.username = username;
    }

    /**
     * Returns the password associated with these credentials. The password is
     * used for authenticating the user.
     *
     * @return the password as a {@link String}
     */
    public String getPassword()
    {
        return this.password;
    }

    /**
     * Sets the password for these credentials. The password is used for
     * authenticating the user.
     *
     * @param password the password to set
     */
    public void setPassword(final String password)
    {
        this.password = password;
    }

}
