package com.uhu.saluhuddatabaseutils.services.administrationportal.user;

import com.uhu.saluhuddatabaseutils.models.user.SaluhudUserFitnessData;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.Valid;
import com.uhu.saluhuddatabaseutils.repositories.administrationportal.user.AdministrationPortalUserFitnessDataRepository;

/**
 * Service class for handling operations related to user fitness data. Provides
 * methods to find, save, update, and delete fitness data records.
 *
 * @author Juan Alberto Dominguez Vazquez
 */
@Service
@Transactional(readOnly = true, transactionManager = "saluhudAdministrationPortalTransactionManager")
public class AdministrationPortalUserFitnessDataService
{

    @Autowired
    private AdministrationPortalUserFitnessDataRepository fitnessDataRepository;

    private static final Logger logger = Logger.getLogger(AdministrationPortalUserFitnessDataService.class.getName());

    /**
     * Retrieves all fitness data records.
     *
     * @return A list of all fitness data records.
     */
    public List<SaluhudUserFitnessData> findAllFitnessData()
    {
        return this.fitnessDataRepository.findAll();
    }

    /**
     * Finds a specific fitness data record by its ID.
     *
     * @param id The ID of the fitness data record to find.
     * @return The fitness data record with the given ID, or throws an exception
     * if not found.
     */
    public SaluhudUserFitnessData findFitnessDataById(long id)
    {
        try
        {
            Optional<SaluhudUserFitnessData> result = this.fitnessDataRepository.findById(id);
            return result.orElseThrow();
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error finding fitness data by ID", e);
            throw e;
        }
    }

    /**
     * Finds fitness data records by biological sex.
     *
     * @param biologicalSex The biological sex to search for.
     * @return A list of fitness data records matching the specified biological
     * sex.
     */
    public List<SaluhudUserFitnessData> findFitnessDataByBiologicalSex(String biologicalSex)
    {
        try
        {
            return this.fitnessDataRepository.findByBiologicalSex(biologicalSex);
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error finding fitness data by biological sex", e);
            throw e;
        }
    }

    /**
     * Finds fitness data records by body mass index (BMI).
     *
     * @param bodyMassIndex The body mass index to search for.
     * @return A list of fitness data records matching the specified BMI.
     */
    public List<SaluhudUserFitnessData> findFitnessDataByBodyMassIndex(String bodyMassIndex)
    {
        try
        {
            return this.fitnessDataRepository.findByBodyMassIndex(bodyMassIndex);
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error finding fitness data by BMI", e);
            throw e;
        }
    }

    /**
     * Finds fitness data records within a specific weight range.
     *
     * @param minWeight The minimum weight.
     * @param maxWeight The maximum weight.
     * @return A list of fitness data records within the specified weight range.
     */
    public List<SaluhudUserFitnessData> findFitnessDataByWeightRange(double minWeight, double maxWeight)
    {
        try
        {
            return this.fitnessDataRepository.findByWeightRange(minWeight, maxWeight);
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error finding fitness data by weight range", e);
            throw e;
        }
    }

    /**
     * Finds fitness data records within a specific height range.
     *
     * @param minHeight The minimum height.
     * @param maxHeight The maximum height.
     * @return A list of fitness data records within the specified height range.
     */
    public List<SaluhudUserFitnessData> findFitnessDataByHeightRange(double minHeight, double maxHeight)
    {
        try
        {
            return this.fitnessDataRepository.findByHeightRange(minHeight, maxHeight);
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error finding fitness data by height range", e);
            throw e;
        }
    }

    /**
     * Finds fitness data records within a specific age range.
     *
     * @param minAge The minimum age.
     * @param maxAge The maximum age.
     * @return A list of fitness data records within the specified age range.
     */
    public List<SaluhudUserFitnessData> findFitnessDataByAgeRange(int minAge, int maxAge)
    {
        try
        {
            return this.fitnessDataRepository.findByAgeRange(minAge, maxAge);
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error finding fitness data by age range", e);
            throw e;
        }
    }

    /**
     * Saves a new fitness data record.
     *
     * @param fitnessData The fitness data to save.
     */
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    public void saveFitnessData(@Valid SaluhudUserFitnessData fitnessData)
    {
        try
        {
            this.fitnessDataRepository.save(fitnessData);
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error saving fitness data", e);
            throw e;
        }
    }

    /**
     * Updates an existing fitness data record.
     *
     * @param fitnessData The fitness data to update.
     */
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    public void updateFitnessData(@Valid SaluhudUserFitnessData fitnessData)
    {
        try
        {
            Optional<SaluhudUserFitnessData> result = this.fitnessDataRepository.findById(fitnessData.getId());

            if (result.isPresent())
            {
                SaluhudUserFitnessData existingData = result.get();
                existingData.setWeight(fitnessData.getWeight());
                existingData.setHeight(fitnessData.getHeight());
                existingData.setBiologicalSex(fitnessData.getBiologicalSex());
                existingData.setAge(fitnessData.getAge());
                existingData.setBodyComposition(fitnessData.getBodyComposition());
                existingData.setRecommendedDailyWaterLiters(fitnessData.getRecommendedDailyWaterLiters());
                existingData.setRecommendedSleepHours(fitnessData.getRecommendedSleepHours());
                existingData.setRecommendedDailySteps(fitnessData.getRecommendedDailySteps());
                existingData.setDailyKilocaloriesObjective(fitnessData.getDailyKilocaloriesObjective());
                existingData.setBodyMassIndex(fitnessData.getBodyMassIndex());

                this.fitnessDataRepository.save(existingData);
            }
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error updating fitness data", e);
            throw e;
        }
    }

    /**
     * Deletes an existing fitness data record.
     *
     * @param fitnessData The fitness data to delete.
     */
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    public void deleteFitnessData(@Valid SaluhudUserFitnessData fitnessData)
    {
        try
        {
            if (this.fitnessDataRepository.existsById(fitnessData.getId()))
            {
                this.fitnessDataRepository.delete(fitnessData);
            }

        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error deleting fitness data", e);
            throw e;
        }
    }
}
