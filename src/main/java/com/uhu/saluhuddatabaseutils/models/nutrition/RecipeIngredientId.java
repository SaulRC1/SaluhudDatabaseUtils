package com.uhu.saluhuddatabaseutils.models.nutrition;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
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
     *
     */
    public RecipeIngredientId()
    {

    }

    /**
     *
     * @param idIngredient
     * @param idRecipe
     */
    public RecipeIngredientId(long idIngredient, long idRecipe)
    {
        this.idIngredient = idIngredient;
        this.idRecipe = idRecipe;
    }

    /**
     *
     * @return
     */
    public long getIdIngredient()
    {
        return idIngredient;
    }

    /**
     *
     * @param idIngredient
     */
    public void setIdIngredient(long idIngredient)
    {
        this.idIngredient = idIngredient;
    }

    /**
     *
     * @return
     */
    public long getIdRecipe()
    {
        return idRecipe;
    }

    /**
     *
     * @param idRecipe
     */
    public void setIdRecipe(long idRecipe)
    {
        this.idRecipe = idRecipe;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.idIngredient);
        hash = 17 * hash + Objects.hashCode(this.idRecipe);
        return hash;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RecipeIngredientId other = (RecipeIngredientId) obj;
        if (!Objects.equals(this.idIngredient, other.idIngredient)) {
            return false;
        }
        return Objects.equals(this.idRecipe, other.idRecipe);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString()
    {
        return "RecipeIngredientId{" + "idIngredient=" + idIngredient + ", idRecipe=" + idRecipe + '}';
    }

}
