package com.uhu.saluhud.database.utils.services.saluhud.admin.user;

import com.uhu.saluhud.database.utils.models.user.SaluhudUser;
import com.uhu.saluhud.database.utils.models.user.WeightHistorical;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.uhu.saluhud.database.utils.repositories.saluhud.admin.user.SaluhudAdminWeightHistoricalRepository;
import jakarta.validation.Valid;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
@Service
@Transactional(readOnly = true, transactionManager = "saluhudAdminTransactionManager")
public class SaluhudAdminWeightHistoricalService {

    @Autowired
    private SaluhudAdminWeightHistoricalRepository weightHistoricalRepository;

    private static final Logger logger = Logger.getLogger(SaluhudAdminWeightHistoricalService.class.getName());

    /**
     * Find all weight historical records.
     *
     * @return A list of all weight historical records.
     */
    public List<WeightHistorical> findAllWeightHistoricals() {
        return this.weightHistoricalRepository.findAll();
    }

    /**
     * Find weight historical by ID.
     *
     * @param id The ID of the weight historical record to find.
     * @return The weight historical record, or null if not found.
     */
    public WeightHistorical findWeightHistoricalById(long id) {
        try {
            Optional<WeightHistorical> result = this.weightHistoricalRepository.findById(id);
            return result.orElseThrow();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error finding weight historical by ID", e);
            throw e;
        }
    }

    /**
     * Find weight historical records by user.
     *
     * @param user The user whose weight historical records are to be found.
     * @return A list of weight historical records for the specified user.
     */
    public List<WeightHistorical> findWeightHistoricalsByUser(@Valid SaluhudUser user) {
        try {
            return this.weightHistoricalRepository.findByUser(user);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error finding weight historical by user", e);
            throw e;
        }
    }

    /**
     * Find weight historical by user ID.
     *
     * @param userId The ID of the user whose weight historical record is to be
     * found.
     * @return The weight historical record, or null if not found.
     */
    public WeightHistorical findWeightHistoricalByUserId(long userId) {
        try {
            return this.weightHistoricalRepository.findByUserId(userId);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error finding weight historical by user ID", e);
            throw e;
        }
    }

    /**
     * Find weight historical records containing a specific entry.
     *
     * @param entryId The ID of the entry to search for.
     * @return A list of weight historical records containing the specified
     * entry.
     */
    public List<WeightHistorical> findWeightHistoricalsByEntryId(long entryId) {
        try {
            return this.weightHistoricalRepository.findByEntryId(entryId);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error finding weight historical by entry ID", e);
            throw e;
        }
    }

    /**
     * Save a new weight historical record.
     *
     * @param weightHistorical The weight historical record to save.
     */
    @Transactional(transactionManager = "saluhudAdminTransactionManager")
    public void saveWeightHistorical(@Valid WeightHistorical weightHistorical) {
        try {
            this.weightHistoricalRepository.save(weightHistorical);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error saving weight historical", e);
            throw e;
        }
    }

    /**
     * Update an existing weight historical record.
     *
     * @param weightHistorical The weight historical record to update.
     */
    @Transactional(transactionManager = "saluhudAdminTransactionManager")
    public void updateWeightHistorical(@Valid WeightHistorical weightHistorical) {
        try {
            Optional<WeightHistorical> result = this.weightHistoricalRepository.findById(weightHistorical.getId());

            if (result.isPresent()) {
                WeightHistorical existingHistorical = result.get();
                existingHistorical.setUser(weightHistorical.getUser());
                existingHistorical.setEntries(weightHistorical.getEntries());

                this.weightHistoricalRepository.save(existingHistorical);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating weight historical", e);
            throw e;
        }
    }

    /**
     * Delete an existing weight historical record.
     *
     * @param weightHistorical The weight historical record to delete.
     */
    @Transactional(transactionManager = "saluhudAdminTransactionManager")
    public void deleteWeightHistorical(@Valid WeightHistorical weightHistorical) {
        try {
            if (this.weightHistoricalRepository.existsById(weightHistorical.getId())) {
                this.weightHistoricalRepository.delete(weightHistorical);
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting weight historical", e);
            throw e;
        }
    }
}
