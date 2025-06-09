package com.uhu.saluhuddatabaseutils.models.user;

import java.io.Serializable;
import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;

/**
 * This class represents one entry in the historical daily steps stored in the
 * database. Each entry records the date, the number of steps taken, the
 * calories burned, and a step evaluation.
 *
 * @author Juan Alberto Dominguez Vazquez
 */
@Entity
@Table(name = "daily_steps_historical_entry")
public class DailyStepsHistoricalEntry implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "entry_date", nullable = false)
    @NotNull
    private LocalDate entryDate;

    @Column(name = "done_steps", nullable = false)
    @NotNull
    private int doneSteps;

    @Column(name = "kilo_calories_burned", nullable = false)
    @NotNull
    private double kiloCaloriesBurned;

    @Convert(converter = HistoricalEvaluationConverter.class)
    @Column(name = "steps_evaluation", nullable = false)
    @NotNull
    private HistoricalEvaluation stepEvaluation;

    @ManyToOne
    @JoinColumn(name = "daily_steps_historical_id", nullable = false)
    @NotNull
    private DailyStepsHistorical dailyStepsHistorical;

    @Version
    @Column(name = "entity_version")
    private Long version;

    /**
     * This a default constructor for the class, with no parameters
     */
    public DailyStepsHistoricalEntry()
    {
    }

    /**
     * Parameterized constructor for the class. It creates a new daily steps
     * entry with the provided details.
     *
     * @param entryDate The date of the entry.
     * @param doneSteps The number of steps taken.
     * @param kiloCaloriesBurned The number of kilocalories burned.
     * @param stepEvaluation The evaluation of the steps taken.
     * @param dailyStepsHistorical The historical record this entry belongs to.
     */
    public DailyStepsHistoricalEntry(LocalDate entryDate, int doneSteps,
            double kiloCaloriesBurned, HistoricalEvaluation stepEvaluation,
            DailyStepsHistorical dailyStepsHistorical)
    {
        this.entryDate = entryDate;
        this.doneSteps = doneSteps;
        this.kiloCaloriesBurned = kiloCaloriesBurned;
        this.stepEvaluation = stepEvaluation;
        this.dailyStepsHistorical = dailyStepsHistorical;
    }

    /**
     * Gets the ID of the daily steps entry.
     *
     * @return The ID of the daily steps entry.
     */
    public long getId()
    {
        return id;
    }

    /**
     * Sets the ID of the daily steps entry.
     *
     * @param id The new ID for the daily steps entry.
     */
    public void setId(long id)
    {
        this.id = id;
    }

    /**
     * Gets the entry date for the daily steps.
     *
     * @return The date of the daily steps entry.
     */
    public LocalDate getEntryDate()
    {
        return entryDate;
    }

    /**
     * Sets the entry date for the daily steps.
     *
     * @param entryDate The new entry date.
     */
    public void setEntryDate(LocalDate entryDate)
    {
        this.entryDate = entryDate;
    }

    /**
     * Gets the number of steps taken for this entry.
     *
     * @return The number of steps completed.
     */
    public int getDoneSteps()
    {
        return doneSteps;
    }

    /**
     * Sets the number of steps taken for this entry.
     *
     * @param doneSteps The new number of steps taken.
     */
    public void setDoneSteps(int doneSteps)
    {
        this.doneSteps = doneSteps;
    }

    /**
     * Gets the number of kilocalories burned in this entry.
     *
     * @return The amount of kilocalories burned.
     */
    public double getKiloCaloriesBurned()
    {
        return kiloCaloriesBurned;
    }

    /**
     * Sets the number of kilocalories burned in this entry.
     *
     * @param kiloCaloriesBurned The new amount of kilocalories burned.
     */
    public void setKiloCaloriesBurned(double kiloCaloriesBurned)
    {
        this.kiloCaloriesBurned = kiloCaloriesBurned;
    }

    /**
     * Gets the step evaluation for this entry.
     *
     * @return The evaluation of the steps performed.
     */
    public HistoricalEvaluation getStepEvaluation()
    {
        return stepEvaluation;
    }

    /**
     * Sets the step evaluation for this entry.
     *
     * @param stepEvaluation The new evaluation of the steps taken.
     */
    public void setStepEvaluation(HistoricalEvaluation stepEvaluation)
    {
        this.stepEvaluation = stepEvaluation;
    }

    /**
     * Gets the historical record this entry belongs to.
     *
     * @return The associated historical record.
     */
    public DailyStepsHistorical getDailyStepsHistorical()
    {
        return dailyStepsHistorical;
    }

    /**
     * Sets the historical record this entry belongs to.
     *
     * @param dailyStepsHistorical The new historical record.
     */
    public void setDailyStepsHistorical(DailyStepsHistorical dailyStepsHistorical)
    {
        this.dailyStepsHistorical = dailyStepsHistorical;
    }

    /**
     * Gets the version of the entity.
     *
     * @return The version number.
     */
    public Long getVersion()
    {
        return version;
    }

    /**
     * Sets the version of the entity.
     *
     * @param version The new version number.
     */
    public void setVersion(Long version)
    {
        this.version = version;
    }

}
