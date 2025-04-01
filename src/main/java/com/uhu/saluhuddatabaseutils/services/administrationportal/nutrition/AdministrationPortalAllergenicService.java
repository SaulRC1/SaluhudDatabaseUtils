package com.uhu.saluhuddatabaseutils.services.administrationportal.nutrition;

import com.uhu.saluhuddatabaseutils.models.nutrition.Allergenic;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.Valid;
import com.uhu.saluhuddatabaseutils.repositories.administrationportal.nutrition.AdministrationPortalAllergenicRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * Service class for managing allergenics. This service provides methods to
 * perform CRUD (Create, Read, Update, Delete) operations on allergenics, such
 * as saving, updating, deleting, and retrieving allergenics by their ID or
 * name. It also supports pagination for fetching large lists of allergenics.
 *
 * @author Juan Alberto Dominguez Vazquez
 */
@Service
@Transactional(readOnly = true, transactionManager = "saluhudAdministrationPortalTransactionManager")
public class AdministrationPortalAllergenicService
{

    @Autowired
    private AdministrationPortalAllergenicRepository allergenicRepository;

    private static final Logger logger = Logger.getLogger(AdministrationPortalAllergenicService.class.getName());

    /**
     * Finds all allergenics. This method retrieves all allergenics stored in
     * the repository.
     *
     * @return A list of all allergenics.
     */
    public List<Allergenic> findAllAllergenics()
    {
        return this.allergenicRepository.findAll();
    }

    /**
     * Find an allergen by ID. This method retrieves an allergenic by its ID
     * from the repository. If the allergenic is not found, an exception is
     * thrown.
     *
     * @param id The ID of the allergen.
     * @return The allergen.
     * @throws EntityNotFoundException if the allergenic is not found.
     */
    public Allergenic findById(long id)
    {
        return this.allergenicRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Alérgeno no encontrado con ID: " + id));
    }

    /**
     * Save a new allergenic. This method saves a new allergenic to the
     * repository.
     *
     * @param allergenic The allergenic to save.
     */
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    public void saveAllergenic(@Valid Allergenic allergenic)
    {
        this.allergenicRepository.save(allergenic);
    }

    /**
     * Update the allergenic. This method updates an existing allergenic in the
     * repository. If the allergenic exists, its name will be updated.
     *
     * @param allergenic The allergenic to update.
     */
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    public void updateAllergenic(@Valid Allergenic allergenic)
    {
        try
        {
            Optional<Allergenic> result = this.allergenicRepository.findById(allergenic.getId());

            if (result.isPresent())
            {
                Allergenic existingAllergenic = result.get();
                if (!allergenic.getName().isBlank())
                {
                    existingAllergenic.setName(allergenic.getName());
                }

                this.allergenicRepository.save(existingAllergenic);
            }
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error updating Allergenic", e);
            throw e;
        }
    }

    /**
     * Delete an allergenic. This method deletes an existing allergenic from the
     * repository.
     *
     * @param allergenic The allergenic to delete.
     */
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    public void deleteAllergenic(@Valid Allergenic allergenic)
    {
        try
        {
            if (this.allergenicRepository.existsById(allergenic.getId()))
            {
                this.allergenicRepository.delete(allergenic);
            }

        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error deleting allergenic", e);
            throw e;
        }
    }

    /**
     * Get an allergenic by name. This method retrieves an allergenic from the
     * repository using its name. If the allergenic is not found, it returns
     * null.
     *
     * @param name The name of the allergenic.
     * @return The allergenic if found, or null if not found.
     */
    public Allergenic getAllergenicByName(String name)
    {
        Allergenic selectedAllergenic;
        try
        {
            selectedAllergenic = this.allergenicRepository.findByName(name);

            if (selectedAllergenic == null)
            {
                return null;
            }
            else
            {
                return selectedAllergenic;
            }

        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error getting allergenic by name", e);
            throw e;
        }
    }

    /**
     * Retrieves a paginated list of allergenics. This method retrieves a
     * paginated list of allergenics from the repository.
     *
     * @param page The page number to retrieve (starting from 0).
     * @param size The number of elements per page.
     * @return A {@link Page} object containing a paginated list of
     * {@link Allergenic}.
     */
    public Page<Allergenic> getAlergens(int page, int size)
    {
        Pageable pageable = PageRequest.of(page, size);
        return allergenicRepository.findAll(pageable);
    }
}
