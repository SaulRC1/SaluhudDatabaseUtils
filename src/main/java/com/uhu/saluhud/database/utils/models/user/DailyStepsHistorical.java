package com.uhu.saluhud.database.utils.models.user;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * This class represents the historical daily steps stored in the database.
 *
 * @author Juan Alberto Dominguez Vazquez
 */
@Entity
@Table(name = "daily_steps_historical")
public class DailyStepsHistorical implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @OneToMany(mappedBy = "dailyStepsHistorical", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DailyStepsHistoricalEntry> entries;

    /**
     * This a default constructor for the class, with no parameters
     */
    public DailyStepsHistorical()
    {
    }

    /**
     * This is a parameterized constructor for the class.It takes, a list of
     * daily steps entries
     *
     * @param entries
     */
    public DailyStepsHistorical(List<DailyStepsHistoricalEntry> entries)
    {
        this.entries = entries;
    }

    /**
     * Getter for the parameter "id"
     *
     * @return the id the of daily steps historical
     */
    public long getId()
    {
        return id;
    }

    /**
     * Getter for the parameter "entries"
     *
     * @return the daily steps historical of the user
     */
    public List<DailyStepsHistoricalEntry> getEntries()
    {
        return entries;
    }

    /**
     * Setter for the parameter "entries"
     *
     * @param entries the new daily steps historical of the user
     */
    public void setEntries(List<DailyStepsHistoricalEntry> entries)
    {
        this.entries = entries;
    }
}
