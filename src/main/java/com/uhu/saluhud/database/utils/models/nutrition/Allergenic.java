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
     * @param id
     * @param name
     */
    public Allergenic(long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     *
     * @return Getter for the parameter "id"
     */
    public long getId() {
        return id;
    }

    /**
     *
     * @return Getter for the parameter "name"
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name Setter for the parameter "name"
     */
    public void setName(String name) {
        this.name = name;
    }
}
