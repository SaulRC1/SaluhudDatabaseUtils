package com.uhu.saluhud.database.utils.models.nutrition;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
    private String name;

    @Column(name = "kilocalories", nullable = false)
    private int kilocalories;

    @Column(name = "protein_amount", nullable = false)
    private int proteinAmount;

    @Column(name = "carbohydrates_amount", nullable = false)
    private int carbohydratesAmount;

    @Column(name = "fat_amount", nullable = false)
    private int fatAmount;

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
     * This is a parameterized constructor for the class. It takes, the
     * name, the kilocalories amount, the protein amount, the carbohydrates
     * amount and the fat amount
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
    public void setProtein_amount(int proteinAmount)
    {
        this.proteinAmount = proteinAmount;
    }

    /**
     * Setter for the parameter "carbohydrates_amount"
     *
     * @param carbohydratesAmount The new amount of carbohydrates of an
     * ingredient
     */
    public void setCarbohydrates_amount(int carbohydratesAmount)
    {
        this.carbohydratesAmount = carbohydratesAmount;
    }

    /**
     * Setter for the parameter "fat_amount"
     *
     * @param fatAmount The new amount of fats of an ingredient
     */
    public void setFat_amount(int fatAmount)
    {
        this.fatAmount = fatAmount;
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

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof Ingredient))
        {
            return false;
        }
        Ingredient other = (Ingredient) obj;
        if (this.id != other.id)
        {
            return false;
        }
        return true;
    }
}
