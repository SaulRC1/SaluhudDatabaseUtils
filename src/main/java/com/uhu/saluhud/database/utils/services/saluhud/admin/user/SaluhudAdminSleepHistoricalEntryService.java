package com.uhu.saluhud.database.utils.services.saluhud.admin.user;

import com.uhu.saluhud.database.utils.models.user.HistoricalEvaluation;
import com.uhu.saluhud.database.utils.models.user.SleepHistoricalEntry;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.uhu.saluhud.database.utils.repositories.saluhud.admin.user.SaluhudAdminSleepHistoricalEntryRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
@Service
@Transactional(readOnly = true, transactionManager = "saluhudAdminTransactionManager")
public class SaluhudAdminSleepHistoricalEntryService {

    @Autowired
    private SaluhudAdminSleepHistoricalEntryRepository sleepHistoricalEntryRepository;

    private static final Logger logger = Logger.getLogger(SaluhudAdminSleepHistoricalEntryService.class.getName());

    /**
     * Finds all SleepHistoricalEntry records within a given date range.
     *
     * @param startDate The start date of the range.
     * @param endDate The end date of the range.
     * @return A list of SleepHistoricalEntry records within the specified date
     * range.
     */
    public List<SleepHistoricalEntry> findEntriesByDateRange(LocalDate startDate, LocalDate endDate) {
        try {
            return sleepHistoricalEntryRepository.findEntriesByDateRange(startDate, endDate);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error finding entries by date range", e);
            throw e;
        }
    }

    /**
     * Finds all SleepHistoricalEntry records for a specific SleepHistorical ID.
     *
     * @param historicalId The ID of the SleepHistorical entity.
     * @return A list of SleepHistoricalEntry records associated with the given
     * SleepHistorical ID.
     */
    public List<SleepHistoricalEntry> findEntriesBySleepHistoricalId(long historicalId) {
        try {
            return sleepHistoricalEntryRepository.findEntriesBySleepHistoricalId(historicalId);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error finding entries by SleepHistorical ID", e);
            throw e;
        }
    }

    /**
     * Finds all SleepHistoricalEntry records with a specific sleep evaluation.
     *
     * @param evaluation The sleep evaluation criteria.
     * @return A list of SleepHistoricalEntry records with the specified sleep
     * evaluation.
     */
    public List<SleepHistoricalEntry> findEntriesBySleepEvaluation(@Valid HistoricalEvaluation evaluation) {
        try {
            return sleepHistoricalEntryRepository.findEntriesBySleepEvaluation(evaluation);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error finding entries by sleep evaluation", e);
            throw e;
        }
    }

    /**
     * Finds the total hours slept within a given date range.
     *
     * @param startDate The start date of the range.
     * @param endDate The end date of the range.
     * @return The total hours slept within the specified date range.
     */
    public Integer findTotalHoursSleptInDateRange(LocalDate startDate, LocalDate endDate) {
        try {
            return sleepHistoricalEntryRepository.findTotalHoursSleptInDateRange(startDate, endDate);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error finding total hours slept in date range", e);
            throw e;
        }
    }

    /**
     * Finds the total minutes slept within a given date range.
     *
     * @param startDate The start date of the range.
     * @param endDate The end date of the range.
     * @return The total minutes slept within the specified date range.
     */
    public Double findTotalMinutesSleptInDateRange(LocalDate startDate, LocalDate endDate) {
        try {
            return sleepHistoricalEntryRepository.findTotalMinutesSleptInDateRange(startDate, endDate);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error finding total minutes slept in date range", e);
            throw e;
        }
    }

    /**
     * Saves a new SleepHistoricalEntry entity.
     *
     * @param entry The SleepHistoricalEntry entity to save.
     */
    @Transactional(transactionManager = "saluhudAdminTransactionManager")
    public void saveSleepHistoricalEntry(@Valid SleepHistoricalEntry entry) {
        try {
            sleepHistoricalEntryRepository.save(entry);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error saving SleepHistoricalEntry", e);
            throw e;
        }
    }

    /**
     * Updates an existing SleepHistoricalEntry entity.
     *
     * @param entry The SleepHistoricalEntry entity to update.
     */
    @Transactional(transactionManager = "saluhudAdminTransactionManager")
    public void updateSleepHistoricalEntry(@Valid SleepHistoricalEntry entry) {
        try {
            Optional<SleepHistoricalEntry> result = sleepHistoricalEntryRepository.findById(entry.getId());

            if (result.isPresent()) {
                SleepHistoricalEntry existingEntry = result.get();
                existingEntry.setEntryDate(entry.getEntryDate());
                existingEntry.setHoursSlept(entry.getHoursSlept());
                existingEntry.setMinutesSlept(entry.getMinutesSlept());
                existingEntry.setSleepEvaluation(entry.getSleepEvaluation());

                sleepHistoricalEntryRepository.save(existingEntry);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating SleepHistoricalEntry", e);
            throw e;
        }
    }

    /**
     * Deletes a SleepHistoricalEntry entity.
     *
     * @param entry The SleepHistoricalEntry entity to delete.
     */
    @Transactional(transactionManager = "saluhudAdminTransactionManager")
    public void deleteSleepHistoricalEntry(@Valid SleepHistoricalEntry entry) {
        try {
            if (this.sleepHistoricalEntryRepository.existsById(entry.getId())) {
                this.sleepHistoricalEntryRepository.delete(entry);
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting SleepHistoricalEntry", e);
            throw e;
        }
    }

    /**
     * Retrieves a SleepHistoricalEntry entity by its ID.
     *
     * @param id The ID of the SleepHistoricalEntry entity.
     * @return The SleepHistoricalEntry entity with the specified ID, or null if
     * not found.
     */
    public SleepHistoricalEntry getSleepHistoricalEntryById(long id) {
        try {
            return sleepHistoricalEntryRepository.findById(id).orElse(null);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error retrieving SleepHistoricalEntry by ID", e);
            throw e;
        }
    }
}
