package com.uhu.saluhuddatabaseutils.services.administrationportal.user;

import com.uhu.saluhuddatabaseutils.models.user.SaluhudAdmin;
import com.uhu.saluhuddatabaseutils.security.PasswordEncryptionService;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.Valid;
import com.uhu.saluhuddatabaseutils.repositories.administrationportal.user.AdministrationPortalSaluhudAdminRepository;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
@Service
@Transactional(readOnly = true, transactionManager = "saluhudAdministrationPortalTransactionManager")
public class AdministrationPortalSaluhudAdminService {

    @Autowired
    private AdministrationPortalSaluhudAdminRepository saluhudAdminRepository;
    
    @Autowired
    private PasswordEncryptionService passwordEncryptionService;

    private static final Logger logger = Logger.getLogger(AdministrationPortalSaluhudAdminService.class.getName());

    /**
     * Retrieve all SaluhudUsers.
     *
     * @return a list of all users.
     */
    public List<SaluhudAdmin> findAllUsers() {
        try {
            return saluhudAdminRepository.findAll();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error finding all users", e);
            throw e;
        }
    }

    /**
     * Find a user by their ID.
     *
     * @param id the ID of the user to find.
     * @return the found user, or null if not found.
     */
    public SaluhudAdmin getUserById(long id) {
        try {
            return saluhudAdminRepository.findById(id).orElseThrow();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error finding user by ID", e);
            throw e;
        }
    }

    /**
     * Find a user by their name.
     *
     * @param name the name of the user to find.
     * @return the found user, or null if not found.
     */
    public SaluhudAdmin getUserByName(String name) {
        try {
            return saluhudAdminRepository.findByName(name).orElseThrow();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error finding user by username", e);
            throw e;
        }
    }

    /**
     * Save a new SaluhudUser.
     *
     * @param user the user to save.
     */
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    public void saveUser(@Valid SaluhudAdmin user) {
        try {         
            saluhudAdminRepository.save(user);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error saving user", e);
            throw e;
        }
    }

    /**
     * Update an existing SaluhudUser.
     *
     * @param user the user to update.
     */
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    public void updateUser(@Valid SaluhudAdmin user) {
        try {
            Optional<SaluhudAdmin> result = saluhudAdminRepository.findById(user.getId());

            if (result.isPresent()) {
                SaluhudAdmin existingUser = result.get();
                existingUser.setUserAccount(user.getUserAccount());

                saluhudAdminRepository.save(existingUser);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error updating user", e);
            throw e;
        }
    }

    /**
     * Delete a SaluhudUser.
     *
     * @param user the user to delete.
     */
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    public void deleteUser(@Valid SaluhudAdmin user) {
        try {
            if (this.saluhudAdminRepository.existsById(user.getId())) {
                saluhudAdminRepository.delete(user);
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting user", e);
            throw e;
        }
    }
}
