package com.uhu.saluhud.database.utils.models.user;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * This class represents the fitness information of an user stored in the
 * database
 *
 * @author Juan Alberto Dominguez Vazquez
 */
@Entity
@Table(name = "saluhud_user_fitness_data")
public class SaluhudUserFitnessData implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "weight")
    private double weight;

    @Column(name = "height")
    private double height;

    @Column(name = "biological_sex")
    private String biologicalSex;

    @Column(name = "age")
    private int age;

    @Column(name = "body_composition")
    private String bodyComposition;

    @Column(name = "recommended_daily_water_liters")
    private int recommendedDailyWaterLiters;

    @Column(name = "recommended_sleep_hours")
    private int recommendedSleepHours;

    @Column(name = "recommended_daily_steps")
    private int recommendedDailySteps;

    @Column(name = "daily_kilocalories_objective")
    private int dailyKilocaloriesObjective;

    @Column(name = "body_mass_index")
    private String bodyMassIndex;

    /**
     * This a default constructor for the class, with no parameters
     */
    public SaluhudUserFitnessData()
    {
    }

    /**
     * This is a parameterized constructor for the class.It takes, the weight of
     * the user, his height, his sex, his age, his body composition that is
     * composed for fatMass, leanMass, boneMass, waterMass and muscleMass, the
     * recommended daily water liters, the recommended sleep hours, the
     * recommended daily steps, the daily kilocalorie objective, the body mass
     * index calculated with in information of the user and the saluhud user
     * which the information belongs to
     *
     * @param weight the weight of the user in kilograms
     * @param height the height of the user in centimetres
     * @param biologicalSex the sex of the user, woman or men
     * @param age the age of the user
     * @param bodyComposition the body composition of the user
     * @param recommendedDailyWaterLiters the recommended daily water liters for
     * the user
     * @param recommendedSleepHours the recommended sleep hours for the user
     * @param recommendedDailySteps the recommended daily steps for the user
     * @param dailyKilocaloriesObjective the daily kilocalorue objective for the
     * user
     * @param bodyMassIndex the body mass index for the user
     * 
     */
    public SaluhudUserFitnessData(double weight, double height, String biologicalSex,
            int age, String bodyComposition, int recommendedDailyWaterLiters,
            int recommendedSleepHours, int recommendedDailySteps,
            int dailyKilocaloriesObjective, String bodyMassIndex)
    {
        this.weight = weight;
        this.height = height;
        this.biologicalSex = biologicalSex;
        this.age = age;
        this.bodyComposition = bodyComposition;
        this.recommendedDailyWaterLiters = recommendedDailyWaterLiters;
        this.recommendedSleepHours = recommendedSleepHours;
        this.recommendedDailySteps = recommendedDailySteps;
        this.dailyKilocaloriesObjective = dailyKilocaloriesObjective;
        this.bodyMassIndex = bodyMassIndex;
    }

    /**
     * Getter for the parameter "id"
     *
     * @return the id of the user fitness data
     */
    public long getId()
    {
        return id;
    }

    /**
     * Getter for the parameter "weight"
     *
     * @return the weight of the user
     */
    public double getWeight()
    {
        return weight;
    }

    /**
     * Getter for the parameter "height"
     *
     * @return the weight of the user
     */
    public double getHeight()
    {
        return height;
    }

    /**
     * Getter for the parameter "biologicalSex"
     *
     * @return the biological sex of the user
     */
    public String getBiologicalSex()
    {
        return biologicalSex;
    }

    /**
     * Getter for the parameter "age"
     *
     * @return the age of the user
     */
    public int getAge()
    {
        return age;
    }

    /**
     * Getter for the parameter "bodyComposition"
     *
     * @return the body composition of the user
     */
    public String getBodyComposition()
    {
        return bodyComposition;
    }

    /**
     * Getter for the parameter "recommendedDailyWaterLiters"
     *
     * @return the recommended daily water liters for the user
     */
    public int getRecommendedDailyWaterLiters()
    {
        return recommendedDailyWaterLiters;
    }

    /**
     * Getter for the parameter "recommended sleep hours"
     *
     * @return the recommended sleep hours for the user
     */
    public int getRecommendedSleepHours()
    {
        return recommendedSleepHours;
    }

    /**
     * Getter for the parameter "recommended daily steps"
     *
     * @return the recommended daily steps for the user
     */
    public int getRecommendedDailySteps()
    {
        return recommendedDailySteps;
    }

    /**
     * Getter for the parameter "daily kilocalorie objective"
     *
     * @return the daily kilocalorie objective for the user
     */
    public int getdailyKilocalorieObjective()
    {
        return dailyKilocaloriesObjective;
    }

    /**
     * Getter for the parameter "body mass index"
     *
     * @return the body mass index for the user
     */
    public String getBodyMassIndex()
    {
        return bodyMassIndex;
    }

    /**
     * Setter for the parameter "weight"
     *
     * @param weight the new weight of the user
     */
    public void setWeight(double weight)
    {
        this.weight = weight;
    }

    /**
     * Setter for the parameter "height"
     *
     * @param height the new height of the user
     */
    public void setHeight(double height)
    {
        this.height = height;
    }

    /**
     * Setter for the parameter "biologicalSex"
     *
     * @param biologicalSex the new biological sex of the user
     */
    public void setBiologicalSex(String biologicalSex)
    {
        this.biologicalSex = biologicalSex;
    }

    /**
     * Setter for the parameter "age"
     *
     * @param age the new age of the user
     */
    public void setAge(int age)
    {
        this.age = age;
    }

    /**
     * Setter for the parameter "bodyComposition"
     *
     * @param bodyComposition the new body composition of the user
     */
    public void setBodyComposition(String bodyComposition)
    {
        this.bodyComposition = bodyComposition;
    }

    /**
     * Setter for the parameter "recommendedDailyWaterLiters"
     *
     * @param recommendedDailyWaterLiters the new recommended daily water liters
     * for the user
     */
    public void setRecommendedDailyWaterLiters(int recommendedDailyWaterLiters)
    {
        this.recommendedDailyWaterLiters = recommendedDailyWaterLiters;
    }

    /**
     * Setter for the parameter "recommendedSleepHours"
     *
     * @param recommendedSleepHours the new recommended sleep hours for the user
     */
    public void setRecommendedSleepHours(int recommendedSleepHours)
    {
        this.recommendedSleepHours = recommendedSleepHours;
    }

    /**
     * Setter for the parameter "recommendedDailySteps"
     *
     * @param recommendedDailySteps the new recommended daily steps for the user
     */
    public void setRecommendedDailySteps(int recommendedDailySteps)
    {
        this.recommendedDailySteps = recommendedDailySteps;
    }

    /**
     * Setter for the parameter "dailyKilocaloriesObjective"
     *
     * @param dailyKilocaloriesObjective the new daily kilocalorie objective for
     * the user
     */
    public void setDailyKilocaloriesObjective(int dailyKilocaloriesObjective)
    {
        this.dailyKilocaloriesObjective = dailyKilocaloriesObjective;
    }

    /**
     * Setter for the parameter "bodyMassIndex"
     *
     * @param bodyMassIndex the new body mass index for the user
     */
    public void setBodyMassIndex(String bodyMassIndex)
    {
        this.bodyMassIndex = bodyMassIndex;
    }
}
