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
import org.hibernate.validator.constraints.Range;

/**
 * This class represents one entry in the sleep historical stored in the
 * database.
 *
 * @author Juan Alberto Dominguez Vazquez
 */
@Entity
@Table(name = "sleep_historical_entry")
public class SleepHistoricalEntry implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "entry_date", nullable = false)
    @NotNull
    private LocalDate entryDate;

    @Column(name = "hours_slept", nullable = false)
    @NotNull
    @Range(min = 1, max = 15)
    private int hoursSlept;

    @Column(name = "minutes_slept", nullable = false)
    @NotNull
    @Range(min = 0, max = 59)
    private double minutesSlept;

    @Convert(converter = HistoricalEvaluationConverter.class)
    @Column(name = "sleep_evaluation", nullable = false)
    @NotNull
    private HistoricalEvaluation sleepEvaluation;

    @ManyToOne
    @JoinColumn(name = "sleep_historical_id", nullable = false)
    @NotNull
    private SleepHistorical sleepHistorical;
    
    @Version
    @Column(name = "entity_version")
    private Long version;

    /**
     * This a default constructor for the class, with no parameters
     */
    public SleepHistoricalEntry()
    {
    }

    /**
     * This is a parameterized constructor for the class.It takes, the entry
     * date, the hours slept, the minutes slept, the sleep
     * evaluation and the sleep historical which the entry belongs to
     *
     * @param entryDate the entry date
     * @param hoursSlept the hours slept by the user
     * @param minutesSlept the amount of minutes slept in the entry
     * @param sleepEvaluation the sleep evaluation of the entry
     * @param sleepHistorical the sleep historical which the entry belongs to
     */
    public SleepHistoricalEntry(LocalDate entryDate, int hoursSlept,
            int minutesSlept, HistoricalEvaluation sleepEvaluation,
            SleepHistorical sleepHistorical)
    {
        this.entryDate = entryDate;
        this.hoursSlept = hoursSlept;
        this.minutesSlept = minutesSlept;
        this.sleepEvaluation = sleepEvaluation;
        this.sleepHistorical = sleepHistorical;
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
     * Setter for the parameter "id"
     * 
     * @param id the id of the daily steps entry
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
     * Getter for the parameter "hoursSlept"
     *
     * @return the hours slept by the user
     */
    public int getHoursSlept()
    {
        return hoursSlept;
    }

    /**
     * Setter for the parameter "hoursSlept"
     * 
     * @param hoursSlept the number of hours slept
     */
    public void setHoursSlept(int hoursSlept) {
        this.hoursSlept = hoursSlept;
    }

    /**
     * Getter for the parameter "minutesSlept"
     *
     * @return the minutes slept by the user
     */
    public double getMinutesSlept()
    {
        return minutesSlept;
    }

    /**
     * Setter for the parameter "minutesSlept"
     *
     * @param minutesSlept the new amount of minutes slept by the user
     */
    public void setMinutesSlept(double minutesSlept)
    {
        this.minutesSlept = minutesSlept;
    }

    /**
     * Getter for the parameter "sleepEvaluation"
     *
     * @return the sleep evaluation of the entry
     */
    public HistoricalEvaluation getSleepEvaluation()
    {
        return sleepEvaluation;
    }

    /**
     * Setter for the parameter "sleepEvaluation"
     *
     * @param sleepEvaluation the new sleep evaluation of the entry
     */
    public void setSleepEvaluation(HistoricalEvaluation sleepEvaluation)
    {
        this.sleepEvaluation = sleepEvaluation;
    }

    /**
     * Getter for the parameter "sleepHistorical"
     *
     * @return the sleep historical of the user
     */
    public SleepHistorical getSleepHistorical()
    {
        return sleepHistorical;
    }

    /**
     * Setter for the parameter "sleepHistorical"
     * 
     * @param sleepHistorical the sleep historical of the user
     */
    public void setSleepHistorical(SleepHistorical sleepHistorical)
    {
        this.sleepHistorical = sleepHistorical;
    }

    /**
     * Getter for the parameter "version"
     * 
     * @return the version of the entry
     */
    public Long getVersion()
    {
        return version;
    }

    /**
     * Setter for the parameter "version"
     * 
     * @param version the new version of the entry
     */
    public void setVersion(Long version)
    {
        this.version = version;
    }
    
}
