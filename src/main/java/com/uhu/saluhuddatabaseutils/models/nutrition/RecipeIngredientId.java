package com.uhu.saluhuddatabaseutils.models.nutrition;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Represents the composite primary key for the RecipeIngredient entity. This
 * class is marked as @Embeddable to be used as an embedded ID.
 *
 * @author Juan Alberto Domínguez Vázquez
 */
@Embeddable
public class RecipeIngredientId implements Serializable
{

    @Column(name = "id_ingredient")
    private long idIngredient;

    @Column(name = "id_recipe")
    private long idRecipe;

    /**
     * Default constructor.
     */
    public RecipeIngredientId()
    {

    }

    /**
     * Constructs a new RecipeIngredientId with the specified ingredient and
     * recipe IDs.
     *
     * @param idIngredient The ID of the ingredient.
     * @param idRecipe The ID of the recipe.
     */
    public RecipeIngredientId(long idIngredient, long idRecipe)
    {
        this.idIngredient = idIngredient;
        this.idRecipe = idRecipe;
    }

    /**
     * Gets the ingredient ID.
     *
     * @return The ingredient ID.
     */
    public long getIdIngredient()
    {
        return idIngredient;
    }

    /**
     * Sets the ingredient ID.
     *
     * @param idIngredient The new ingredient ID.
     */
    public void setIdIngredient(long idIngredient)
    {
        this.idIngredient = idIngredient;
    }

    /**
     * Gets the recipe ID.
     *
     * @return The recipe ID.
     */
    public long getIdRecipe()
    {
        return idRecipe;
    }

    /**
     * Sets the recipe ID.
     *
     * @param idRecipe The new recipe ID.
     */
    public void setIdRecipe(long idRecipe)
    {
        this.idRecipe = idRecipe;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.idIngredient);
        hash = 17 * hash + Objects.hashCode(this.idRecipe);
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
        final RecipeIngredientId other = (RecipeIngredientId) obj;
        if (!Objects.equals(this.idIngredient, other.idIngredient))
        {
            return false;
        }
        return Objects.equals(this.idRecipe, other.idRecipe);
    }

    @Override
    public String toString()
    {
        return "RecipeIngredientId{" + "idIngredient=" + idIngredient + ", idRecipe=" + idRecipe + '}';
    }

}
