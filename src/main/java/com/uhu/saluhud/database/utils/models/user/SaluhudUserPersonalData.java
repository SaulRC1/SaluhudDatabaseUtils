package com.uhu.saluhud.database.utils.models.user;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class represents the personal information of an user stored in the
 * database
 *
 * @author Juan Alberto Dominguez Vazquez
 */
@Entity
@Table(name = "saluhud_user_personal_data")
public class SaluhudUserPersonalData implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "phone_number", unique = true)
    private int phoneNumber;

    /**
     * This a default constructor for the class, with no parameters
     */
    public SaluhudUserPersonalData()
    {
    }

    /**
     * This is a parameterized constructor for the class.It takes, the user his
     * name his surname and his phone number
     *
     * @param name the name of the user
     * @param surname the surname of the user
     * @param phoneNumber the phone number of ther user
     * 
     */
    public SaluhudUserPersonalData(String name, String surname, int phoneNumber)
    {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
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
    public int getPhoneNumber()
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
    public void setPhoneNumber(int phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
}
