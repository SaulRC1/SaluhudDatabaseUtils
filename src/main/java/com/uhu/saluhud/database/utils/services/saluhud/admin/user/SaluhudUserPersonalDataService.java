package com.uhu.saluhud.database.utils.services.saluhud.admin.user;

import com.uhu.saluhud.database.utils.models.user.SaluhudUserPersonalData;
import com.uhu.saluhud.database.utils.repositories.saluhud.admin.user.SaluhudUserPersonalDataRepository;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
@Service
public class SaluhudUserPersonalDataService {

    @Autowired
    private SaluhudUserPersonalDataRepository personalDataRepository;

    private static final Logger logger = Logger.getLogger(SaluhudUserPersonalDataService.class.getName());

    /**
     * Find all personal data records.
     *
     * @return a list of all personal data records.
     */
    public List<SaluhudUserPersonalData> findAllPersonalData() {
        return this.personalDataRepository.findAll();
    }

    /**
     * Find personal data by ID.
     *
     * @param id the ID of the personal data to find.
     * @return the personal data record, or null if not found.
     */
    public SaluhudUserPersonalData findPersonalDataById(long id) {
        try {
            Optional<SaluhudUserPersonalData> result = this.personalDataRepository.findById(id);
            return result.orElse(null);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error finding personal data by ID", e);
            throw e;
        }
    }

    /**
     * Find personal data by name.
     *
     * @param name the name to search for.
     * @return a list of personal data records matching the specified name.
     */
    public List<SaluhudUserPersonalData> findPersonalDataByName(String name) {
        try {
            return this.personalDataRepository.findByName(name);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error finding personal data by name", e);
            throw e;
        }
    }

    /**
     * Find personal data by surname.
     *
     * @param surname the surname to search for.
     * @return a list of personal data records matching the specified surname.
     */
    public List<SaluhudUserPersonalData> findPersonalDataBySurname(String surname) {
        try {
            return this.personalDataRepository.findBySurname(surname);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error finding personal data by surname", e);
            throw e;
        }
    }

    /**
     * Find personal data by phone number.
     *
     * @param phoneNumber the phone number to search for.
     * @return the personal data record, or null if not found.
     */
    public SaluhudUserPersonalData findPersonalDataByPhoneNumber(int phoneNumber) {
        try {
            Optional<SaluhudUserPersonalData> result = this.personalDataRepository.findByPhoneNumber(phoneNumber);
            return result.orElse(null);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error finding personal data by phone number", e);
            throw e;
        }
    }

    /**
     * Save a new personal data record.
     *
     * @param personalData the personal data to save.
     */
    @Transactional
    public void savePersonalData(SaluhudUserPersonalData personalData) {
        try {
            this.personalDataRepository.save(personalData);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error saving personal data", e);
            throw e;
        }
    }

    /**
     * Update an existing personal data record.
     *
     * @param personalData the personal data to update.
     */
    @Transactional
    public void updatePersonalData(SaluhudUserPersonalData personalData) {
        try {
            Optional<SaluhudUserPersonalData> result = this.personalDataRepository.findById(personalData.getId());

            if (result.isPresent()) {
                SaluhudUserPersonalData existingData = result.get();
                existingData.setName(personalData.getName());
                existingData.setSurname(personalData.getSurname());
                existingData.setPhoneNumber(personalData.getPhoneNumber());

                this.personalDataRepository.save(existingData);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating personal data", e);
            throw e;
        }
    }

    /**
     * Delete an existing personal data record.
     *
     * @param personalData the personal data to delete.
     */
    @Transactional
    public void deletePersonalData(SaluhudUserPersonalData personalData) {
        try {
            this.personalDataRepository.delete(personalData);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting personal data", e);
            throw e;
        }
    }

    /**
     * Check if a personal data record exists by phone number.
     *
     * @param phoneNumber the phone number to check.
     * @return true if a record exists, false otherwise.
     */
    public boolean existsByPhoneNumber(int phoneNumber) {
        try {
            return this.personalDataRepository.existsByPhoneNumber(phoneNumber);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error checking existence by phone number", e);
            throw e;
        }
    }
}
