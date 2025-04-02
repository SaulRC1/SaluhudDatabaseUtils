package com.uhu.saluhuddatabaseutils.models.nutrition;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.Version;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * Represents the relationship between a recipe and an ingredient, including the
 * quantity and unit of measurement.
 *
 * @author Juan Alberto Domínguez Vázquez
 */
@Entity
@Table(name = "RECIPE_INGREDIENT")
public class RecipeIngredient implements Serializable
{

    @EmbeddedId
    private RecipeIngredientId id;

    @ManyToOne
    @MapsId("idRecipe")
    @JoinColumn(name = "id_recipe")
    private Recipe recipe;

    @ManyToOne
    @MapsId("idIngredient")
    @JoinColumn(name = "id_ingredient")
    private Ingredient ingredient;

    @Column(name = "quantity", precision = 6, scale = 2)
    private BigDecimal quantity;

    @Column(name = "unit", length = 20)
    private String unit;

    @Version
    @Column(name = "entity_version")
    private Long version;

    /**
     * Default constructor.
     */
    public RecipeIngredient()
    {

    }

    /**
     * Constructs a new RecipeIngredient with the specified details.
     *
     * @param id The composite key identifying the recipe-ingredient
     * relationship.
     * @param recipe The recipe associated with this ingredient.
     * @param ingredient The ingredient used in the recipe.
     * @param quantity The quantity of the ingredient.
     * @param unit The unit of measurement.
     */
    public RecipeIngredient(RecipeIngredientId id, Recipe recipe, Ingredient ingredient,
            BigDecimal quantity, String unit)
    {
        this.id = id;
        this.recipe = recipe;
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.unit = unit;
    }

    /**
     * Constructs a new RecipeIngredient without specifying an ID.
     *
     * @param recipe The recipe associated with this ingredient.
     * @param ingredient The ingredient used in the recipe.
     * @param quantity The quantity of the ingredient.
     * @param unit The unit of measurement.
     */
    public RecipeIngredient(Recipe recipe, Ingredient ingredient,
            BigDecimal quantity, String unit)
    {
        this.recipe = recipe;
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.unit = unit;
    }

    /**
     * Gets the ID of the ingredient.
     *
     * @return The ingredient ID, or null if not set.
     */
    @Transient
    public Long getIngredientId()
    {
        return ingredient != null ? ingredient.getId() : null;
    }

    /**
     * Sets the ingredient ID and updates the associated ingredient reference.
     *
     * @param id The new ingredient ID.
     */
    public void setIngredientId(Long id)
    {
        if (id != null)
        {
            if (this.id == null)
            {
                this.id = new RecipeIngredientId();
            }
            // Configuramos la parte del ID compuesta para el ingrediente
            this.id.setIdIngredient(id);
            if (this.ingredient == null)
            {
                this.ingredient = new Ingredient();
            }
            this.ingredient.setId(id);
        }
    }

    /**
     * @return The composite ID of this RecipeIngredient.
     */
    public RecipeIngredientId getId()
    {
        return id;
    }

    /**
     * @param id The new composite ID for this RecipeIngredient.
     */
    public void setId(RecipeIngredientId id)
    {
        this.id = id;
    }

    /**
     * @return The recipe associated with this ingredient.
     */
    public Recipe getRecipe()
    {
        return recipe;
    }

    /**
     * @param recipe The new recipe for this RecipeIngredient.
     */
    public void setRecipe(Recipe recipe)
    {
        this.recipe = recipe;
    }

    /**
     * @return The ingredient associated with this RecipeIngredient.
     */
    public Ingredient getIngredient()
    {
        return ingredient;
    }

    /**
     * @param ingredient The new ingredient for this RecipeIngredient.
     */
    public void setIngredient(Ingredient ingredient)
    {
        this.ingredient = ingredient;
    }

    /**
     * @return The quantity of the ingredient in the recipe.
     */
    public BigDecimal getQuantity()
    {
        return quantity;
    }

    /**
     * @param quantity The new quantity of the ingredient.
     */
    public void setQuantity(BigDecimal quantity)
    {
        this.quantity = quantity;
    }

    /**
     * @return The unit of measurement for the quantity.
     */
    public String getUnit()
    {
        return unit;
    }

    /**
     * @param unit The new unit of measurement.
     */
    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    /**
     * @return The version number of the entity.
     */
    public Long getVersion()
    {
        return version;
    }

    /**
     * @param version The new version value.
     */
    public void setVersion(Long version)
    {
        this.version = version;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final RecipeIngredient other = (RecipeIngredient) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString()
    {
        return "RecipeIngredient{" + "id=" + id + ", recipe=" + recipe
                + ", ingredient=" + ingredient + ", quantity=" + quantity
                + ", unit=" + unit + ", version=" + version + '}';
    }

}
