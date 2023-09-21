package com.uhu.saluhud.database.utils.models.nutrition;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class represents the existent allergenics present in food.
 */
@Entity
@Table(name = "ALLERGENIC")
public class Allergenic {

    @Id
    private long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    /**
     * This a default constructor for the class, with no parameters
     */
    public Allergenic() {

    }

    /**
     * This is a parameterized constructor for the class. It takes, the id and
     * the name of an Allergenic
     *
     * @param id The id of the Allergenic
     * @param name The name of the Allergenic
     */
    public Allergenic(long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Getter for the parameter "id"
     *
     * @return The id of the Allergenic
     */
    public long getId() {
        return id;
    }

    /**
     * Getter for the parameter "name"
     *
     * @return The name of the Allergenic
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the parameter "name"
     *
     * @param name The new name of the Allergenic
     */
    public void setName(String name) {
        this.name = name;
    }
}