package com.uhu.saluhuddatabaseutils.services.administrationportal.user;

import com.uhu.saluhuddatabaseutils.models.user.WeightHistoricalEntry;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import com.uhu.saluhuddatabaseutils.repositories.administrationportal.user.AdministrationPortalWeightHistoricalEntryRepository;

/**
 * Service class for managing weight historical entries.
 *
 * @author Juan Alberto Dominguez Vazquez
 */
@Service
@Transactional(readOnly = true, transactionManager = "saluhudAdministrationPortalTransactionManager")
public class AdministrationPortalWeightHistoricalEntryService
{

    @Autowired
    private AdministrationPortalWeightHistoricalEntryRepository weightHistoricalEntryRepository;

    private static final Logger logger = Logger.getLogger(AdministrationPortalWeightHistoricalEntryService.class.getName());

    /**
     * Retrieves all weight historical entries.
     *
     * @return A list of all weight historical entries.
     */
    public List<WeightHistoricalEntry> findAllWeightHistoricalEntries()
    {
        return this.weightHistoricalEntryRepository.findAll();
    }

    /**
     * Retrieves a weight historical entry by its ID.
     *
     * @param id The ID of the weight historical entry.
     * @return The weight historical entry, or null if not found.
     */
    public WeightHistoricalEntry findWeightHistoricalEntryById(long id)
    {
        try
        {
            Optional<WeightHistoricalEntry> result = this.weightHistoricalEntryRepository.findById(id);
            return result.orElseThrow();
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error finding weight historical entry by ID", e);
            throw e;
        }
    }

    /**
     * Retrieves all weight historical entries associated with a specific weight
     * historical ID.
     *
     * @param weightHistoricalId The ID of the weight historical.
     * @return A list of weight historical entries.
     */
    public List<WeightHistoricalEntry> findEntriesByWeightHistoricalId(Long weightHistoricalId)
    {
        try
        {
            return this.weightHistoricalEntryRepository.findByWeightHistoricalId(weightHistoricalId);
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error finding entries by weight historical ID", e);
            throw e;
        }
    }

    /**
     * Retrieves all weight historical entries for a specific date.
     *
     * @param entryDate The date of the entries.
     * @return A list of weight historical entries.
     */
    public List<WeightHistoricalEntry> findEntriesByDate(LocalDate entryDate)
    {
        try
        {
            return this.weightHistoricalEntryRepository.findByEntryDate(entryDate);
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error finding entries by date", e);
            throw e;
        }
    }

    /**
     * Retrieves weight historical entries within a specified date range for a
     * specific weight historical.
     *
     * @param weightHistoricalId The ID of the weight historical.
     * @param startDate The start date of the range.
     * @param endDate The end date of the range.
     * @return A list of weight historical entries.
     */
    public List<WeightHistoricalEntry> findEntriesByDateRangeAndWeightHistoricalId(Long weightHistoricalId, LocalDate startDate, LocalDate endDate)
    {
        try
        {
            return this.weightHistoricalEntryRepository.findByDateRangeAndWeightHistoricalId(weightHistoricalId, startDate, endDate);
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error finding entries by date range and weight historical ID", e);
            throw e;
        }
    }

    /**
     * Retrieves the most recent weight historical entry for a specific weight
     * historical.
     *
     * @param weightHistoricalId The ID of the weight historical.
     * @return The most recent weight historical entry.
     */
    public WeightHistoricalEntry findMostRecentEntryByWeightHistoricalId(Long weightHistoricalId)
    {
        try
        {
            return this.weightHistoricalEntryRepository.findMostRecentEntryByWeightHistoricalId(weightHistoricalId);
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error finding most recent entry by weight historical ID", e);
            throw e;
        }
    }

    /**
     * Saves a new weight historical entry.
     *
     * @param weightHistoricalEntry The weight historical entry to save.
     */
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    public void saveWeightHistoricalEntry(@Valid WeightHistoricalEntry weightHistoricalEntry)
    {
        try
        {
            this.weightHistoricalEntryRepository.save(weightHistoricalEntry);
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error saving weight historical entry", e);
            throw e;
        }
    }

    /**
     * Updates an existing weight historical entry.
     *
     * @param weightHistoricalEntry The weight historical entry to update.
     */
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    public void updateWeightHistoricalEntry(@Valid WeightHistoricalEntry weightHistoricalEntry)
    {
        try
        {
            Optional<WeightHistoricalEntry> result = this.weightHistoricalEntryRepository.findById(weightHistoricalEntry.getId());

            if (result.isPresent())
            {
                WeightHistoricalEntry existingEntry = result.get();
                existingEntry.setEntryDate(weightHistoricalEntry.getEntryDate());
                existingEntry.setWeightEntry(weightHistoricalEntry.getWeightEntry());
                existingEntry.setHeightEntry(weightHistoricalEntry.getHeightEntry());
                existingEntry.setKilocaloriesObjectiveEntry(weightHistoricalEntry.getKilocaloriesObjectiveEntry());

                this.weightHistoricalEntryRepository.save(existingEntry);
            }
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error updating weight historical entry", e);
            throw e;
        }
    }

    /**
     * Deletes an existing weight historical entry.
     *
     * @param weightHistoricalEntry The weight historical entry to delete.
     */
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    public void deleteWeightHistoricalEntry(@Valid WeightHistoricalEntry weightHistoricalEntry)
    {
        try
        {
            if (this.weightHistoricalEntryRepository.existsById(weightHistoricalEntry.getId()))
            {
                this.weightHistoricalEntryRepository.delete(weightHistoricalEntry);
            }

        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error deleting weight historical entry", e);
            throw e;
        }
    }
}
