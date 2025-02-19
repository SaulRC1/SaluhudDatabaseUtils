package com.uhu.saluhud.database.utils.services.saluhud.admin.nutrition;

import com.uhu.saluhud.database.utils.models.nutrition.Allergenic;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.uhu.saluhud.database.utils.repositories.saluhud.admin.nutrition.SaluhudAdminAllergenicRepository;
import jakarta.validation.Valid;

/**
 * Service class for managing allergenics.
 *
 * @author Juan Alberto Dominguez Vazquez
 */
@Service
@Transactional(readOnly = true, transactionManager = "saluhudAdministrationPortalTransactionManager")
public class SaluhudAdminAllergenicService {

    @Autowired
    private SaluhudAdminAllergenicRepository allergenicRepository;

    private static final Logger logger = Logger.getLogger(SaluhudAdminAllergenicService.class.getName());

    public List<Allergenic> findAllAllergenics() {
        return this.allergenicRepository.findAll();
    }

    /**
     * Save a new allergenic.
     *
     * @param allergenic The allergenic to save.
     */
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    public void saveAllergenic(@Valid Allergenic allergenic) {
        this.allergenicRepository.save(allergenic);
    }

    /**
     * Update the allergenic.
     *
     * @param allergenic the allergenic that is going to be update.
     */
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    public void updateAllergenic(@Valid Allergenic allergenic) {
        try {
            Optional<Allergenic> result = this.allergenicRepository.findById(allergenic.getId());

            if (result.isPresent()) {
                Allergenic existingAllergenic = result.get();
                if (!allergenic.getName().isBlank()) {
                    existingAllergenic.setName(allergenic.getName());
                }

                this.allergenicRepository.save(existingAllergenic);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating Allergenic", e);
            throw e;
        }
    }

    /**
     * Delete a allergenic
     *
     * @param allergenic the allergenic to delete.
     */
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    public void deleteAllergenic(@Valid Allergenic allergenic) {
        try {
            if (this.allergenicRepository.existsById(allergenic.getId())) {
                this.allergenicRepository.delete(allergenic);
            }

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
