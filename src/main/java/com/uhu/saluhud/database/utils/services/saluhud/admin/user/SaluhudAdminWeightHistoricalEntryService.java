package com.uhu.saluhud.database.utils.services.saluhud.admin.user;

import com.uhu.saluhud.database.utils.models.user.WeightHistoricalEntry;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.uhu.saluhud.database.utils.repositories.saluhud.admin.user.SaluhudAdminWeightHistoricalEntryRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
@Service
public class SaluhudAdminWeightHistoricalEntryService {

    @Autowired
    private SaluhudAdminWeightHistoricalEntryRepository weightHistoricalEntryRepository;

    private static final Logger logger = Logger.getLogger(SaluhudAdminWeightHistoricalEntryService.class.getName());

    /**
     * Find all weight historical entries.
     *
     * @return A list of all weight historical entries.
     */
    public List<WeightHistoricalEntry> findAllWeightHistoricalEntries() {
        return this.weightHistoricalEntryRepository.findAll();
    }

    /**
     * Find a weight historical entry by its ID.
     *
     * @param id The ID of the weight historical entry.
     * @return The weight historical entry, or null if not found.
     */
    public WeightHistoricalEntry findWeightHistoricalEntryById(long id) {
        try {
            Optional<WeightHistoricalEntry> result = this.weightHistoricalEntryRepository.findById(id);
            return result.orElseThrow();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error finding weight historical entry by ID", e);
            throw e;
        }
    }

    /**
     * Find all weight historical entries associated with a specific weight
     * historical ID.
     *
     * @param weightHistoricalId The ID of the weight historical.
     * @return A list of weight historical entries.
     */
    @Transactional(readOnly = true)
    public List<WeightHistoricalEntry> findEntriesByWeightHistoricalId(Long weightHistoricalId) {
        try {
            return this.weightHistoricalEntryRepository.findByWeightHistoricalId(weightHistoricalId);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error finding entries by weight historical ID", e);
            throw e;
        }
    }

    /**
     * Find all weight historical entries for a specific date.
     *
     * @param entryDate The date of the entries.
     * @return A list of weight historical entries.
     */
    @Transactional(readOnly = true)
    public List<WeightHistoricalEntry> findEntriesByDate(LocalDate entryDate) {
        try {
            return this.weightHistoricalEntryRepository.findByEntryDate(entryDate);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error finding entries by date", e);
            throw e;
        }
    }

    /**
     * Find weight historical entries within a specified date range for a
     * specific weight historical.
     *
     * @param weightHistoricalId The ID of the weight historical.
     * @param startDate The start date of the range.
     * @param endDate The end date of the range.
     * @return A list of weight historical entries.
     */
    @Transactional(readOnly = true)
    public List<WeightHistoricalEntry> findEntriesByDateRangeAndWeightHistoricalId(Long weightHistoricalId, LocalDate startDate, LocalDate endDate) {
        try {
            return this.weightHistoricalEntryRepository.findByDateRangeAndWeightHistoricalId(weightHistoricalId, startDate, endDate);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error finding entries by date range and weight historical ID", e);
            throw e;
        }
    }

    /**
     * Find the most recent weight historical entry for a specific weight
     * historical.
     *
     * @param weightHistoricalId The ID of the weight historical.
     * @return The most recent weight historical entry.
     */
    @Transactional(readOnly = true)
    public WeightHistoricalEntry findMostRecentEntryByWeightHistoricalId(Long weightHistoricalId) {
        try {
            return this.weightHistoricalEntryRepository.findMostRecentEntryByWeightHistoricalId(weightHistoricalId);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error finding most recent entry by weight historical ID", e);
            throw e;
        }
    }

    /**
     * Save a new weight historical entry.
     *
     * @param weightHistoricalEntry The weight historical entry to save.
     */
    @Transactional
    public void saveWeightHistoricalEntry(WeightHistoricalEntry weightHistoricalEntry) {
        try {
            this.weightHistoricalEntryRepository.save(weightHistoricalEntry);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error saving weight historical entry", e);
            throw e;
        }
    }

    /**
     * Update an existing weight historical entry.
     *
     * @param weightHistoricalEntry The weight historical entry to update.
     */
    @Transactional
    public void updateWeightHistoricalEntry(WeightHistoricalEntry weightHistoricalEntry) {
        try {
            Optional<WeightHistoricalEntry> result = this.weightHistoricalEntryRepository.findById(weightHistoricalEntry.getId());

            if (result.isPresent()) {
                WeightHistoricalEntry existingEntry = result.get();
                existingEntry.setEntryDate(weightHistoricalEntry.getEntryDate());
                existingEntry.setWeightEntry(weightHistoricalEntry.getWeightEntry());
                existingEntry.setHeightEntry(weightHistoricalEntry.getHeightEntry());
                existingEntry.setKilocaloriesObjectiveEntry(weightHistoricalEntry.getKilocaloriesObjectiveEntry());

                this.weightHistoricalEntryRepository.save(existingEntry);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating weight historical entry", e);
            throw e;
        }
    }

    /**
     * Delete an existing weight historical entry.
     *
     * @param weightHistoricalEntry The weight historical entry to delete.
     */
    @Transactional
    public void deleteWeightHistoricalEntry(WeightHistoricalEntry weightHistoricalEntry) {
        try {
            if (this.weightHistoricalEntryRepository.existsById(weightHistoricalEntry.getId())) {
                this.weightHistoricalEntryRepository.delete(weightHistoricalEntry);
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting weight historical entry", e);
            throw e;
        }
    }
}
