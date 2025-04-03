package com.uhu.saluhuddatabaseutils.services.administrationportal.user;

import com.uhu.saluhuddatabaseutils.models.user.SaluhudUser;
import com.uhu.saluhuddatabaseutils.models.user.WeightHistorical;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.Valid;
import com.uhu.saluhuddatabaseutils.repositories.administrationportal.user.AdministrationPortalWeightHistoricalRepository;

/**
 * Service class for handling operations related to weight historical records.
 * Provides methods to find, save, update, and delete weight historical entries.
 *
 * @author Juan Alberto Dominguez Vazquez
 */
@Service
@Transactional(readOnly = true, transactionManager = "saluhudAdministrationPortalTransactionManager")
public class AdministrationPortalWeightHistoricalService
{

    @Autowired
    private AdministrationPortalWeightHistoricalRepository weightHistoricalRepository;

    private static final Logger logger = Logger.getLogger(AdministrationPortalWeightHistoricalService.class.getName());

    /**
     * Retrieves all weight historical records.
     *
     * @return A list of all weight historical records.
     */
    public List<WeightHistorical> findAllWeightHistoricals()
    {
        return this.weightHistoricalRepository.findAll();
    }

    /**
     * Finds a specific weight historical record by its ID.
     *
     * @param id The ID of the weight historical record to find.
     * @return The weight historical record with the given ID, or throws an
     * exception if not found.
     */
    public WeightHistorical findWeightHistoricalById(long id)
    {
        try
        {
            Optional<WeightHistorical> result = this.weightHistoricalRepository.findById(id);
            return result.orElseThrow();
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error finding weight historical by ID", e);
            throw e;
        }
    }

    /**
     * Retrieves all weight historical records for a specific user.
     *
     * @param user The user whose weight historical records are to be retrieved.
     * @return A list of weight historical records associated with the specified
     * user.
     */
    public List<WeightHistorical> findWeightHistoricalsByUser(@Valid SaluhudUser user)
    {
        try
        {
            return this.weightHistoricalRepository.findByUser(user);
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error finding weight historical by user", e);
            throw e;
        }
    }

    /**
     * Retrieves a weight historical record for a specific user by their user
     * ID.
     *
     * @param userId The ID of the user whose weight historical record is to be
     * retrieved.
     * @return The weight historical record associated with the specified user
     * ID, or null if not found.
     */
    public WeightHistorical findWeightHistoricalByUserId(long userId)
    {
        try
        {
            return this.weightHistoricalRepository.findByUserId(userId);
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error finding weight historical by user ID", e);
            throw e;
        }
    }

    /**
     * Retrieves all weight historical records containing a specific entry.
     *
     * @param entryId The ID of the entry to search for.
     * @return A list of weight historical records that contain the specified
     * entry.
     */
    public List<WeightHistorical> findWeightHistoricalsByEntryId(long entryId)
    {
        try
        {
            return this.weightHistoricalRepository.findByEntryId(entryId);
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error finding weight historical by entry ID", e);
            throw e;
        }
    }

    /**
     * Saves a new weight historical record.
     *
     * @param weightHistorical The weight historical record to save.
     */
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    public void saveWeightHistorical(@Valid WeightHistorical weightHistorical)
    {
        try
        {
            this.weightHistoricalRepository.save(weightHistorical);
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error saving weight historical", e);
            throw e;
        }
    }

    /**
     * Updates an existing weight historical record.
     *
     * @param weightHistorical The weight historical record to update.
     */
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    public void updateWeightHistorical(@Valid WeightHistorical weightHistorical)
    {
        try
        {
            Optional<WeightHistorical> result = this.weightHistoricalRepository.findById(weightHistorical.getId());

            if (result.isPresent())
            {
                WeightHistorical existingHistorical = result.get();
                existingHistorical.setUser(weightHistorical.getUser());
                existingHistorical.setEntries(weightHistorical.getEntries());

                this.weightHistoricalRepository.save(existingHistorical);
            }
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error updating weight historical", e);
            throw e;
        }
    }

    /**
     * Deletes an existing weight historical record.
     *
     * @param weightHistorical The weight historical record to delete.
     */
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    public void deleteWeightHistorical(@Valid WeightHistorical weightHistorical)
    {
        try
        {
            if (this.weightHistoricalRepository.existsById(weightHistorical.getId()))
            {
                this.weightHistoricalRepository.delete(weightHistorical);
            }

        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error deleting weight historical", e);
            throw e;
        }
    }
}
