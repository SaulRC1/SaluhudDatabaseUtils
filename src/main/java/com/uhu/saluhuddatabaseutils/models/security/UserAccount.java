package com.uhu.saluhuddatabaseutils.models.security;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.CollectionTable;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.Version;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

@Entity
@Table(name = "user_account")
public class UserAccount implements UserDetails
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "username", nullable = false, unique = true)
    @NotBlank
    @Pattern(regexp = "^\\w+$")
    @Size(min = 2, max = 32)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @NotBlank
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*\\-_]).{8,32}$")
    @Size(min = 8, max = 32)
    @Transient //This raw password will be not persisted
    //The raw password without any hashing/encryption, it will only be used for
    //validation of the real password before encypting and persisting it into the
    //database
    private String rawPassword;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_account_authorities", joinColumns = @JoinColumn(name = "user_account_id"))
    @Column(name = "authority")
    @NotNull
    @Valid
    private Collection<Authority> authorities;

    @Version
    @Column(name = "entity_version")
    private Long version;

    public UserAccount()
    {

    }

    /**
     *
     * @param username
     * @param rawPassword
     * @param authorities
     */
    public UserAccount(String username, String rawPassword, Collection<Authority> authorities)
    {
        this.username = username;
        this.rawPassword = rawPassword;
        this.authorities = authorities;
    }

    /**
     * Getter for the parameter "username"
     *
     * @return the username of the user
     */
    @Override
    public String getUsername()
    {
        return username;
    }

    /**
     * Setter for the parameter "username"
     *
     * @param username the new username
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

    /**
     * Getter for the parameter "password"
     *
     * @return the password of the user
     */
    @Override
    public String getPassword()
    {
        return password;
    }

    /**
     * Setter for the parameter "password"
     *
     * @param password the new password
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * Returns the raw password (the password without any encryption). It will
     * return null if the entity has been retrieved from the database.
     *
     * @return the password without any encryption or null
     */
    public String getRawPassword()
    {
        return rawPassword;
    }

    /**
     * Sets the raw password of this user (the password without any encryption)
     *
     * @param rawPassword User's password without any encryption
     */
    public void setRawPassword(String rawPassword)
    {
        this.rawPassword = rawPassword;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority("ROLE_" + authority.getAuthority()))
                .toList();
    }

    public void setAuthorities(final Collection<Authority> authorities)
    {
        this.authorities = authorities;
    }

    public void addAuthority(final Authority authority)
    {
        Assert.notNull(authority, "Authority must not be null");
        Assert.isTrue(!this.authorities.contains(authority), "Authority already added");

        this.authorities.add(authority);
    }

    public void removeAuthority(final Authority authority)
    {
        Assert.notNull(authority, "Authority must not be null");
        Assert.isTrue(this.authorities.contains(authority), "Authority not found");

        this.authorities.remove(authority);
    }

    @Transient
    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Transient
    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Transient
    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Transient
    @Override
    public boolean isEnabled()
    {
        return true;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public Long getVersion()
    {
        return version;
    }

    /**
     *
     * @param version
     */
    public void setVersion(Long version)
    {
        this.version = version;
    }

}
