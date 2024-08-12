package com.uhu.saluhud.database.utils.services.saluhud.admin.user;

import com.uhu.saluhud.database.utils.models.user.SaluhudUserFitnessData;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.uhu.saluhud.database.utils.repositories.saluhud.admin.user.SaluhudAdminUserFitnessDataRepository;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
@Service
public class SaluhudAdminUserFitnessDataService {

    @Autowired
    private SaluhudAdminUserFitnessDataRepository fitnessDataRepository;

    private static final Logger logger = Logger.getLogger(SaluhudAdminUserFitnessDataService.class.getName());

    /**
     * Find all fitness data records.
     *
     * @return A list of all fitness data records.
     */
    public List<SaluhudUserFitnessData> findAllFitnessData() {
        return this.fitnessDataRepository.findAll();
    }

    /**
     * Find fitness data by ID.
     *
     * @param id The ID of the fitness data to find.
     * @return The fitness data record, or null if not found.
     */
    public SaluhudUserFitnessData findFitnessDataById(long id) {
        try {
            Optional<SaluhudUserFitnessData> result = this.fitnessDataRepository.findById(id);
            return result.orElse(null);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error finding fitness data by ID", e);
            throw e;
        }
    }

    /**
     * Find fitness data by biological sex.
     *
     * @param biologicalSex The biological sex to search for.
     * @return A list of fitness data records matching the specified biological
     * sex.
     */
    @Transactional(readOnly = true)
    public List<SaluhudUserFitnessData> findFitnessDataByBiologicalSex(String biologicalSex) {
        try {
            return this.fitnessDataRepository.findByBiologicalSex(biologicalSex);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error finding fitness data by biological sex", e);
            throw e;
        }
    }

    /**
     * Find fitness data by body mass index.
     *
     * @param bodyMassIndex The body mass index to search for.
     * @return A list of fitness data records matching the specified BMI.
     */
    @Transactional(readOnly = true)
    public List<SaluhudUserFitnessData> findFitnessDataByBodyMassIndex(String bodyMassIndex) {
        try {
            return this.fitnessDataRepository.findByBodyMassIndex(bodyMassIndex);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error finding fitness data by BMI", e);
            throw e;
        }
    }

    /**
     * Find fitness data records within a specific weight range.
     *
     * @param minWeight The minimum weight.
     * @param maxWeight The maximum weight.
     * @return A list of fitness data records within the specified weight range.
     */
    @Transactional(readOnly = true)
    public List<SaluhudUserFitnessData> findFitnessDataByWeightRange(double minWeight, double maxWeight) {
        try {
            return this.fitnessDataRepository.findByWeightRange(minWeight, maxWeight);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error finding fitness data by weight range", e);
            throw e;
        }
    }

    /**
     * Find fitness data records within a specific height range.
     *
     * @param minHeight The minimum height.
     * @param maxHeight The maximum height.
     * @return A list of fitness data records within the specified height range.
     */
    @Transactional(readOnly = true)
    public List<SaluhudUserFitnessData> findFitnessDataByHeightRange(double minHeight, double maxHeight) {
        try {
            return this.fitnessDataRepository.findByHeightRange(minHeight, maxHeight);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error finding fitness data by height range", e);
            throw e;
        }
    }

    /**
     * Find fitness data records within a specific age range.
     *
     * @param minAge The minimum age.
     * @param maxAge The maximum age.
     * @return A list of fitness data records within the specified age range.
     */
    @Transactional(readOnly = true)
    public List<SaluhudUserFitnessData> findFitnessDataByAgeRange(int minAge, int maxAge) {
        try {
            return this.fitnessDataRepository.findByAgeRange(minAge, maxAge);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error finding fitness data by age range", e);
            throw e;
        }
    }

    /**
     * Save a new fitness data record.
     *
     * @param fitnessData The fitness data to save.
     */
    @Transactional
    public void saveFitnessData(SaluhudUserFitnessData fitnessData) {
        try {
            this.fitnessDataRepository.save(fitnessData);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error saving fitness data", e);
            throw e;
        }
    }

    /**
     * Update an existing fitness data record.
     *
     * @param fitnessData The fitness data to update.
     */
    @Transactional
    public void updateFitnessData(SaluhudUserFitnessData fitnessData) {
        try {
            Optional<SaluhudUserFitnessData> result = this.fitnessDataRepository.findById(fitnessData.getId());

            if (result.isPresent()) {
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
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating fitness data", e);
            throw e;
        }
    }

    /**
     * Delete an existing fitness data record.
     *
     * @param fitnessData The fitness data to delete.
     */
    @Transactional
    public void deleteFitnessData(SaluhudUserFitnessData fitnessData) {
        try {
            this.fitnessDataRepository.delete(fitnessData);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting fitness data", e);
            throw e;
        }
    }
}
