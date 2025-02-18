package com.uhu.saluhud.database.utils.models.nutrition;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 *
 * This class represents the recipes stored in the database, composed of a list
 * of ingredients.
 */
@Entity
@Table(name = "recipe")
public class Recipe implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name", nullable = false)
    @NotBlank
    @Size(min = 2, max = 200)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "ingredients_description")
    private String ingredientsDescription;
    
    @Column(name = "kilocalories")
    private int kilocalories;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade
            = {
                CascadeType.MERGE
            })
    @JoinTable(name = "RECIPE_ALLERGENIC",
            joinColumns = @JoinColumn(name = "id_recipe"),
            inverseJoinColumns = @JoinColumn(name = "id_allergenic"))
    private Set<Allergenic> allergenics;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<RecipeIngredient> recipeIngredients;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RecipeElaborationStep> elaborationSteps;

    @Version
    @Column(name = "entity_version")
    private Long version;

    /**
     * This a default constructor for the class, with no parameters
     */
    public Recipe()
    {

    }

    /**
     * This is a parameterized constructor for the class.It takes, the id, the
     * name, the description and the ingredients description of a Recipe
     *
     * @param id The id of the recipe
     * @param name The name of the recipe
     * @param description The description of the recipe
     * @param ingredientsDescription The description of all ingredients of the
     * recipe
     * @param allergenics The set of all allergenics of the recipe
     * @param elaborationSteps The list of all steps of the recipe
     * @param recipeIngredients 
     * @param kilocalories 
     */
    public Recipe(long id, String name, String description,
            String ingredientsDescription,
            Set<Allergenic> allergenics, List<RecipeElaborationStep> elaborationSteps,
            List<RecipeIngredient> recipeIngredients, int kilocalories)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ingredientsDescription = ingredientsDescription;
        this.allergenics = allergenics;
        this.elaborationSteps = elaborationSteps;
        this.recipeIngredients = recipeIngredients;
        this.kilocalories = kilocalories;
    }

    /**
     * This is a parameterized constructor for the class.It takes, the
     * name, the description and the ingredients description of a Recipe
     *
     * @param name The name of the recipe
     * @param description The description of the recipe
     * @param ingredientsDescription The description of all ingredients of the
     * recipe
     * @param kilocalories
     */
    public Recipe(String name, String description, String ingredientsDescription,
            int kilocalories)
    {
        this.name = name;
        this.description = description;
        this.ingredientsDescription = ingredientsDescription;
        this.kilocalories = kilocalories;
    }

    /**
     * Getter for the parameter "id"
     *
     * @return The id of the recipe
     */
    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    /**
     * Getter for the parameter "name"
     *
     * @return The name of the recipe
     */
    public String getName()
    {
        return name;
    }

    /**
     * Getter for the parameter "description"
     *
     * @return The description of description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Getter for the parameter "ingredients_description"
     *
     * @return The description of the ingredients
     */
    public String getIngredientsDescription()
    {
        return ingredientsDescription;
    }


    /**
     * Getter for the parameter "allergenics"
     *
     * @return The set of allergenics of the recipe
     */
    public Set<Allergenic> getAllergenics()
    {
        return allergenics;
    }

    /**
     * Getter for the parameter "elaborationSteps"
     *
     * @return The list of the steps of the recipe
     */
    public List<RecipeElaborationStep> getElaborationSteps()
    {
        return elaborationSteps;
    }

    /**
     * Setter for the parameter "name"
     *
     * @param name The new name of the recipe
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Setter for the parameter "description"
     *
     * @param description Tne new description of the recipe
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * Setter for the parameter "ingredients_description"
     *
     * @param ingredientsDescription The new ingredients description of the
     * recipe
     */
    public void setIngredientsDescription(String ingredientsDescription)
    {
        this.ingredientsDescription = ingredientsDescription;
    }


    /**
     * Setter for the parameter "allergenics"
     *
     * @param allergenics The new set of allergenics of the recipe
     */
    public void setAllergenics(Set<Allergenic> allergenics)
    {
        this.allergenics = allergenics;
    }

    /**
     * Setter for the parameter "elaborationSteps"
     *
     * @param elaborationSteps The new List of steps of the recipe
     */
    public void setElaborationSteps(List<RecipeElaborationStep> elaborationSteps)
    {
        this.elaborationSteps = elaborationSteps;
    }

    /**
     *
     * @return
     */
    public List<RecipeIngredient> getRecipeIngredients()
    {
        return recipeIngredients;
    }

    /**
     *
     * @param recipeIngredients
     */
    public void setRecipeIngredients(List<RecipeIngredient> recipeIngredients)
    {
        this.recipeIngredients = recipeIngredients;
    }

    /**
     *
     * @return
     */
    public int getKilocalories()
    {
        return kilocalories;
    }

    /**
     *
     * @param kilocalories
     */
    public void setKilocalories(int kilocalories)
    {
        this.kilocalories = kilocalories;
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
