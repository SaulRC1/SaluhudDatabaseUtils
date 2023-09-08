/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uhu.saluhud.database.utils.models.nutrition;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * This class represents the recipes stored in the database, composed of a list
 * of ingredients.
 */
@Entity
@Table(name = "RECIPE")
public class Recipe {

    @Id
    private long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String ingredients_description;

    /**
     * This a default constructor for the class, with no parameters
     */
    public Recipe() {

    }

    /**
     * This is a parameterized constructor for the class. It takes, the id, the
     * name, the description and the ingredients description of a Recipe
     *
     * @param id
     * @param name
     * @param description
     * @param ingredients_description
     */
    public Recipe(long id, String name, String description, String ingredients_description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ingredients_description = ingredients_description;
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
     * @return Getter for the parameter "description"
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @return Getter for the parameter "ingredients_description"
     */
    public String getIngredients_description() {
        return ingredients_description;
    }

    /**
     *
     * @param id Setter for the parameter "name
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     *
     * @param name Setter for the parameter "name"
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param description Setter for the parameter "description"
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @param ingredients_description Setter for the parameter
     * "ingredients_description"
     */
    public void setIngredients_description(String ingredients_description) {
        this.ingredients_description = ingredients_description;
    }
}
