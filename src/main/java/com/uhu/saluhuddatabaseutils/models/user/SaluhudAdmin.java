package com.uhu.saluhuddatabaseutils.models.user;

import com.uhu.saluhuddatabaseutils.models.security.UserAccount;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * This class represents the Saluhud administrators stored in the database. Each
 * administrator is associated with a user account and has a name. The class
 * supports versioning for optimistic locking and includes validation for
 * fields.
 *
 * @author Juan Alberto Dominguez Vazquez
 */
@Entity
@Table(name = "saluhud_admin")
public class SaluhudAdmin implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name", nullable = false)
    @Size(min = 1, max = 200)
    @Pattern(regexp = "^[a-zA-Zà-üÀ-Ü\\s]+$")
    @NotBlank
    private String name;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_account_id", nullable = false)
    private UserAccount userAccount;

    @Version
    @Column(name = "entity_version")
    private Long version;

    /**
     * Default constructor for the SaluhudAdmin class. It does not initialize
     * any fields.
     */
    public SaluhudAdmin()
    {
    }

    /**
     * Parameterized constructor to create a new SaluhudAdmin instance with a
     * given name and user account.
     *
     * @param name
     * @param userAccount the user account of the admin
     */
    public SaluhudAdmin(String name, UserAccount userAccount)
    {
        this.name = name;
        this.userAccount = userAccount;
    }

    /**
     * Gets the ID of the administrator.
     *
     * @return The ID of the administrator.
     */
    public long getId()
    {
        return id;
    }

    /**
     * Sets the ID of the administrator.
     *
     * @param id The new ID for the administrator.
     */
    public void setId(long id)
    {
        this.id = id;
    }

    /**
     * Gets the version of the entity for optimistic locking.
     *
     * @return The version number of the entity.
     */
    public Long getVersion()
    {
        return version;
    }

    /**
     * Sets the version of the entity for optimistic locking.
     *
     * @param version The new version number of the entity.
     */
    public void setVersion(Long version)
    {
        this.version = version;
    }

    /**
     * Gets the name of the administrator.
     *
     * @return The name of the administrator.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the name of the administrator.
     *
     * @param name The new name for the administrator.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        SaluhudAdmin that = (SaluhudAdmin) o;
        return id == that.id;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }

    /**
     * Gets the user account associated with this administrator.
     *
     * @return The user account associated with the administrator.
     */
    public UserAccount getUserAccount()
    {
        return userAccount;
    }

    /**
     * Sets the user account associated with this administrator.
     *
     * @param userAccount The new user account for the administrator.
     */
    public void setUserAccount(final UserAccount userAccount)
    {
        this.userAccount = userAccount;
    }

}
