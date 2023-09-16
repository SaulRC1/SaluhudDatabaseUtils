package com.uhu.saluhud.database.utils.models.nutrition;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * This class represents the ingredients that are stored in the database. An
 * ingredient have the id, a name, an amount of kilocalories, proteins, fats and
 * carbohydrates
 */
@Entity
@Table(name = "INGREDIENT")
public class Ingredient {

    @Id
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
    public Ingredient() {

    }

    /**
     * This is a parameterized constructor for the class. It takes, the id, the
     * name, the kilocalories amount, the protein amount, the carbohydrates
     * amount and the fat amount
     *
     * @param id
     * @param name
     * @param kilocalories
     * @param proteinAmount
     * @param carbohydratesAmount
     * @param fatAmount
     */
    public Ingredient(long id, String name, int kilocalories, int proteinAmount, int carbohydratesAmount, int fatAmount) {
        this.id = id;
        this.name = name;
        this.kilocalories = kilocalories;
        this.proteinAmount = proteinAmount;
        this.carbohydratesAmount = carbohydratesAmount;
        this.fatAmount = fatAmount;
    }

    /**
     * Getter for the parameter "id"
     * 
     * @return 
     */
    public long getId() {
        return id;
    }

    /**
     *
     * @return Getter for the parameter "name"
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return Getter for the parameter "kilocalories"
     */
    public int getKilocalories() {
        return kilocalories;
    }

    /**
     *
     * @return Getter for the parameter "protein_amount"
     */
    public int getProteinAmount() {
        return proteinAmount;
    }

    /**
     *
     * @return Getter for the parameter "carbohydrates_amount"
     */
    public int getCarbohydratesAmount() {
        return carbohydratesAmount;
    }

    /**
     *
     * @return Getter for the parameter "fat_amount"
     */
    public int getFatAmount() {
        return fatAmount;
    }

    /**
     *
     * @param name Setter for the parameter "name"
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param kilocalories Setter for the parameter "kilocalories"
     */
    public void setKilocalories(int kilocalories) {
        this.kilocalories = kilocalories;
    }

    /**
     *
     * @param proteinAmount Setter for the parameter "protein_amount"
     */
    public void setProtein_amount(int proteinAmount) {
        this.proteinAmount = proteinAmount;
    }

    /**
     *
     * @param carbohydratesAmount Setter for the parameter
     * "carbohydrates_amount"
     */
    public void setCarbohydrates_amount(int carbohydratesAmount) {
        this.carbohydratesAmount = carbohydratesAmount;
    }

    /**
     *
     * @param fatAmount Setter for the parameter "fat_amount"
     */
    public void setFat_amount(int fatAmount) {
        this.fatAmount = fatAmount;
    }
}
