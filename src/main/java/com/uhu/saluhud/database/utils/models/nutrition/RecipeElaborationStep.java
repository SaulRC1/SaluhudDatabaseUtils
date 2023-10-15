/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uhu.saluhud.database.utils.models.nutrition;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * This class represent one step of the proccess of elaboration of the recipe
 */
@Entity
@Table(name = "RECIPE_ELABORATION_STEP")
public class RecipeElaborationStep {

    @Id
    private long id;

    @Column(name = "step_description", nullable = false)
    private String stepDescription;

    @Column(name = "step_number", nullable = false)
    private int stepNumber;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "RECIPE")
    private Recipe recipe;

    /**
     * This a default constructor for the class, with no parameters
     */
    public RecipeElaborationStep() {

    }

    /**
     * This is a parameterized constructor for the class. It takes, the id, the
     * step description, the step number and the recipe
     *
     * @param id The id of the Step
     * @param stepDescription The description of the Step
     * @param stepNumber The number of the Step
     * @param recipe The recipe which the Step belongs to
     */
    public RecipeElaborationStep(long id, String stepDescription,
            int stepNumber, Recipe recipe) {
        this.id = id;
        this.stepDescription = stepDescription;
        this.stepNumber = stepNumber;
        this.recipe = recipe;
    }

    /**
     * Getter for the parameter "id"
     *
     * @return The id of the Step
     */
    public long getId() {
        return id;
    }

    /**
     * Getter fot the parameter "stepDescription"
     *
     * @return The description of the Step
     */
    public String getStepDescription() {
        return stepDescription;
    }

    /**
     * Setter for the parameter "stepDescription"
     *
     * @param stepDescription The new description of the Step
     */
    public void setStepDescription(String stepDescription) {
        this.stepDescription = stepDescription;
    }

    /**
     * Getter of the parameter "stepNumber"
     *
     * @return The number of the Step
     */
    public int getStepNumber() {
        return stepNumber;
    }

    /**
     * Setter for the parameter "stepNumber"
     *
     * @param stepNumber The new number of the step
     */
    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }

    /**
     * Getter for the parameter "recipe"
     *
     * @return The recipe which the Step belongs to
     */
    public Recipe getRecipe() {
        return recipe;
    }

    /**
     * Setter for the parameter "recipe"
     *
     * @param recipe The new recipe which the step belongs now
     */
    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}