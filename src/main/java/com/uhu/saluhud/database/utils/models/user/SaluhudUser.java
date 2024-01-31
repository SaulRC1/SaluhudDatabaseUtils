package com.uhu.saluhud.database.utils.models.user;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * This class represents the users stored in the database
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

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "SALUHUD_USER_PERSONAL_DATA_SALUHUD_USER",
            joinColumns = @JoinColumn(name = "id_saluhud_user"),
            inverseJoinColumns = @JoinColumn(name = "id_user_personal_data"))
    private SaluhudUserPersonalData personalData;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "SALUHUD_USER_SALUHUD_USER_FITNESS_DATA",
            joinColumns = @JoinColumn(name = "id_saluhud_user"),
            inverseJoinColumns = @JoinColumn(name = "id_user_fitness_data"))
    private SaluhudUserFitnessData fitnessData;

    /**
     * This a default constructor for the class, with no parameters
     */
    public SaluhudUser()
    {
    }

    /**
     * This is a parameterized constructor for the class.It takes, the username,
     * his password and his email.
     *
     * @param username the username used to logging in the app
     * @param password the password of the user
     * @param email the email of the user
     */
    public SaluhudUser(String username, String password, String email)
    {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    /**
     * This is a parameterized constructor for the class.It takes, the username,
     * his password and his email.
     *
     * @param username the username used to logging in the app
     * @param password the password of the user
     * @param email the email of the user
     * @param userPersonalData
     * @param userFitnessData
     */
    public SaluhudUser(String username, String password, String email, 
            SaluhudUserPersonalData userPersonalData, SaluhudUserFitnessData userFitnessData)
    {
        this.username = username;
        this.password = password;
        this.email = email;
        this.personalData = userPersonalData;
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
     * Setter for the parameter "username"
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
}
