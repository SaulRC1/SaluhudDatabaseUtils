package com.uhu.saluhuddatabaseutils.models.nutrition;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 *
 * Represents a single step in the process of elaborating a recipe. Each step
 * contains a description, a sequence number, and is linked to a specific
 * recipe.
 *
 * @author Juan Alberto Domínguez Vázquez
 */
@Entity
@Table(name = "recipe_elaboration_step")
public class RecipeElaborationStep implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "step_description", nullable = false)
    @NotBlank
    private String stepDescription;

    @Column(name = "step_number", nullable = false)
    @NotNull
    private int stepNumber;

    @ManyToOne
    @JoinColumn(name = "id_recipe", nullable = false)
    private Recipe recipe;

    @Version
    @Column(name = "entity_version")
    private Long version;

    /**
     * This a default constructor for the class, with no parameters
     */
    public RecipeElaborationStep()
    {

    }

    /**
     * Constructs a new RecipeElaborationStep with an ID, step description, and
     * step number.
     *
     * @param id The unique identifier of the step.
     * @param stepDescription The description of the step.
     * @param stepNumber The sequence number of the step.
     */
    public RecipeElaborationStep(long id, String stepDescription, int stepNumber)
    {
        this.id = id;
        this.stepDescription = stepDescription;
        this.stepNumber = stepNumber;
    }

    /**
     * Constructs a new RecipeElaborationStep with a step description, step
     * number, and an associated recipe.
     *
     * @param stepDescription The description of the step.
     * @param stepNumber The sequence number of the step.
     * @param recipe The recipe to which this step belongs.
     */
    public RecipeElaborationStep(String stepDescription, int stepNumber,
            Recipe recipe)
    {
        this.stepDescription = stepDescription;
        this.stepNumber = stepNumber;
        this.recipe = recipe;
    }

    /**
     * Getter for the parameter "id"
     *
     * @return The id of the Step
     */
    public long getId()
    {
        return id;
    }

    /**
     * Setter for the parameter "id"
     *
     * @param id The id of the Step
     */
    public void setId(long id)
    {
        this.id = id;
    }

    /**
     * Getter fot the parameter "stepDescription"
     *
     * @return The description of the Step
     */
    public String getStepDescription()
    {
        return stepDescription;
    }

    /**
     * Setter for the parameter "stepDescription"
     *
     * @param stepDescription The new description of the Step
     */
    public void setStepDescription(String stepDescription)
    {
        this.stepDescription = stepDescription;
    }

    /**
     * Getter of the parameter "stepNumber"
     *
     * @return The number of the Step
     */
    public int getStepNumber()
    {
        return stepNumber;
    }

    /**
     * Setter for the parameter "stepNumber"
     *
     * @param stepNumber The new number of the step
     */
    public void setStepNumber(int stepNumber)
    {
        this.stepNumber = stepNumber;
    }

    /**
     * Getter for the parameter "recipe"
     *
     * @return the recipe which the step belongs to
     */
    public Recipe getRecipe()
    {
        return recipe;
    }

    /**
     * Setter for the parameter "recipe"
     *
     * @param recipe the new recipe which the step belongs to
     */
    public void setRecipe(Recipe recipe)
    {
        this.recipe = recipe;
    }

    /**
     * @return The version number of the entity.
     */
    public Long getVersion()
    {
        return version;
    }

    /**
     * Sets the version of the entity.
     *
     * @param version The new version number.
     */
    public void setVersion(Long version)
    {
        this.version = version;
    }

}
