package com.uhu.saluhud.database.utils.models.user;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

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
    
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private SaluhudUser user;

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
     * @param entries the list of daily steps entries
     * @param user the user which the historical belongs to
     */
    public DailyStepsHistorical(List<DailyStepsHistoricalEntry> entries, 
            SaluhudUser user)
    {
        this.entries = entries;
        this.user = user;
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

    /**
     * Getter for the parameter "entries"
     * 
     * @return the user which the historical belongs to
     */
    public SaluhudUser getUser()
    {
        return user;
    }

    /**
     * Setter for the parameter "user"
     * 
     * @param user the new user which the historical belongs to
     */
    public void setUser(SaluhudUser user)
    {
        this.user = user;
    }   
}
