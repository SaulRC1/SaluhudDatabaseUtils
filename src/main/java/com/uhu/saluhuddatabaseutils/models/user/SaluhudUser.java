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
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.Objects;

/**
 * This class represents the Saluhud mobile app users stored in the database
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
    @JoinTable(name = "SALUHUD_USER_SALUHUD_USER_FITNESS_DATA",
            joinColumns = @JoinColumn(name = "id_saluhud_user"),
            inverseJoinColumns = @JoinColumn(name = "id_user_fitness_data"))
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
     * This is a parameterized constructor for the class. It takes, the
     * username, his password and his email.
     *
     * @param username the username used to logging in the app
     * @param password The password of the user
     * @param email the email of the user
     * @param name the name of the user
     */
    public SaluhudUser(String username, String password, String email, String name)
    {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
    }

    /**
     * This is a parameterized constructor for the class. It takes, the
     * username, his password, his email, name, surname and phone number.
     *
     * @param username the username used to logging in the app
     * @param password the password of the user
     * @param email the email of the user
     * @param name the name of the user
     * @param surname the surname of the user
     * @param phoneNumber the phone number of the user
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
     * This is a parameterized constructor for the class.It takes, the username,
     * his password, his email, name, surname, phone number and his fitness
     * data.
     *
     * @param username the username used to logging in the app
     * @param password the password of the user
     * @param email the email of the user
     * @param name the name of the user
     * @param surname the surname of the user
     * @param phoneNumber the phone number of the user
     * @param userFitnessData the fitness data of the user
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
     * Getter for the parameter "username"
     *
     * @return the username of the user
     */
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
     * Getter for the parameter "email"
     *
     * @return the email of the user
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * Setter for the parameter "email"
     *
     * @param email the new email of the user
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * Getter for the parameter "fitnessData"
     *
     * @return the fitness data of the user
     */
    public SaluhudUserFitnessData getFitnessData()
    {
        return fitnessData;
    }

    /**
     * Setter for the parameter "fitnessData"
     *
     * @param fitnessData the fitness data of the user
     */
    public void setFitnessData(SaluhudUserFitnessData fitnessData)
    {
        this.fitnessData = fitnessData;
    }

    /**
     * Getter for the parameter "name"
     *
     * @return the name of the user
     */
    public String getName()
    {
        return name;
    }

    /**
     * Getter for the parameter "surname"
     *
     * @return the surname of the user
     */
    public String getSurname()
    {
        return surname;
    }

    /**
     * Getter for the parameter "phoneNumber"
     *
     * @return the phone number of the user
     */
    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    /**
     * Setter for the parameter "name"
     *
     * @param name the new name of the user
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Setter for the parameter "surname"
     *
     * @param surname the new surname of the user
     */
    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    /**
     * Setter for the parameter "phoneNumber"
     *
     * @param phoneNumber the new phone number of the user
     */
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
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

    @Override
    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
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
