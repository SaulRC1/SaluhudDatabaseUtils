package com.uhu.saluhud.database.utils.models.security;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;

/**
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

    public Authority()
    {
        super();
    }

    @Override
    public String getAuthority()
    {
        return this.authority;
    }

    public void setAuthority(final String authority)
    {
        this.authority = authority;
    }

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

    @Override
    public int hashCode()
    {
        return this.getAuthority().hashCode();
    }

    @Override
    public boolean equals(final Object other)
    {
        boolean result;

        if (this == other) {
            result = true;
        } else if (other == null) {
            result = false;
        } else if (!this.getClass().isInstance(other)) {
            result = false;
        } else {
            result = (this.getAuthority().equals(((Authority) other).getAuthority()));
        }

        return result;
    }

    @Override
    public String toString()
    {
        return this.authority;
    }
}
