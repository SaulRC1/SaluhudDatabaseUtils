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
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;

/**
 * This class represents the sleep historical stored in the database.
 *
 * @author Juan Alberto Dominguez Vazquez
 */
@Entity
@Table(name = "sleep_historical")
public class SleepHistorical implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @OneToMany(mappedBy = "sleepHistorical", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SleepHistoricalEntry> entries;
    
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private SaluhudUser user;
    
    @Version
    @Column(name = "entity_version")
    private Long version;

    /**
     * This a default constructor for the class, with no parameters
     */
    public SleepHistorical()
    {
    }

    /**
     * This is a parameterized constructor for the class.It takes, a list of
     * sleep entries
     *
     * @param entries the list of sleep historical entries
     * @param user the user which the historical belongs to
     */
    public SleepHistorical(List<SleepHistoricalEntry> entries, SaluhudUser user)
    {
        this.entries = entries;
        this.user = user;
    }

    /**
     * Getter for the parameter "id"
     *
     * @return the id the of sleep historical
     */
    public long getId()
    {
        return id;
    }

    /**
     * Setter for the parameter "id"
     * 
     * @param id the id the of sleep historical
     */
    public void setId(long id)
    {
        this.id = id;
    }

    /**
     * Getter for the parameter "entries"
     *
     * @return the sleep historical of the user
     */
    public List<SleepHistoricalEntry> getEntries()
    {
        return entries;
    }

    /**
     * Setter for the parameter "entries"
     *
     * @param entries the new sleep historical of the user
     */
    public void setEntries(List<SleepHistoricalEntry> entries)
    {
        this.entries = entries;
    }

    /**
     * Getter for the parameter "user"
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

    public Long getVersion()
    {
        return version;
    }

    public void setVersion(Long version)
    {
        this.version = version;
    }
    
}
