/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uhu.saluhud.database.utils.models.nutrition;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "ingredients_description")
    private String ingredientsDescription;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "RECIPE_INGREDIENT")
    private List<Ingredient> ingredients;

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
    public Recipe(long id, String name, String description, String ingredientsDescription) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ingredientsDescription = ingredientsDescription;
    }

    /**
     * Getter for the parameter "id"
     *
     * @return The id of the recipe
     */
    public long getId() {
        return id;
    }

    /**
     * Getter for the parameter "name"
     *
     * @return The name of the recipe
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the parameter "description"
     *
     * @return The description of description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter for the parameter "ingredients_description"
     *
     * @return The description of the ingredients
     */
    public String getIngredientsDescription() {
        return ingredientsDescription;
    }

    /**
     *
     * @return
     */
    public List<Ingredient> getIngredients() {
        return ingredients;
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

    /**
     *
     * @param ingredients
     */
    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
