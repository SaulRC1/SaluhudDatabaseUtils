package com.uhu.saluhud.database.utils.models.nutrition.services;

import com.uhu.saluhud.database.utils.models.nutrition.Allergenic;
import com.uhu.saluhud.database.utils.models.repositories.nutrition.AllergenicRepository;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for managing allergenics.
 *
 * @author Juan Alberto Dominguez Vazquez
 */
@Service
public class AllergenicService {

    @Autowired
    private AllergenicRepository allergenicRepository;

    private static final Logger logger = Logger.getLogger(AllergenicService.class.getName());

    public List<Allergenic> findAllAllergenics() {
        return this.allergenicRepository.findAll();
    }

    /**
     * Save a new allergenic.
     *
     * @param allergenic The allergenic to save.
     */
    @Transactional
    public void saveAllergenic(Allergenic allergenic) {
        this.allergenicRepository.save(allergenic);
    }

    /**
     * Update the allergenic.
     *
     * @param allergenic the allergenic that is going to be update.
     */
    @Transactional
    public void updateAllergenic(Allergenic allergenic) {
        try {
            this.allergenicRepository.save(allergenic);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating allergenic", e);
            throw e;
        }
    }

    /**
     * Delete a allergenic
     *
     * @param allergenic the allergenic to delete.
     */
    @Transactional
    public void deleteAllergenic(Allergenic allergenic) {
        try {
            this.allergenicRepository.delete(allergenic);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting allergenic", e);
            throw e;
        }
    }

    /**
     * Getting a allergenic by name
     *
     * @param name the name of the allergenic
     * @return the allergenic if exists
     */
    public Allergenic getAllergenicByName(String name) {
        Allergenic selectedAllergenic;
        try {
            selectedAllergenic = this.allergenicRepository.findByName(name);

            if (selectedAllergenic == null) {
                return null;
            } else {
                return selectedAllergenic;
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error getting allergenic by name", e);
            throw e;
        }
    }
}
