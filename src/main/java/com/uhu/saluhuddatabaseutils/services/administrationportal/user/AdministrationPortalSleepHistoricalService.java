package com.uhu.saluhuddatabaseutils.services.administrationportal.user;

import com.uhu.saluhuddatabaseutils.models.user.SleepHistorical;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.Valid;
import com.uhu.saluhuddatabaseutils.repositories.administrationportal.user.AdministrationPortalSleepHistoricalRepository;

/**
 * Service class for managing sleep historical records in the administration
 * portal. This service provides methods for retrieving, saving, updating, and
 * deleting sleep history data.
 *
 * @author Juan Alberto Dominguez Vazquez
 */
@Service
@Transactional(readOnly = true, transactionManager = "saluhudAdministrationPortalTransactionManager")
public class AdministrationPortalSleepHistoricalService
{

    @Autowired
    private AdministrationPortalSleepHistoricalRepository sleepHistoricalRepository;

    private static final Logger logger = Logger.getLogger(AdministrationPortalSleepHistoricalService.class.getName());

    /**
     * Retrieves all sleep historical records.
     *
     * @return A list of all sleep historical records.
     */
    public List<SleepHistorical> findAll()
    {
        return sleepHistoricalRepository.findAll();
    }

    /**
     * Finds a sleep historical record by its ID.
     *
     * @param id The ID of the sleep historical record.
     * @return The sleep historical record with the specified ID, or
     * {@code null} if not found.
     */
    public SleepHistorical findById(long id)
    {
        return sleepHistoricalRepository.findById(id).orElse(null);
    }

    /**
     * Finds a sleep historical record associated with a specific user.
     *
     * @param userId The ID of the user.
     * @return The sleep historical record for the specified user.
     */
    public SleepHistorical findByUserId(long userId)
    {
        return sleepHistoricalRepository.findByUserId(userId);
    }

    /**
     * Retrieves sleep historical records that contain entries within a
     * specified date range.
     *
     * @param startDate The start date of the range.
     * @param endDate The end date of the range.
     * @return A list of sleep historical records with entries within the
     * specified date range.
     */
    public List<SleepHistorical> findWithEntriesInDateRange(LocalDate startDate, LocalDate endDate)
    {
        return sleepHistoricalRepository.findWithEntriesInDateRange(startDate, endDate);
    }

    /**
     * Retrieves sleep historical records for a specific user that contain
     * entries within a specified date range.
     *
     * @param userId The ID of the user.
     * @param startDate The start date of the range.
     * @param endDate The end date of the range.
     * @return A list of sleep historical records for the specified user within
     * the date range.
     */
    public List<SleepHistorical> findByUserIdWithEntriesInDateRange(long userId, LocalDate startDate, LocalDate endDate)
    {
        return sleepHistoricalRepository.findByUserIdWithEntriesInDateRange(userId, startDate, endDate);
    }

    /**
     * Retrieves sleep historical records that have at least a specified number
     * of entries.
     *
     * @param minEntries The minimum number of entries required.
     * @return A list of sleep historical records containing at least the
     * specified number of entries.
     */
    public List<SleepHistorical> findByMinEntries(int minEntries)
    {
        return sleepHistoricalRepository.findByMinEntries(minEntries);
    }

    /**
     * Retrieves the total sleep hours for a specific user within a given date
     * range.
     *
     * @param userId The ID of the user.
     * @param startDate The start date of the range.
     * @param endDate The end date of the range.
     * @return The total sleep hours recorded for the user within the date
     * range.
     */
    public double findTotalSleepHoursByUserIdAndDateRange(long userId, LocalDate startDate, LocalDate endDate)
    {
        return sleepHistoricalRepository.findTotalSleepHoursByUserIdAndDateRange(userId, startDate, endDate);
    }

    /**
     * Saves a new sleep historical record.
     *
     * @param sleepHistorical The sleep historical record to save.
     */
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    public void saveSleepHistorical(@Valid SleepHistorical sleepHistorical)
    {
        sleepHistoricalRepository.save(sleepHistorical);
    }

    /**
     * Updates an existing sleep historical record.
     *
     * @param sleepHistorical The sleep historical record to update.
     */
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    public void updateSleepHistorical(@Valid SleepHistorical sleepHistorical)
    {
        try
        {
            Optional<SleepHistorical> result = this.sleepHistoricalRepository.findById(sleepHistorical.getId());

            if (result.isPresent())
            {
                SleepHistorical existingSleepHistorical = result.get();
                existingSleepHistorical.setUser(sleepHistorical.getUser());
                existingSleepHistorical.setEntries(sleepHistorical.getEntries());

                this.sleepHistoricalRepository.save(existingSleepHistorical);
            }
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error updating SleepHistorical", e);
            throw e;
        }
    }

    /**
     * Deletes a sleep historical record.
     *
     * @param sleepHistorical The sleep historical record to delete.
     */
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    public void deleteSleepHistorical(@Valid SleepHistorical sleepHistorical)
    {
        try
        {
            if (this.sleepHistoricalRepository.existsById(sleepHistorical.getId()))
            {
                this.sleepHistoricalRepository.delete(sleepHistorical);
            }

        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error deleting SleepHistorical", e);
            throw e;
        }
    }
}
