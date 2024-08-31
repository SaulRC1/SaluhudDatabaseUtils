package com.uhu.saluhud.database.utils.models.user;

import java.io.Serializable;
import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Version;

/**
 * This class represents one entry in the historical daily steps stored in the
 * database.
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

    @Enumerated(EnumType.STRING)
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
     * This is a parameterized constructor for the class.It takes, the entry
     * date of the entry, the done steps, the kilocalories burned and the step
     * evaluation
     *
     * @param entryDate the entry date
     * @param doneSteps the done steps in the entry
     * @param kiloCaloriesBurned the amount of kilocalories burned in the entry
     * @param stepEvaluation the step evaluation of the entry
     * @param dailyStepsHistorical
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
     * Getter for the parameter "id"
     *
     * @return the id of the daily steps entry
     */
    public long getId()
    {
        return id;
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
     * Getter for the parameter "doneSteps"
     *
     * @return the done steps of the entry
     */
    public int getDoneSteps()
    {
        return doneSteps;
    }

    /**
     * Setter for the parameter "doneSteps"
     *
     * @param doneSteps the new done steps of the entry
     */
    public void setDoneSteps(int doneSteps)
    {
        this.doneSteps = doneSteps;
    }

    /**
     * Getter for the parameter "kilocaloriesBurned"
     *
     * @return the kilocalories burned of the entry
     */
    public double getKiloCaloriesBurned()
    {
        return kiloCaloriesBurned;
    }

    /**
     * Setter for the parameter "kilocaloriesBurned"
     *
     * @param kiloCaloriesBurned the new amount of kilocalories burned
     */
    public void setKiloCaloriesBurned(double kiloCaloriesBurned)
    {
        this.kiloCaloriesBurned = kiloCaloriesBurned;
    }

    /**
     * Getter for the parameter "stepEvaluation"
     *
     * @return the step evaluation of the entry
     */
    public HistoricalEvaluation getStepEvaluation()
    {
        return stepEvaluation;
    }

    /**
     * Setter for the parameter "stepEvaluation"
     *
     * @param stepEvaluation the new step evaluation of the entry
     */
    public void setStepEvaluation(HistoricalEvaluation stepEvaluation)
    {
        this.stepEvaluation = stepEvaluation;
    }

    /**
     *
     * @return
     */
    public DailyStepsHistorical getDailyStepsHistorical()
    {
        return dailyStepsHistorical;
    }
    
    /**
     *
     * @param dailyStepsHistorical
     */
    public void setDailyStepsHistorical(DailyStepsHistorical dailyStepsHistorical)
    {
        this.dailyStepsHistorical = dailyStepsHistorical;
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
