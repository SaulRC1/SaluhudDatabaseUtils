/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uhu.saluhud.database.utils.models.nutrition;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "ingredients_description")
    private String ingredientsDescription;

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
     * @param ingredientsDescription
     */
    public Recipe(Long id, String name, String description, String ingredientsDescription) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ingredientsDescription = ingredientsDescription;
    }

    /**
     *
     * @return Getter for the parameter "id"
     */
    public Long getId() {
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
    public String getIngredientsDescription() {
        return ingredientsDescription;
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
     * @param ingredientsDescription Setter for the parameter
     * "ingredients_description"
     */
    public void setIngredientsDescription(String ingredientsDescription) {
        this.ingredientsDescription = ingredientsDescription;
    }
}
