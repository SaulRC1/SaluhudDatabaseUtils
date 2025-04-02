package com.uhu.saluhuddatabaseutils.models.user;

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
    @NotNull
    private SaluhudUser user;

    @Version
    @Column(name = "entity_version")
    private Long version;

    /**
     * This a default constructor for the class, with no parameters
     */
    public DailyStepsHistorical()
    {
    }

    /**
     * Parameterized constructor for the class.
     *
     * @param entries The list of daily steps entries.
     * @param user The user to whom this historical record belongs.
     */
    public DailyStepsHistorical(List<DailyStepsHistoricalEntry> entries,
            SaluhudUser user)
    {
        this.entries = entries;
        this.user = user;
    }

    /**
     * Gets the ID of the daily steps historical record.
     *
     * @return The ID of the record.
     */
    public long getId()
    {
        return id;
    }

    /**
     * Sets the ID of the daily steps historical record.
     *
     * @param id The new ID of the record.
     */
    public void setId(long id)
    {
        this.id = id;
    }

    /**
     * Gets the list of daily steps entries.
     *
     * @return The list of entries.
     */
    public List<DailyStepsHistoricalEntry> getEntries()
    {
        return entries;
    }

    /**
     * Sets the list of daily steps entries.
     *
     * @param entries The new list of entries.
     */
    public void setEntries(List<DailyStepsHistoricalEntry> entries)
    {
        this.entries = entries;
    }

    /**
     * Gets the user to whom this historical record belongs.
     *
     * @return The associated user.
     */
    public SaluhudUser getUser()
    {
        return user;
    }

    /**
     * Sets the user to whom this historical record belongs.
     *
     * @param user The new associated user.
     */
    public void setUser(SaluhudUser user)
    {
        this.user = user;
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
