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
     *
     */
    public RecipeIngredient()
    {

    }

    /**
     *
     * @param id
     * @param recipe
     * @param ingredient
     * @param quantity
     * @param unit
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
     *
     * @param recipe
     * @param ingredient
     * @param quantity
     * @param unit
     */
    public RecipeIngredient(Recipe recipe, Ingredient ingredient,
            BigDecimal quantity, String unit)
    {
        this.recipe = recipe;
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.unit = unit;
    }

    @Transient
    public Long getIngredientId()
    {
        return ingredient != null ? ingredient.getId() : null;
    }

    public void setIngredientId(Long id)
    {
        if (id != null) {
            if (this.id == null) {
                this.id = new RecipeIngredientId();
            }
            // Configuramos la parte del ID compuesta para el ingrediente
            this.id.setIdIngredient(id);
            if (this.ingredient == null) {
                this.ingredient = new Ingredient();
            }
            this.ingredient.setId(id);
        }
    }

    /**
     *
     * @return
     */
    public RecipeIngredientId getId()
    {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(RecipeIngredientId id)
    {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public Recipe getRecipe()
    {
        return recipe;
    }

    /**
     *
     * @param recipe
     */
    public void setRecipe(Recipe recipe)
    {
        this.recipe = recipe;
    }

    /**
     *
     * @return
     */
    public Ingredient getIngredient()
    {
        return ingredient;
    }

    /**
     *
     * @param ingredient
     */
    public void setIngredient(Ingredient ingredient)
    {
        this.ingredient = ingredient;
    }

    /**
     *
     * @return
     */
    public BigDecimal getQuantity()
    {
        return quantity;
    }

    /**
     *
     * @param quantity
     */
    public void setQuantity(BigDecimal quantity)
    {
        this.quantity = quantity;
    }

    /**
     *
     * @return
     */
    public String getUnit()
    {
        return unit;
    }

    /**
     *
     * @param unit
     */
    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    /**
     *
     * @return
     */
    public Long getVersion()
    {
        return version;
    }

    /**
     *
     * @param version
     */
    public void setVersion(Long version)
    {
        this.version = version;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
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
