package com.uhu.saluhuddatabaseutils.services.administrationportal.user;

import com.uhu.saluhuddatabaseutils.models.user.DailyStepsHistorical;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.Valid;
import com.uhu.saluhuddatabaseutils.repositories.administrationportal.user.AdministrationPortalDailyStepsHistoricalRepository;

/**
 * Service class for managing daily steps historical records in the
 * administration portal. This service provides methods for saving, updating,
 * deleting, and querying daily steps history.
 *
 * @author Juan Alberto Dominguez Vazquez
 */
@Service
@Transactional(readOnly = true, transactionManager = "saluhudAdministrationPortalTransactionManager")
public class AdministrationPortalDailyStepsHistoricalService
{

    @Autowired
    private AdministrationPortalDailyStepsHistoricalRepository dailyStepsHistoricalRepository;

    private static final Logger logger = Logger.getLogger(AdministrationPortalDailyStepsHistoricalService.class.getName());

    /**
     * Saves a new daily steps historical record.
     *
     * @param dailyStepsHistorical The daily steps historical record to save.
     */
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    public void saveDailyStepsHistorical(@Valid DailyStepsHistorical dailyStepsHistorical)
    {
        this.dailyStepsHistoricalRepository.save(dailyStepsHistorical);
    }

    /**
     * Updates an existing daily steps historical record.
     *
     * @param dailyStepsHistorical The daily steps historical record to update.
     */
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    public void updateDailyStepsHistorical(@Valid DailyStepsHistorical dailyStepsHistorical)
    {
        try
        {
            Optional<DailyStepsHistorical> result;

            result = this.dailyStepsHistoricalRepository.findById(dailyStepsHistorical.getId());

            if (result.isPresent())
            {
                DailyStepsHistorical existingDailySteps = result.get();
                existingDailySteps.setUser(dailyStepsHistorical.getUser());
                existingDailySteps.setEntries(dailyStepsHistorical.getEntries());

                this.dailyStepsHistoricalRepository.save(existingDailySteps);
            }
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error updating DailyStepsHistoricalEntry", e);
            throw e;
        }
    }

    /**
     * Deletes a daily steps historical record.
     *
     * @param dailyStepsHistorical The daily steps historical record to delete.
     */
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    public void deleteDailyStepsHistorical(@Valid DailyStepsHistorical dailyStepsHistorical)
    {
        try
        {
            if (this.dailyStepsHistoricalRepository.existsById(dailyStepsHistorical.getId()))
            {
                this.dailyStepsHistoricalRepository.delete(dailyStepsHistorical);
            }

        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error deleting DailyStepsHistoricalEntry", e);
            throw e;
        }
    }

    /**
     * Retrieves all daily steps historical records.
     *
     * @return A list of all daily steps historical records.
     */
    public List<DailyStepsHistorical> findAll()
    {
        return dailyStepsHistoricalRepository.findAll();
    }

    /**
     * Finds a daily steps historical record by its ID.
     *
     * @param id The ID of the daily steps historical record.
     * @return An Optional containing the daily steps historical record if
     * found.
     */
    public Optional<DailyStepsHistorical> findById(long id)
    {
        return dailyStepsHistoricalRepository.findById(id);
    }

    /**
     * Retrieves all daily steps historical records for a specific user.
     *
     * @param userId The ID of the user.
     * @return A list of daily steps historical records belonging to the user.
     */
    public List<DailyStepsHistorical> findAllByUserId(Long userId)
    {
        return dailyStepsHistoricalRepository.findAllByUserId(userId);
    }

    /**
     * Retrieves daily steps historical records that contain at least a
     * specified number of entries.
     *
     * @param minEntries The minimum number of entries required.
     * @return A list of daily steps historical records with at least
     * {@code minEntries} entries.
     */
    public List<DailyStepsHistorical> findAllWithMinEntries(int minEntries)
    {
        return dailyStepsHistoricalRepository.findAllWithMinEntries(minEntries);
    }

    /**
     * Retrieves all daily steps historical records along with their entries for
     * a specific user.
     *
     * @param userId The ID of the user.
     * @return A list of daily steps historical records belonging to the user,
     * including their entries.
     */
    public List<DailyStepsHistorical> findAllWithEntriesByUserId(Long userId)
    {
        return dailyStepsHistoricalRepository.findAllWithEntriesByUserId(userId);
    }

    /**
     * Counts the number of daily steps historical records for a specific user.
     *
     * @param userId The ID of the user.
     * @return The total count of daily steps historical records for the user.
     */
    public int countByUserId(Long userId)
    {
        return dailyStepsHistoricalRepository.countByUserId(userId);
    }
}
