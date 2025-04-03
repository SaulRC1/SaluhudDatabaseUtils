package com.uhu.saluhuddatabaseutils.models.security;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;

/**
 * Represents a user authority or role within the application. This class
 * implements the {@link GrantedAuthority} interface and is used to define roles
 * and permissions for users.
 *
 * @author Juan Alberto Domínguez Vázquez
 */
@Embeddable
public class Authority implements GrantedAuthority
{

    public static final String ADMIN = "ADMIN";

    @NotBlank
    @Column(name = "authority")
    @Pattern(regexp = "^" + Authority.ADMIN + "$")
    private String authority;

    /**
     * Default constructor. Initializes a new instance of the {@link Authority}
     * class.
     */
    public Authority()
    {
        super();
    }

    /**
     * Returns the authority (role) assigned to this object. This is the value
     * that represents the role of the user, e.g., "ADMIN".
     *
     * @return the authority (role) as a {@link String}
     */
    @Override
    public String getAuthority()
    {
        return this.authority;
    }

    /**
     * Sets the authority (role) for this object. This method assigns a role to
     * the {@link Authority} object.
     *
     * @param authority the authority (role) to set
     */
    public void setAuthority(final String authority)
    {
        this.authority = authority;
    }

    /**
     * Returns a collection of predefined authorities for the application.
     * Currently, only the "ADMIN" role is defined.
     *
     * @return a {@link Collection} of {@link Authority} objects
     */
    public static Collection<Authority> listAuthorities()
    {
        Collection<Authority> result;
        Authority authority;

        result = new ArrayList<Authority>();

        authority = new Authority();
        authority.setAuthority(Authority.ADMIN);
        result.add(authority);

        return result;
    }

    /**
     * Calculates the hash code for this {@link Authority} object. The hash code
     * is based on the {@link Authority#getAuthority()} value.
     *
     * @return the hash code for this object
     */
    @Override
    public int hashCode()
    {
        return this.getAuthority().hashCode();
    }

    /**
     * Compares this {@link Authority} object to another object for equality.
     * Two authorities are considered equal if their authority values are the
     * same.
     *
     * @param other the object to compare with
     * @return {@code true} if the objects are equal, {@code false} otherwise
     */
    @Override
    public boolean equals(final Object other)
    {
        boolean result;

        if (this == other)
        {
            result = true;
        }
        else if (other == null)
        {
            result = false;
        }
        else if (!this.getClass().isInstance(other))
        {
            result = false;
        }
        else
        {
            result = (this.getAuthority().equals(((Authority) other).getAuthority()));
        }

        return result;
    }

    /**
     * Returns a string representation of this {@link Authority} object. The
     * string is the authority value.
     *
     * @return a string representation of the authority
     */
    @Override
    public String toString()
    {
        return this.authority;
    }
}
