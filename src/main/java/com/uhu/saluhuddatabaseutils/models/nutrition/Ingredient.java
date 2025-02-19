package com.uhu.saluhuddatabaseutils.models.nutrition;

import jakarta.persistence.CascadeType;
import java.io.Serializable;
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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import java.util.Set;
import org.hibernate.validator.constraints.Range;

/**
 *
 * This class represents the ingredients that are stored in the database. An
 * ingredient have the id, a name, an amount of kilocalories, proteins, fats and
 * carbohydrates
 */
@Entity
@Table(name = "ingredient")
public class Ingredient implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name", unique = true, nullable = false)
    @NotBlank
    @Size(min = 2, max = 60)
    private String name;

    @Column(name = "kilocalories", nullable = false)
    @NotNull
    @Range(min = 0, max = 9999)
    private int kilocalories;

    @Column(name = "protein_amount", nullable = false)
    @NotNull
    @Range(min = 0, max = 9999)
    private int proteinAmount;

    @Column(name = "carbohydrates_amount", nullable = false)
    @NotNull
    @Range(min = 0, max = 9999)
    private int carbohydratesAmount;

    @Column(name = "fat_amount", nullable = false)
    @NotNull
    @Range(min = 0, max = 9999)
    private int fatAmount;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade
            = {
                CascadeType.MERGE
            })
    @JoinTable(
            name = "INGREDIENT_ALLERGIC",
            joinColumns = @JoinColumn(name = "id_ingredient"),
            inverseJoinColumns = @JoinColumn(name = "id_allergenic")
    )
    private Set<Allergenic> allergens;

    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<RecipeIngredient> recipeIngredients;

    @Version
    @Column(name = "entity_version")
    private Long version;

    /**
     * This a default constructor for the class, with no parameters
     */
    public Ingredient()
    {

    }

    /**
     * This is a parameterized constructor for the class. It takes, the id, the
     * name, the kilocalories amount, the protein amount, the carbohydrates
     * amount and the fat amount
     *
     * @param id The id of the ingredient
     * @param name The name of the ingredient
     * @param kilocalories The amount of kilocalories of an ingredient
     * @param proteinAmount The amount of protein of an ingredient
     * @param carbohydratesAmount The carbohydrates of an ingredient
     * @param fatAmount The amount of fat of an ingredient
     */
    public Ingredient(long id, String name, int kilocalories, int proteinAmount, int carbohydratesAmount, int fatAmount)
    {
        this.id = id;
        this.name = name;
        this.kilocalories = kilocalories;
        this.proteinAmount = proteinAmount;
        this.carbohydratesAmount = carbohydratesAmount;
        this.fatAmount = fatAmount;
    }

    /**
     * This is a parameterized constructor for the class. It takes, the name,
     * the kilocalories amount, the protein amount, the carbohydrates amount and
     * the fat amount
     *
     * @param name The name of the ingredient
     * @param kilocalories The amount of kilocalories of an ingredient
     * @param proteinAmount The amount of protein of an ingredient
     * @param carbohydratesAmount The carbohydrates of an ingredient
     * @param fatAmount The amount of fat of an ingredient
     */
    public Ingredient(String name, int kilocalories, int proteinAmount, int carbohydratesAmount, int fatAmount)
    {
        this.name = name;
        this.kilocalories = kilocalories;
        this.proteinAmount = proteinAmount;
        this.carbohydratesAmount = carbohydratesAmount;
        this.fatAmount = fatAmount;
    }

    /**
     * This is a parameterized constructor for the class. It takes, the name,
     * the kilocalories amount, the protein amount, the carbohydrates amount,
     * the fat amount and his allergens
     *
     * @param name The name of the ingredient
     * @param kilocalories The amount of kilocalories of an ingredient
     * @param proteinAmount The amount of protein of an ingredient
     * @param carbohydratesAmount The carbohydrates of an ingredient
     * @param fatAmount The amount of fat of an ingredient
     * @param allergens The set of allergens of an ingredient
     */
    public Ingredient(String name, int kilocalories, int proteinAmount, int carbohydratesAmount, int fatAmount, Set<Allergenic> allergens)
    {
        this.name = name;
        this.kilocalories = kilocalories;
        this.proteinAmount = proteinAmount;
        this.carbohydratesAmount = carbohydratesAmount;
        this.fatAmount = fatAmount;
        this.allergens = allergens;
    }

    /**
     * Getter for the parameter "id"
     *
     * @return The id of an ingredient
     */
    public long getId()
    {
        return id;
    }

    /**
     * Getter for the parameter "name"
     *
     * @return The name of an ingredient
     */
    public String getName()
    {
        return name;
    }

    /**
     * Getter for the parameter "kilocalories"
     *
     * @return The number of kilocalories of an ingredient
     */
    public int getKilocalories()
    {
        return kilocalories;
    }

    /**
     * Getter for the parameter "protein_amount"
     *
     * @return The amount of proteins of an ingredient
     */
    public int getProteinAmount()
    {
        return proteinAmount;
    }

    /**
     * Getter for the parameter "carbohydrates_amount"
     *
     * @return The amount of carbohydrates of an ingredient
     */
    public int getCarbohydratesAmount()
    {
        return carbohydratesAmount;
    }

    /**
     * Getter for the parameter "fat_amount"
     *
     * @return The amount of fat of an ingredient
     */
    public int getFatAmount()
    {
        return fatAmount;
    }

    /**
     * Setter for the parameter "id"
     *
     * @param id The id of an ingredient
     */
    public void setId(long id)
    {
        this.id = id;
    }

    /**
     * Setter for the parameter "name"
     *
     * @param name The new name of the ingredient
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Setter for the parameter "kilocalories"
     *
     * @param kilocalories The new amount of kilocaries of an ingredient
     */
    public void setKilocalories(int kilocalories)
    {
        this.kilocalories = kilocalories;
    }

    /**
     * Setter for the parameter "protein_amount"
     *
     * @param proteinAmount The new amount of proteins of an ingredient
     */
    public void setProteinAmount(int proteinAmount)
    {
        this.proteinAmount = proteinAmount;
    }

    /**
     * Setter for the parameter "carbohydrates_amount"
     *
     * @param carbohydratesAmount The new amount of carbohydrates of an
     * ingredient
     */
    public void setCarbohydratesAmount(int carbohydratesAmount)
    {
        this.carbohydratesAmount = carbohydratesAmount;
    }

    /**
     * Setter for the parameter "fat_amount"
     *
     * @param fatAmount The new amount of fats of an ingredient
     */
    public void setFatAmount(int fatAmount)
    {
        this.fatAmount = fatAmount;
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
    @Override
    public String toString()
    {
        return "Ingredient[ id=" + this.id + " ]";
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 71 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Ingredient other = (Ingredient) obj;
        return this.id == other.id;
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
     * Getter for the parameter "allergens"
     *
     * @return the allergens of the ingredient
     */
    public Set<Allergenic> getAllergens()
    {
        return allergens;
    }

    /**
     * Setter for the parameter "allergens"
     *
     * @param allergens the new allergens of the ingredient
     */
    public void setAllergens(Set<Allergenic> allergens)
    {
        this.allergens = allergens;
    }

}
