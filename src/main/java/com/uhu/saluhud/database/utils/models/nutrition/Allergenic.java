package com.uhu.saluhud.database.utils.models.nutrition;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

/**
 * This class represents the existent allergenics present in food.
 */
@Entity
@Table(name = "allergenic")
public class Allergenic implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name", unique = true, nullable = false)
    @NotBlank
    private String name;

    @ManyToMany(mappedBy = "allergens", fetch = FetchType.EAGER)
    private List<Ingredient> ingredients;

    @Version
    @Column(name = "entity_version")
    private Long version;

    /**
     * This a default constructor for the class, with no parameters
     */
    public Allergenic()
    {

    }

    /**
     * This is a parameterized constructor for the class. It takes, the id and
     * the name of an Allergenic
     *
     * @param id The id of the Allergenic
     * @param name The name of the Allergenic
     */
    public Allergenic(long id, String name)
    {
        this.id = id;
        this.name = name;
    }

    /**
     * This is a parameterized constructor for the class.It takes, the name of
     * an Allergenic
     *
     * @param name The name of the Allergenic
     */
    public Allergenic(String name)
    {
        this.name = name;
    }

    /**
     * Getter for the parameter "id"
     *
     * @return The id of the Allergenic
     */
    public long getId()
    {
        return id;
    }

    /**
     * Getter for the parameter "name"
     *
     * @return The name of the Allergenic
     */
    public String getName()
    {
        return name;
    }

    /**
     * Setter for the parameter "name"
     *
     * @param name The new name of the Allergenic
     */
    public void setName(String name)
    {
        this.name = name;
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
     * Getter for the parameters "ingredients"
     *
     * @return the ingredients that contain the allergen
     */
    public List<Ingredient> getIngredients()
    {
        return ingredients;
    }

    /**
     * Setter for the parameters "ingredients"
     * 
     * @param ingredients the new list of ingredients that contain the allergen
     */
    public void setIngredients(List<Ingredient> ingredients)
    {
        this.ingredients = ingredients;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 29 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

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
        final Allergenic other = (Allergenic) obj;
        return this.id == other.id;
    }
    
}
