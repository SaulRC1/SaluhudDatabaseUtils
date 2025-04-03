package com.uhu.saluhuddatabaseutils.services.administrationportal.user;

import com.uhu.saluhuddatabaseutils.models.user.HistoricalEvaluation;
import com.uhu.saluhuddatabaseutils.models.user.SleepHistoricalEntry;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import com.uhu.saluhuddatabaseutils.repositories.administrationportal.user.AdministrationPortalSleepHistoricalEntryRepository;

/**
 * Service class for managing sleep historical entries in the administration
 * portal. Provides methods for retrieving, saving, updating, and deleting sleep
 * historical entry records.
 *
 * @author Juan Alberto Dominguez Vazquez
 */
@Service
@Transactional(readOnly = true, transactionManager = "saluhudAdministrationPortalTransactionManager")
public class AdministrationPortalSleepHistoricalEntryService
{

    @Autowired
    private AdministrationPortalSleepHistoricalEntryRepository sleepHistoricalEntryRepository;

    private static final Logger logger = Logger.getLogger(AdministrationPortalSleepHistoricalEntryService.class.getName());

    /**
     * Retrieves all sleep historical entries within a specified date range.
     *
     * @param startDate The start date of the range.
     * @param endDate The end date of the range.
     * @return A list of sleep historical entries within the date range.
     */
    public List<SleepHistoricalEntry> findEntriesByDateRange(LocalDate startDate, LocalDate endDate)
    {
        try
        {
            return sleepHistoricalEntryRepository.findEntriesByDateRange(startDate, endDate);
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error finding entries by date range", e);
            throw e;
        }
    }

    /**
     * Retrieves all sleep historical entries for a specific sleep historical
     * record.
     *
     * @param historicalId The ID of the sleep historical record.
     * @return A list of sleep historical entries associated with the given
     * record.
     */
    public List<SleepHistoricalEntry> findEntriesBySleepHistoricalId(long historicalId)
    {
        try
        {
            return sleepHistoricalEntryRepository.findEntriesBySleepHistoricalId(historicalId);
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error finding entries by SleepHistorical ID", e);
            throw e;
        }
    }

    /**
     * Retrieves sleep historical entries based on a specific sleep evaluation
     * criterion.
     *
     * @param evaluation The sleep evaluation criteria.
     * @return A list of sleep historical entries matching the specified
     * evaluation.
     */
    public List<SleepHistoricalEntry> findEntriesBySleepEvaluation(@Valid HistoricalEvaluation evaluation)
    {
        try
        {
            return sleepHistoricalEntryRepository.findEntriesBySleepEvaluation(evaluation);
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error finding entries by sleep evaluation", e);
            throw e;
        }
    }

    /**
     * Calculates the total hours slept within a specified date range.
     *
     * @param startDate The start date of the range.
     * @param endDate The end date of the range.
     * @return The total number of hours slept in the given range.
     */
    public Integer findTotalHoursSleptInDateRange(LocalDate startDate, LocalDate endDate)
    {
        try
        {
            return sleepHistoricalEntryRepository.findTotalHoursSleptInDateRange(startDate, endDate);
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error finding total hours slept in date range", e);
            throw e;
        }
    }

    /**
     * Calculates the total minutes slept within a specified date range.
     *
     * @param startDate The start date of the range.
     * @param endDate The end date of the range.
     * @return The total number of minutes slept in the given range.
     */
    public Double findTotalMinutesSleptInDateRange(LocalDate startDate, LocalDate endDate)
    {
        try
        {
            return sleepHistoricalEntryRepository.findTotalMinutesSleptInDateRange(startDate, endDate);
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error finding total minutes slept in date range", e);
            throw e;
        }
    }

    /**
     * Saves a new sleep historical entry.
     *
     * @param entry The sleep historical entry to be saved.
     */
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    public void saveSleepHistoricalEntry(@Valid SleepHistoricalEntry entry)
    {
        try
        {
            sleepHistoricalEntryRepository.save(entry);
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error saving SleepHistoricalEntry", e);
            throw e;
        }
    }

    /**
     * Updates an existing sleep historical entry.
     *
     * @param entry The sleep historical entry to be updated.
     */
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    public void updateSleepHistoricalEntry(@Valid SleepHistoricalEntry entry)
    {
        try
        {
            Optional<SleepHistoricalEntry> result = sleepHistoricalEntryRepository.findById(entry.getId());

            if (result.isPresent())
            {
                SleepHistoricalEntry existingEntry = result.get();
                existingEntry.setEntryDate(entry.getEntryDate());
                existingEntry.setHoursSlept(entry.getHoursSlept());
                existingEntry.setMinutesSlept(entry.getMinutesSlept());
                existingEntry.setSleepEvaluation(entry.getSleepEvaluation());

                sleepHistoricalEntryRepository.save(existingEntry);
            }
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error updating SleepHistoricalEntry", e);
            throw e;
        }
    }

    /**
     * Deletes a specified sleep historical entry.
     *
     * @param entry The sleep historical entry to be deleted.
     */
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    public void deleteSleepHistoricalEntry(@Valid SleepHistoricalEntry entry)
    {
        try
        {
            if (this.sleepHistoricalEntryRepository.existsById(entry.getId()))
            {
                this.sleepHistoricalEntryRepository.delete(entry);
            }

        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error deleting SleepHistoricalEntry", e);
            throw e;
        }
    }

    /**
     * Retrieves a sleep historical entry by its ID.
     *
     * @param id The ID of the sleep historical entry.
     * @return The sleep historical entry with the given ID, or null if not
     * found.
     */
    public SleepHistoricalEntry getSleepHistoricalEntryById(long id)
    {
        try
        {
            return sleepHistoricalEntryRepository.findById(id).orElse(null);
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error retrieving SleepHistoricalEntry by ID", e);
            throw e;
        }
    }
}
