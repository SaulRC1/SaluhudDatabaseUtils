package com.uhu.saluhud.database.utils.models.forum;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class represents the forum in the database, composed of a list of forum
 * threads.
 *
 * @author Juan Alberto Dominguez Vazquez
 */
@Entity
@Table(name = "forum")
public class Forum implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    /**
     * This a default constructor for the class, with no parameters
     */
    public Forum()
    {

    }

    /**
     * This is a parameterized constructor for the class.It takes, the name, the
     * description and the list of forum threads
     *
     * @param name The name of the forum
     * @param description The description of the forum
     */
    public Forum(String name, String description)
    {
        this.name = name;
        this.description = description;
    }

    /**
     * Getter for the parameter "id"
     *
     * @return the id of the forum
     */
    public long getId()
    {
        return id;
    }

    /**
     * Getter for the parameter "name"
     *
     * @return the name of the forum
     */
    public String getName()
    {
        return name;
    }

    /**
     * Setter for the parameter "id"
     *
     * @param name the new name of the forum
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Getter for the parameter "description"
     *
     * @return the description of the forum
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Setter for the parameter "description"
     *
     * @param description the new description of the forum
     */
    public void setDescription(String description)
    {
        this.description = description;
    }
}
