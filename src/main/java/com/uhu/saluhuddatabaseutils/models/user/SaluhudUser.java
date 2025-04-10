package com.uhu.saluhuddatabaseutils.models.user;

import java.io.Serializable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
import java.util.Objects;

/**
 * This class represents the Saluhud mobile app users stored in the database. It
 * includes user details such as username, password, email, name, surname, and
 * phone number. It also supports fitness data for the user.
 *
 * @author Juan Alberto Dominguez Vazquez
 */
@Entity
@Table(name = "saluhud_user")
public class SaluhudUser implements Serializable
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
    @NotBlank
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
    @Size(min = 6, max = 200) //Minimum possible size is of 6 characters taking
    //into account the regular expression  
    private String email;

    @Column(name = "name", nullable = false)
    @Size(min = 1, max = 200)
    @Pattern(regexp = "^[a-zA-Zà-üÀ-Ü\\s]+$")
    @NotBlank
    private String name;

    @Column(name = "surname", nullable = true)
    @Size(max = 200)
    @Pattern(regexp = "^[a-zA-Zà-üÀ-Ü\\s]*$")
    private String surname;

    @Column(name = "phone_number", nullable = true, unique = true)
    //This pattern accepts phone number having the following format: +34 xxxx or
    //+1-939 xxxx (being x any possible number)
    @Pattern(regexp = "^(\\+(\\d{1,3}|\\d{1,3}-\\d{1,3})\\s\\d{4,32})?$")
    private String phoneNumber;

    @OneToOne(optional = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user_fitness_data")
    private SaluhudUserFitnessData fitnessData;

    @Version
    @Column(name = "entity_version")
    private Long version;

    /**
     * This a default constructor for the class, with no parameters
     */
    public SaluhudUser()
    {
    }

    /**
     * Parameterized constructor to create a new SaluhudUser instance with a
     * given username, password, email, and name.
     *
     * @param username The username used to log in to the app.
     * @param password The password of the user.
     * @param email The email of the user.
     * @param name The name of the user.
     */
    public SaluhudUser(String username, String password, String email, String name)
    {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
    }

    /**
     * Parameterized constructor to create a new SaluhudUser instance with a
     * given username, password, email, name, surname, and phone number.
     *
     * @param username The username used to log in to the app.
     * @param password The password of the user.
     * @param email The email of the user.
     * @param name The name of the user.
     * @param surname The surname of the user.
     * @param phoneNumber The phone number of the user.
     */
    public SaluhudUser(String username, String password, String email,
            String name, String surname, String phoneNumber)
    {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Parameterized constructor to create a new SaluhudUser instance with a
     * given username, password, email, name, surname, phone number, and fitness
     * data.
     *
     * @param username The username used to log in to the app.
     * @param password The password of the user.
     * @param email The email of the user.
     * @param name The name of the user.
     * @param surname The surname of the user.
     * @param phoneNumber The phone number of the user.
     * @param userFitnessData The fitness data associated with the user.
     */
    public SaluhudUser(String username, String password, String email,
            String name, String surname, String phoneNumber, SaluhudUserFitnessData userFitnessData)
    {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.fitnessData = userFitnessData;
    }

    /**
     * Gets the ID of the user.
     *
     * @return The ID of the user.
     */
    public long getId()
    {
        return id;
    }

    /**
     * Sets the ID of the user.
     *
     * @param id The new ID for the user.
     */
    public void setId(long id)
    {
        this.id = id;
    }

    /**
     * Gets the username of the user.
     *
     * @return The username of the user.
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * Sets the username of the user.
     *
     * @param username The new username for the user.
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

    /**
     * Gets the password of the user.
     *
     * @return The password of the user.
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password The new password for the user.
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * Gets the email of the user.
     *
     * @return The email of the user.
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * Sets the email of the user.
     *
     * @param email The new email for the user.
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * Gets the fitness data of the user.
     *
     * @return The fitness data associated with the user.
     */
    public SaluhudUserFitnessData getFitnessData()
    {
        return fitnessData;
    }

    /**
     * Sets the fitness data of the user.
     *
     * @param fitnessData The new fitness data for the user.
     */
    public void setFitnessData(SaluhudUserFitnessData fitnessData)
    {
        this.fitnessData = fitnessData;
    }

    /**
     * Gets the name of the user.
     *
     * @return The name of the user.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Gets the surname of the user.
     *
     * @return The surname of the user.
     */
    public String getSurname()
    {
        return surname;
    }

    /**
     * Gets the phone number of the user.
     *
     * @return The phone number of the user.
     */
    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    /**
     * Sets the name of the user.
     *
     * @param name The new name for the user.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Sets the surname of the user.
     *
     * @param surname The new surname for the user.
     */
    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    /**
     * Sets the phone number of the user.
     *
     * @param phoneNumber The new phone number for the user.
     */
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
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
        SaluhudUser that = (SaluhudUser) o;
        return id == that.id;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("SaluhudUser{");
        sb.append("id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", email=").append(email);
        sb.append(", name=").append(name);
        sb.append(", surname=").append(surname);
        sb.append(", phoneNumber=").append(phoneNumber);
        sb.append(", version=").append(version);
        sb.append('}');
        return sb.toString();
    }
}
