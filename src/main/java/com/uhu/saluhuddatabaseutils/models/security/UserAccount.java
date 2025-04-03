package com.uhu.saluhuddatabaseutils.models.security;

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

/**
 * Represents a user account in the system, including the username, password,
 * authorities, and version. Implements {@link UserDetails} for Spring Security
 * authentication and authorization. The password is stored encrypted, and
 * authorities are managed through roles.
 *
 * @author Juan Alberto Domínguez Vázquez
 */
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

    /**
     * Default constructor for the {@link UserAccount} entity. Initializes a new
     * instance of the class with no parameters.
     */
    public UserAccount()
    {

    }

    /**
     * Constructor for creating a new {@link UserAccount} with the specified
     * username, raw password, and authorities.
     *
     * @param username the username of the user
     * @param rawPassword the raw password (unencrypted) for validation
     * @param authorities the collection of {@link Authority} granted to the
     * user
     */
    public UserAccount(String username, String rawPassword, Collection<Authority> authorities)
    {
        this.username = username;
        this.rawPassword = rawPassword;
        this.authorities = authorities;
    }

    /**
     * Returns the username associated with this account.
     *
     * @return the username of the user as a {@link String}
     */
    @Override
    public String getUsername()
    {
        return username;
    }

    /**
     * Sets the username for this account.
     *
     * @param username the new username to be set
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

    /**
     * Returns the encrypted password associated with this account.
     *
     * @return the encrypted password of the user as a {@link String}
     */
    @Override
    public String getPassword()
    {
        return password;
    }

    /**
     * Sets the encrypted password for this account.
     *
     * @param password the new encrypted password to be set
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * Returns the raw password (unencrypted) associated with this account. The
     * raw password is only used during validation and is not persisted in the
     * database.
     *
     * @return the raw password of the user or {@code null} if retrieved from
     * the database
     */
    public String getRawPassword()
    {
        return rawPassword;
    }

    /**
     * Sets the raw password (unencrypted) for this account. The raw password is
     * used for validation before encryption and persistence.
     *
     * @param rawPassword the raw password to be set
     */
    public void setRawPassword(String rawPassword)
    {
        this.rawPassword = rawPassword;
    }

    /**
     * Returns a collection of {@link GrantedAuthority} for this user. This is
     * used by Spring Security to manage user roles and permissions.
     *
     * @return a collection of granted authorities as {@link GrantedAuthority}
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority("ROLE_" + authority.getAuthority()))
                .toList();
    }

    /**
     * Sets the collection of {@link Authority} for this user account.
     *
     * @param authorities the collection of authorities to be assigned to the
     * user
     */
    public void setAuthorities(final Collection<Authority> authorities)
    {
        this.authorities = authorities;
    }

    /**
     * Adds a new {@link Authority} to this user's account.
     *
     * @param authority the authority to be added
     * @throws IllegalArgumentException if the authority is null or already
     * added
     */
    public void addAuthority(final Authority authority)
    {
        Assert.notNull(authority, "Authority must not be null");
        Assert.isTrue(!this.authorities.contains(authority), "Authority already added");

        this.authorities.add(authority);
    }

    /**
     * Removes an existing {@link Authority} from this user's account.
     *
     * @param authority the authority to be removed
     * @throws IllegalArgumentException if the authority is null or not found
     */
    public void removeAuthority(final Authority authority)
    {
        Assert.notNull(authority, "Authority must not be null");
        Assert.isTrue(this.authorities.contains(authority), "Authority not found");

        this.authorities.remove(authority);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@code true} if the account is not expired, {@code false}
     * otherwise
     */
    @Transient
    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@code true} if the account is not locked, {@code false}
     * otherwise
     */
    @Transient
    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@code true} if the credentials are not expired, {@code false}
     * otherwise
     */
    @Transient
    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @return {@code true} if the account is enabled, {@code false} otherwise
     */
    @Transient
    @Override
    public boolean isEnabled()
    {
        return true;
    }

    /**
     * Returns the ID of this user account.
     *
     * @return the ID of the user account as a {@link long}
     */
    public long getId()
    {
        return id;
    }

    /**
     * Sets the ID for this user account.
     *
     * @param id the new ID to be set
     */
    public void setId(long id)
    {
        this.id = id;
    }

    /**
     * Returns the version of this user account entity for optimistic locking.
     *
     * @return the version of the entity as a {@link Long}
     */
    public Long getVersion()
    {
        return version;
    }

    /**
     * Sets the version of this user account entity for optimistic locking.
     *
     * @param version the new version to be set
     */
    public void setVersion(Long version)
    {
        this.version = version;
    }

}
