package com.uhu.saluhuddatabaseutils.services.administrationportal.user;

import com.uhu.saluhuddatabaseutils.models.user.DailyStepsHistoricalEntry;
import com.uhu.saluhuddatabaseutils.models.user.HistoricalEvaluation;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.validation.Valid;
import com.uhu.saluhuddatabaseutils.repositories.administrationportal.user.AdministrationPortalDailyStepsHistoricalEntryRepository;

/**
 * Service class responsible for managing daily step historical entries.
 * Provides functionality to save, update, delete, and retrieve daily step
 * historical data.
 *
 * @author Juan Alberto Dominguez Vazquez
 */
@Service
@Transactional(readOnly = true, transactionManager = "saluhudAdministrationPortalTransactionManager")
public class AdministrationPortalDailyStepsHistoricalEntryService
{

    @Autowired
    private AdministrationPortalDailyStepsHistoricalEntryRepository dailyStepsHistoricalEntryRepository;

    private static final Logger logger = Logger.getLogger(AdministrationPortalDailyStepsHistoricalEntryService.class.getName());

    /**
     * Saves a new daily steps historical entry.
     *
     * @param dailyStepsHistoricalEntry The daily steps historical entry to
     * save.
     */
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    public void saveDailyStepsHistoricalEntry(@Valid DailyStepsHistoricalEntry dailyStepsHistoricalEntry)
    {
        try
        {
            if (dailyStepsHistoricalEntry.getDoneSteps() < 2500)
            {
                dailyStepsHistoricalEntry.setStepEvaluation(HistoricalEvaluation.FAILED);
            }
            else if (dailyStepsHistoricalEntry.getDoneSteps() >= 2500
                    && dailyStepsHistoricalEntry.getDoneSteps() < 4000)
            {
                dailyStepsHistoricalEntry.setStepEvaluation(HistoricalEvaluation.MINIMUM);
            }
            else if (dailyStepsHistoricalEntry.getDoneSteps() >= 4000
                    && dailyStepsHistoricalEntry.getDoneSteps() < 7000)
            {
                dailyStepsHistoricalEntry.setStepEvaluation(HistoricalEvaluation.WELL);
            }
            else
            {
                dailyStepsHistoricalEntry.setStepEvaluation(HistoricalEvaluation.EXCELLENT);
            }
            this.dailyStepsHistoricalEntryRepository.save(dailyStepsHistoricalEntry);
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error saving SleepHistoricalEntry", e);
            throw e;
        }
    }

    /**
     * Updates an existing daily steps historical entry.
     *
     * @param dailyStepsHistoricalEntry The daily steps historical entry to
     * update.
     */
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    public void updateDailyStepsHistoricalEntry(@Valid DailyStepsHistoricalEntry dailyStepsHistoricalEntry)
    {
        try
        {
            Optional<DailyStepsHistoricalEntry> result;
            result = this.dailyStepsHistoricalEntryRepository.findById(dailyStepsHistoricalEntry.getId());

            if (result.isPresent())
            {
                DailyStepsHistoricalEntry existingDailyStepsEntry = result.get();
                existingDailyStepsEntry.setDailyStepsHistorical(dailyStepsHistoricalEntry.getDailyStepsHistorical());
                existingDailyStepsEntry.setDoneSteps(dailyStepsHistoricalEntry.getDoneSteps());
                existingDailyStepsEntry.setEntryDate(dailyStepsHistoricalEntry.getEntryDate());
                existingDailyStepsEntry.setKiloCaloriesBurned(dailyStepsHistoricalEntry.getKiloCaloriesBurned());
                if (existingDailyStepsEntry.getDoneSteps() < 2500)
                {
                    existingDailyStepsEntry.setStepEvaluation(HistoricalEvaluation.FAILED);
                }
                else if (existingDailyStepsEntry.getDoneSteps() >= 2500
                        && existingDailyStepsEntry.getDoneSteps() < 4000)
                {
                    existingDailyStepsEntry.setStepEvaluation(HistoricalEvaluation.MINIMUM);
                }
                else if (existingDailyStepsEntry.getDoneSteps() >= 4000
                        && existingDailyStepsEntry.getDoneSteps() < 7000)
                {
                    existingDailyStepsEntry.setStepEvaluation(HistoricalEvaluation.WELL);
                }
                else
                {
                    existingDailyStepsEntry.setStepEvaluation(HistoricalEvaluation.EXCELLENT);
                }

                this.dailyStepsHistoricalEntryRepository.save(existingDailyStepsEntry);
            }
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error updating DailyStepsHistoricalEntry", e);
            throw e; // Re-throw the exception to trigger rollback
        }
    }

    /**
     * Deletes a daily steps historical entry.
     *
     * @param dailyStepsHistoricalEntry The daily steps historical entry to
     * delete.
     */
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    public void deleteDailyStepsHistorical(@Valid DailyStepsHistoricalEntry dailyStepsHistoricalEntry)
    {
        try
        {
            if (this.dailyStepsHistoricalEntryRepository.existsById(dailyStepsHistoricalEntry.getId()))
            {
                this.dailyStepsHistoricalEntryRepository.delete(dailyStepsHistoricalEntry);
            }

        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error deleting DailyStepsHistoricalEntry", e);
            throw e;
        }
    }

    /**
     * Finds entries within a specific date range.
     *
     * @param startDate The start date.
     * @param endDate The end date.
     * @return A list of entries within the specified date range.
     */
    public List<DailyStepsHistoricalEntry> findEntriesByDateRange(LocalDate startDate, LocalDate endDate)
    {
        return dailyStepsHistoricalEntryRepository.findEntriesByDateRange(startDate, endDate);
    }

    /**
     * Finds entries by step evaluation.
     *
     * @param evaluation The step evaluation.
     * @return A list of entries with the specified evaluation.
     */
    public List<DailyStepsHistoricalEntry> findEntriesByStepEvaluation(@Valid HistoricalEvaluation evaluation)
    {
        return dailyStepsHistoricalEntryRepository.findEntriesByStepEvaluation(evaluation);
    }

    /**
     * Finds the total steps within a specified date range.
     *
     * @param startDate The start date.
     * @param endDate The end date.
     * @return The total steps within the specified date range.
     */
    public int findTotalStepsInDateRange(LocalDate startDate, LocalDate endDate)
    {
        return dailyStepsHistoricalEntryRepository.findTotalStepsInDateRange(startDate, endDate);
    }

    /**
     * Finds the total calories burned within a specified date range.
     *
     * @param startDate The start date.
     * @param endDate The end date.
     * @return The total calories burned within the specified date range.
     */
    public double findTotalCaloriesBurnedInDateRange(LocalDate startDate, LocalDate endDate)
    {
        return dailyStepsHistoricalEntryRepository.findTotalCaloriesBurnedInDateRange(startDate, endDate);
    }

    /**
     * Finds entries by daily steps historical ID.
     *
     * @param historicalId The ID of the daily steps historical record.
     * @return A list of entries associated with the specified historical ID.
     */
    public List<DailyStepsHistoricalEntry> findEntriesByDailyStepsHistoricalId(long historicalId)
    {
        return dailyStepsHistoricalEntryRepository.findEntriesByDailyStepsHistoricalId(historicalId);
    }

    /**
     * Finds entries where the number of steps is greater than a specified
     * value.
     *
     * @param steps The minimum number of steps.
     * @return A list of entries with steps greater than the specified value.
     */
    public List<DailyStepsHistoricalEntry> findEntriesWithStepsGreaterThan(int steps)
    {
        return dailyStepsHistoricalEntryRepository.findEntriesWithStepsGreaterThan(steps);
    }

    /**
     * Finds all daily steps historical entries by username.
     *
     * @param username The username.
     * @return A list of daily steps historical entries for the specified user.
     */
    public List<DailyStepsHistoricalEntry> findAllByUserUsername(String username)
    {
        return dailyStepsHistoricalEntryRepository.findAllByUserUsername(username);
    }
}
