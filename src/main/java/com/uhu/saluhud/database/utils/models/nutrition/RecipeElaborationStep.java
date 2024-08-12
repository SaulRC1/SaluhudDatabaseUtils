package com.uhu.saluhud.database.utils.models.nutrition;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.data.annotation.Version;

/**
 *
 * This class represent one step of the proccess of elaboration of the recipe
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
    private String stepDescription;

    @Column(name = "step_number", nullable = false)
    private int stepNumber;
    
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
     * This is a parameterized constructor for the class. It takes, the id, the
     * step description and the step number
     *
     * @param id The id of the Step
     * @param stepDescription The description of the Step
     * @param stepNumber The number of the Step
     */
    public RecipeElaborationStep(long id, String stepDescription, int stepNumber)
    {
        this.id = id;
        this.stepDescription = stepDescription;
        this.stepNumber = stepNumber;
    }
    
    /**
     * This is a parameterized constructor for the class. It takes, the
     * step description and the step number
     *
     * @param stepDescription The description of the Step
     * @param stepNumber The number of the Step
     */
    public RecipeElaborationStep(String stepDescription, int stepNumber)
    {
        this.stepDescription = stepDescription;
        this.stepNumber = stepNumber;
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

    public Long getVersion()
    {
        return version;
    }

    public void setVersion(Long version)
    {
        this.version = version;
    }
    
}
