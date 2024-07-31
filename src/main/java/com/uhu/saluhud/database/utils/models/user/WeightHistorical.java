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
 * This class represents the weight historical stored in the database.
 *
 * @author Juan Alberto Dominguez Vazquez
 */
@Entity
@Table(name = "weight_historical")
public class WeightHistorical implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @OneToMany(mappedBy = "weightHistorical", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WeightHistoricalEntry> entries;
    
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private SaluhudUser user;

    /**
     * This a default constructor for the class, with no parameters
     */
    public WeightHistorical()
    {
    }

    /**
     * This is a parameterized constructor for the class.It takes, a list of
     * weight entries
     *
     * @param entries the list of sleep entries
     * @param user the user which the historical belongs to
     */
    public WeightHistorical(List<WeightHistoricalEntry> entries, SaluhudUser user)
    {
        this.entries = entries;
        this.user = user;
    }

    /**
     * Getter for the parameter "id"
     * 
     * @return the id of the weight historical
     */
    public long getId()
    {
        return id;
    }

    /**
     * Getter for the parameter "entries"
     *
     * @return the list of weight entries
     */
    public List<WeightHistoricalEntry> getEntries()
    {
        return entries;
    }

    /**
     * Setter for the parameter "entries"
     *
     * @param entries the new list of weight historical entries
     */
    public void setEntries(List<WeightHistoricalEntry> entries)
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
}
