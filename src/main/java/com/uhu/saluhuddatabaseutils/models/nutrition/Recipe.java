package com.uhu.saluhuddatabaseutils.models.nutrition;

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
 * Represents a recipe stored in the database, composed of a list of
 * ingredients, allergens, and preparation steps. Each recipe contains a name,
 * description, ingredient details, and nutritional information such as
 * kilocalories.
 */
@Entity
@Table(name = "recipe")
public class Recipe implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name_key", nullable = false)
    @NotBlank
    @Size(min = 2, max = 200)
    private String name;

    @Column(name = "description_key")
    private String description;

    @Column(name = "ingredients_description_key")
    private String ingredientsDescription;

    @Column(name = "kilocalories")
    private int kilocalories;
    
    @Column(name = "image_source", nullable = false)
    private String imageSource;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade
            =
            {
                CascadeType.MERGE
            })
    @JoinTable(name = "RECIPE_ALLERGENIC",
            joinColumns = @JoinColumn(name = "id_recipe"),
            inverseJoinColumns = @JoinColumn(name = "id_allergenic"))
    private Set<Allergenic> allergenics;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<RecipeIngredient> recipeIngredients;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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
     * Parameterized constructor.
     *
     * @param id The unique identifier for the recipe.
     * @param name The name of the recipe.
     * @param description A description of the recipe.
     * @param ingredientsDescription A detailed description of the ingredients.
     * @param allergenics A set of allergens present in the recipe.
     * @param elaborationSteps A list of preparation steps for the recipe.
     * @param recipeIngredients A list of ingredients used in the recipe.
     * @param kilocalories The total kilocalories of the recipe.
     * @param imageSource The recipe image URL or URI.
     */
    public Recipe(long id, String name, String description,
            String ingredientsDescription,
            Set<Allergenic> allergenics, List<RecipeElaborationStep> elaborationSteps,
            List<RecipeIngredient> recipeIngredients, int kilocalories, String imageSource)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ingredientsDescription = ingredientsDescription;
        this.allergenics = allergenics;
        this.elaborationSteps = elaborationSteps;
        this.recipeIngredients = recipeIngredients;
        this.kilocalories = kilocalories;
        this.imageSource = imageSource;
    }

    /**
     * Constructor for creating a recipe without allergens and preparation steps.
     *
     * @param name The name of the recipe.
     * @param description A description of the recipe.
     * @param ingredientsDescription A detailed description of the ingredients.
     * @param kilocalories The total kilocalories of the recipe.
     * @param imageSource The recipe image URL or URI.
     */
    public Recipe(String name, String description, String ingredientsDescription,
            int kilocalories, String imageSource)
    {
        this.name = name;
        this.description = description;
        this.ingredientsDescription = ingredientsDescription;
        this.kilocalories = kilocalories;
        this.imageSource = imageSource;
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

    /**
     * Sets the ID of the recipe.
     *
     * @param id The unique identifier to set.
     */
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
     * @return The list of ingredients used in the recipe.
     */
    public List<RecipeIngredient> getRecipeIngredients()
    {
        return recipeIngredients;
    }

    /**
     * Sets the ingredients of the recipe.
     *
     * @param recipeIngredients The new list of ingredients.
     */
    public void setRecipeIngredients(List<RecipeIngredient> recipeIngredients)
    {
        this.recipeIngredients = recipeIngredients;
    }

    /**
     * @return The total kilocalories of the recipe.
     */
    public int getKilocalories()
    {
        return kilocalories;
    }

    /**
     * Sets the kilocalories of the recipe.
     *
     * @param kilocalories The new kilocalories value.
     */
    public void setKilocalories(int kilocalories)
    {
        this.kilocalories = kilocalories;
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

    /**
     * Returns this recipe's image source URL/URI.
     * 
     * @return this recipe's image source URL/URI.
     */
    public String getImageSource()
    {
        return imageSource;
    }

    /**
     * Sets this recipe's image source URL/URI.
     * 
     * @param imageSource The recipe image URL or URI.
     */
    public void setImageSource(String imageSource)
    {
        this.imageSource = imageSource;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Recipe{");
        sb.append("id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", kilocalories=").append(kilocalories);
        sb.append('}');
        return sb.toString();
    }
}
