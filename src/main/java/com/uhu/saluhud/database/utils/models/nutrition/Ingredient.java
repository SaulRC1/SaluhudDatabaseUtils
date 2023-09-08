package com.uhu.saluhud.database.utils.models.nutrition;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * This class represents the ingredients that are stored in the database An
 * ingredient have the id, a name, an amount of kilocalories, proteins, fats and
 * carbohydrates
 */
@Entity
@Table(name = "INGREDIENT")
public class Ingredient {

    @Id
    private long id;

    @Column
    private String name;

    @Column
    private int kilocalories;

    @Column
    private int protein_amount;

    @Column
    private int carbohydrates_amount;

    @Column
    private int fat_amount;

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
     * @param protein_amount
     * @param carbohydrates_amount
     * @param fat_amount
     */
    public Ingredient(long id, String name, int kilocalories, int protein_amount, int carbohydrates_amount, int fat_amount) {
        this.id = id;
        this.name = name;
        this.kilocalories = kilocalories;
        this.protein_amount = protein_amount;
        this.carbohydrates_amount = carbohydrates_amount;
        this.fat_amount = fat_amount;
    }

    /**
     *
     * @return Getter for the parameter "id"
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
    public int getProtein_amount() {
        return protein_amount;
    }

    /**
     *
     * @return Getter for the parameter "carbohydrates_amount"
     */
    public int getCarbohydrates_amount() {
        return carbohydrates_amount;
    }

    /**
     *
     * @return Getter for the parameter "fat_amount"
     */
    public int getFat_amount() {
        return fat_amount;
    }

    /**
     *
     * @param id Setter for the parameter "id"
     */
    public void setId(long id) {
        this.id = id;
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
     * @param protein_amount Setter for the parameter "protein_amount"
     */
    public void setProtein_amount(int protein_amount) {
        this.protein_amount = protein_amount;
    }

    /**
     *
     * @param carbohydrates_amount Setter for the parameter
     * "carbohydrates_amount"
     */
    public void setCarbohydrates_amount(int carbohydrates_amount) {
        this.carbohydrates_amount = carbohydrates_amount;
    }

    /**
     *
     * @param fat_amount Setter for the parameter "fat_amount"
     */
    public void setFat_amount(int fat_amount) {
        this.fat_amount = fat_amount;
    }
}
