/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uhu.saluhud.database.utils.models.nutrition;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
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
    private List<Ingredient> ingredients = new ArrayList<>();

    /**
     * This a default constructor for the class, with no parameters
     */
    public Recipe() {

    }

    /**
     * This is a parameterized constructor for the class. It takes, the id, the
     * name, the description and the ingredients description of a Recipe
     *
     * @param id The id of the recipe
     * @param name The name of the recipe
     * @param description The description of the recipe
     * @param ingredientsDescription The description of all ingredients of the
     * recipe
     */
    public Recipe(long id, String name, String description, String ingredientsDescription, List<Ingredient> ingredients) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ingredientsDescription = ingredientsDescription;
        this.ingredients = ingredients;
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
     * Getter fot the parameter "ingredients"
     *
     * @return
     */
    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    /**
     * Setter for the parameter "name"
     *
     * @param name The new name of the recipe
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter for the parameter "description"
     *
     * @param description Tne new description of the recipe
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Setter for the parameter "ingredients_description"
     *
     * @param ingredientsDescription The new ingredients description of the
     * recipe
     */
    public void setIngredientsDescription(String ingredientsDescription) {
        this.ingredientsDescription = ingredientsDescription;
    }

    /**
     * Setter for the parameter "ingredients"
     *
     * @param ingredients The new list of ingredients of the recipe
     */
    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}