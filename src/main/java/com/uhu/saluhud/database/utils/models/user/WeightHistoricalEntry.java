package com.uhu.saluhud.database.utils.models.user;

import java.io.Serializable;
import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

/**
 * This class represents one entry in the weight historical stored in the
 * database.
 *
 * @author Juan Alberto Dominguez Vazquez
 */
@Entity
@Table(name = "weight_historical_entry")
public class WeightHistoricalEntry implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "entry_date", nullable = false)
    @NotNull
    private LocalDate entryDate;

    @Column(name = "weight_entry", nullable = false)
    @NotNull
    @Range(min = 40, max = 200)
    private double weightEntry;

    @Column(name = "height_entry", nullable = false)
    @NotNull
    @Range(min = 120, max = 240)
    private double heightEntry;

    @Column(name = "kilo_calories_objective_entry", nullable = false)
    @NotNull
    @Range(min = 1500, max = 4000)
    private double kilocaloriesObjectiveEntry;

    @ManyToOne
    @JoinColumn(name = "weight_historical_id", nullable = false)
    @NotNull
    private WeightHistorical weightHistorical;
    
    @Version
    @Column(name = "entity_version")
    private Long version;

    /**
     * This a default constructor for the class, with no parameters
     */
    public WeightHistoricalEntry()
    {
    }

    /**
     * This is a parameterized constructor for the class. It takes, the entry
     * date, the weight entry, the height entry, the kilocalories objective
     * entry and the weight historical of the user
     *
     * @param entryDate the entry date
     * @param weightEntry the weight of the user
     * @param heightEntry the height of the user
     * @param kilocaloriesObjectiveEntry the kilocalories objective of the user
     * @param weightHistorical the weight historical which the entry belongs to
     */
    public WeightHistoricalEntry(LocalDate entryDate, double weightEntry,
            double heightEntry, double kilocaloriesObjectiveEntry,
            WeightHistorical weightHistorical)
    {
        this.entryDate = entryDate;
        this.weightEntry = weightEntry;
        this.heightEntry = heightEntry;
        this.kilocaloriesObjectiveEntry = kilocaloriesObjectiveEntry;
        this.weightHistorical = weightHistorical;
    }

    /**
     * Getter for the parameter "id"
     *
     * @return the id of the entry
     */
    public long getId()
    {
        return id;
    }

    /**
     * Setter for the parameter "id"
     * 
     * @param id the id of the entry
     */
    public void setId(long id)
    {
        this.id = id;
    }

    /**
     * Getter for the parameter "entryDate"
     *
     * @return the entry date
     */
    public LocalDate getEntryDate()
    {
        return entryDate;
    }

    /**
     * Setter for the parameter "entryDate"
     *
     * @param entryDate the new entry date
     */
    public void setEntryDate(LocalDate entryDate)
    {
        this.entryDate = entryDate;
    }

    /**
     * Getter for the parameter "weightEntry"
     *
     * @return the weight entry
     */
    public double getWeightEntry()
    {
        return weightEntry;
    }

    /**
     * Setter for the parameter "weightEntry"
     *
     * @param weightEntry the new weight entry
     */
    public void setWeightEntry(double weightEntry)
    {
        this.weightEntry = weightEntry;
    }

    /**
     * Getter for the parameter "heightEntry"
     *
     * @return the height entry
     */
    public double getHeightEntry()
    {
        return heightEntry;
    }

    /**
     * Setter for the parameter "heightEntry"
     *
     * @param heightEntry the height entry
     */
    public void setHeightEntry(double heightEntry)
    {
        this.heightEntry = heightEntry;
    }

    /**
     * Getter for the parameter "kilocaloriesObjectiveEntry"
     *
     * @return the kilocalories objective entry for the user
     */
    public double getKilocaloriesObjectiveEntry()
    {
        return kilocaloriesObjectiveEntry;
    }

    /**
     * Setter for the parameter "kilocaloriesObjectiveEntry"
     *
     * @param kilocaloriesObjectiveEntry the new kilocalories objective entry
     * for the user
     */
    public void setKilocaloriesObjectiveEntry(double kilocaloriesObjectiveEntry)
    {
        this.kilocaloriesObjectiveEntry = kilocaloriesObjectiveEntry;
    }

    /**
     * Getter for the parameter "weightHistorical"
     *
     * @return the weight historical which the entry belongs to
     */
    public WeightHistorical getWeightHistorical()
    {
        return weightHistorical;
    }

    /**
     * Setter for the parameter "weightHistorical"
     * 
     * @param weightHistorical the new weight historical which the entry belongs to
     */
    public void setWeightHistorical(WeightHistorical weightHistorical)
    {
        this.weightHistorical = weightHistorical;
    }

    public Long getVersion()
    {
        return version;
    }

    public void setVersion(Long version)
    {
        this.version = version;
    }
    
}
