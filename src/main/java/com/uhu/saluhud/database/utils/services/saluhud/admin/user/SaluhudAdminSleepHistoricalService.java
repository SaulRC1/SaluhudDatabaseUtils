package com.uhu.saluhud.database.utils.services.saluhud.admin.user;

import com.uhu.saluhud.database.utils.models.user.SleepHistorical;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.uhu.saluhud.database.utils.repositories.saluhud.admin.user.SaluhudAdminSleepHistoricalRepository;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
@Service
public class SaluhudAdminSleepHistoricalService {

    @Autowired
    private SaluhudAdminSleepHistoricalRepository sleepHistoricalRepository;

    private static final Logger logger = Logger.getLogger(SaluhudAdminSleepHistoricalService.class.getName());

    /**
     * Find all SleepHistorical records.
     *
     * @return A list of all SleepHistorical records.
     */
    public List<SleepHistorical> findAll() {
        return sleepHistoricalRepository.findAll();
    }

    /**
     * Find a SleepHistorical by its ID.
     *
     * @param id The ID of the SleepHistorical.
     * @return The SleepHistorical with the specified ID.
     */
    public SleepHistorical findById(long id) {
        return sleepHistoricalRepository.findById(id).orElse(null);
    }

    /**
     * Find a SleepHistorical by the associated user ID.
     *
     * @param userId The ID of the user.
     * @return The SleepHistorical associated with the specified user.
     */
    @Transactional(readOnly = true)
    public SleepHistorical findByUserId(long userId) {
        return sleepHistoricalRepository.findByUserId(userId);
    }

    /**
     * Find all SleepHistoricals with entries within a specified date range.
     *
     * @param startDate The start date of the range.
     * @param endDate The end date of the range.
     * @return A list of SleepHistoricals with entries within the specified date
     * range.
     */
    @Transactional(readOnly = true)
    public List<SleepHistorical> findWithEntriesInDateRange(LocalDate startDate, LocalDate endDate) {
        return sleepHistoricalRepository.findWithEntriesInDateRange(startDate, endDate);
    }

    /**
     * Find all SleepHistoricals for a specific user with entries within a
     * specified date range.
     *
     * @param userId The ID of the user.
     * @param startDate The start date of the range.
     * @param endDate The end date of the range.
     * @return A list of SleepHistoricals for the specified user with entries
     * within the specified date range.
     */
    @Transactional(readOnly = true)
    public List<SleepHistorical> findByUserIdWithEntriesInDateRange(long userId, LocalDate startDate, LocalDate endDate) {
        return sleepHistoricalRepository.findByUserIdWithEntriesInDateRange(userId, startDate, endDate);
    }

    /**
     * Find all SleepHistoricals with a minimum number of entries.
     *
     * @param minEntries The minimum number of entries.
     * @return A list of SleepHistoricals with at least the specified number of
     * entries.
     */
    @Transactional(readOnly = true)
    public List<SleepHistorical> findByMinEntries(int minEntries) {
        return sleepHistoricalRepository.findByMinEntries(minEntries);
    }

    /**
     * Find the total sleep hours for a specific user within a date range.
     *
     * @param userId The ID of the user.
     * @param startDate The start date of the range.
     * @param endDate The end date of the range.
     * @return The total sleep hours for the specified user within the specified
     * date range.
     */
    @Transactional(readOnly = true)
    public double findTotalSleepHoursByUserIdAndDateRange(long userId, LocalDate startDate, LocalDate endDate) {
        return sleepHistoricalRepository.findTotalSleepHoursByUserIdAndDateRange(userId, startDate, endDate);
    }

    /**
     * Save a new SleepHistorical record.
     *
     * @param sleepHistorical The SleepHistorical to save.
     */
    @Transactional
    public void saveSleepHistorical(SleepHistorical sleepHistorical) {
        sleepHistoricalRepository.save(sleepHistorical);
    }

    /**
     * Update an existing SleepHistorical record.
     *
     * @param sleepHistorical The SleepHistorical to update.
     */
    @Transactional
    public void updateSleepHistorical(SleepHistorical sleepHistorical) {
        try {
            Optional<SleepHistorical> result = this.sleepHistoricalRepository.findById(sleepHistorical.getId());

            if (result.isPresent()) {
                SleepHistorical existingSleepHistorical = result.get();
                existingSleepHistorical.setUser(sleepHistorical.getUser());
                existingSleepHistorical.setEntries(sleepHistorical.getEntries());

                this.sleepHistoricalRepository.save(existingSleepHistorical);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating SleepHistorical", e);
            throw e;
        }
    }

    /**
     * Delete a SleepHistorical record.
     *
     * @param sleepHistorical The SleepHistorical to delete.
     */
    @Transactional
    public void deleteSleepHistorical(SleepHistorical sleepHistorical) {
        try {
            sleepHistoricalRepository.delete(sleepHistorical);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting SleepHistorical", e);
            throw e;
        }
    }
}
