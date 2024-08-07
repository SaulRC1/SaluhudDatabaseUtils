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
    private LocalDate entryDate;

    @Column(name = "hours_slept", nullable = false)
    private int hoursSlept;

    @Column(name = "minutes_slept", nullable = false)
    private double minutesSlept;

    @Enumerated(EnumType.STRING)
    @Column(name = "sleep_evaluation", nullable = false)
    private HistoricalEvaluation sleepEvaluation;

    @ManyToOne
    @JoinColumn(name = "sleep_historical_id", nullable = false)
    private SleepHistorical sleepHistorical;

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
     * Setter for the parameter "hoursSlept"
     *
     * @param hoursSlept the new amount of hours slept by the user
     */
    public void setDoneSteps(int hoursSlept)
    {
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
}
