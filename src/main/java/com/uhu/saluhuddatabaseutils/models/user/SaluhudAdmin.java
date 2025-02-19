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
 * This class represents the Saluhud administrators stored in the database
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
     * This a default constructor for the class, with no parameters
     */
    public SaluhudAdmin()
    {
    }


    /**
     * This is a parameterized constructor for the class.It takes, his user account.
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
     * Getter for the parameter "id"
     *
     * @return the id of the user
     */
    public long getId()
    {
        return id;
    }  

    /**
     * Setter for the parameter "id"
     * 
     * @param id the id of the user
     */
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

    /**
     * Getter for the parameter "name"
     *
     * @return the name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the parameter "name"
     *
     * @param name the new name of the user
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
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
     *
     * @return
     */
    public UserAccount getUserAccount()
    {
        return userAccount;
    }

    /**
     *
     * @param userAccount
     */
    public void setUserAccount(final UserAccount userAccount)
    {
        this.userAccount = userAccount;
    }

}
