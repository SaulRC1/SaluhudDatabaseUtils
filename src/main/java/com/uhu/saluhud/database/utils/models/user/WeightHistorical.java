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
     */
    public WeightHistorical(List<WeightHistoricalEntry> entries)
    {
        this.entries = entries;
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
}
