package com.uhu.saluhuddatabaseutils.models.user;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

/**
 * This class represents the fitness information of a user stored in the
 * database. It includes attributes such as weight, height, biological sex, age,
 * body composition, recommended daily water intake, sleep hours, daily steps,
 * kilocalorie objective, and body mass index. The class is linked to the
 * `SaluhudUser` entity, representing the user to which the fitness data
 * belongs.
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
    @DecimalMin("0")
    private double weight;

    @Column(name = "height")
    @DecimalMin("0")
    private double height;

    @Column(name = "biological_sex")
    @Convert(converter = BiologicalSexConverter.class)
    private BiologicalSex biologicalSex;

    @Column(name = "age")
    @Range(min = 16, max = 110)
    private int age;

    @Embedded
    private BodyComposition bodyComposition;
    
    @Column(name = "activity_factor")
    @Convert(converter = HarrisBenedictBMRActivityFactorConverter.class)
    private HarrisBenedictBMRActivityFactor activityFactor;

    @Column(name = "recommended_daily_water_liters")
    @Range(min = 2, max = 6)
    private int recommendedDailyWaterLiters;

    @Column(name = "recommended_sleep_hours")
    @Range(min = 6, max = 12)
    private int recommendedSleepHours;

    @Column(name = "recommended_daily_steps")
    @Range(min = 5000, max = 100000)
    private int recommendedDailySteps;

    @Column(name = "daily_kilocalories_objective")
    @Range(min = 1500, max = 5000)
    private int dailyKilocaloriesObjective;

    @Column(name = "body_mass_index")
    @DecimalMin("0")
    private double bodyMassIndex;

    @OneToOne(mappedBy = "fitnessData", fetch = FetchType.LAZY)
    private SaluhudUser saluhudUser;

    @Version
    @Column(name = "entity_version")
    private Long version;

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
     * @param user the user where the information belongs to
     * @param activityFactor The Harris-Benedict activity factor.
     */
    public SaluhudUserFitnessData(double weight, double height, BiologicalSex biologicalSex,
            int age, BodyComposition bodyComposition, int recommendedDailyWaterLiters,
            int recommendedSleepHours, int recommendedDailySteps,
            int dailyKilocaloriesObjective, double bodyMassIndex, SaluhudUser user,
            HarrisBenedictBMRActivityFactor activityFactor)
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
        this.saluhudUser = user;
        this.activityFactor = activityFactor;
    }

    /**
     *
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
     */
    public SaluhudUserFitnessData(double weight, double height, BiologicalSex biologicalSex,
            int age, BodyComposition bodyComposition, int recommendedDailyWaterLiters,
            int recommendedSleepHours, int recommendedDailySteps,
            int dailyKilocaloriesObjective, double bodyMassIndex)
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
    public BiologicalSex getBiologicalSex()
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
    public BodyComposition getBodyComposition()
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
    public int getDailyKilocaloriesObjective()
    {
        return dailyKilocaloriesObjective;
    }

    /**
     * Getter for the parameter "body mass index"
     *
     * @return the body mass index for the user
     */
    public double getBodyMassIndex()
    {
        return bodyMassIndex;
    }

    /**
     * Setter for the parameter "id"
     *
     * @param id the id of the user fitness data
     */
    public void setId(long id)
    {
        this.id = id;
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
    public void setBiologicalSex(BiologicalSex biologicalSex)
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
    public void setBodyComposition(BodyComposition bodyComposition)
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
    public void setBodyMassIndex(double bodyMassIndex)
    {
        this.bodyMassIndex = bodyMassIndex;
    }

    /**
     * Getter for the version field.
     *
     * @return the version of the entity
     */
    public Long getVersion()
    {
        return version;
    }

    /**
     * Setter for the version field.
     *
     * @param version the new version of the entity
     */
    public void setVersion(Long version)
    {
        this.version = version;
    }

    /**
     * Getter for the parameter "saluhudUser"
     *
     * @return the saluhud user
     */
    public SaluhudUser getSaluhudUser()
    {
        return saluhudUser;
    }

    /**
     * Setter for the parameter "saluhudUser"
     *
     * @param saluhudUser the new saluhudUser
     */
    public void setSaluhudUser(SaluhudUser saluhudUser)
    {
        this.saluhudUser = saluhudUser;
    }

    public HarrisBenedictBMRActivityFactor getActivityFactor()
    {
        return activityFactor;
    }

    public void setActivityFactor(HarrisBenedictBMRActivityFactor activityFactor)
    {
        this.activityFactor = activityFactor;
    }
}
