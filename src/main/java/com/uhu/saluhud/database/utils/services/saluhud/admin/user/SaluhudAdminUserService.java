package com.uhu.saluhud.database.utils.services.saluhud.admin.user;

import com.uhu.saluhud.database.utils.models.user.SaluhudAdmin;
import com.uhu.saluhud.database.utils.security.PasswordEncryptionService;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.uhu.saluhud.database.utils.repositories.saluhud.admin.user.SaluhudAdminUserRepository;
import jakarta.validation.Valid;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
@Service
@Transactional(readOnly = true, transactionManager = "saluhudAdminTransactionManager")
public class SaluhudAdminUserService {

    @Autowired
    private SaluhudAdminUserRepository saluhudUserRepository;
    
    @Autowired
    private PasswordEncryptionService passwordEncryptionService;

    private static final Logger logger = Logger.getLogger(SaluhudAdminUserService.class.getName());

    /**
     * Retrieve all SaluhudUsers.
     *
     * @return a list of all users.
     */
    public List<SaluhudAdmin> findAllUsers() {
        try {
            return saluhudUserRepository.findAll();
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
            return saluhudUserRepository.findById(id).orElseThrow();
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
            return saluhudUserRepository.findByName(name).orElseThrow();
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
    @Transactional(transactionManager = "saluhudAdminTransactionManager")
    public void saveUser(@Valid SaluhudAdmin user) {
        try {         
            saluhudUserRepository.save(user);
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
    @Transactional(transactionManager = "saluhudAdminTransactionManager")
    public void updateUser(@Valid SaluhudAdmin user) {
        try {
            Optional<SaluhudAdmin> result = saluhudUserRepository.findById(user.getId());

            if (result.isPresent()) {
                SaluhudAdmin existingUser = result.get();
                existingUser.setCuentaUsuario(user.getCuentaUsuario());

                saluhudUserRepository.save(existingUser);
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
    @Transactional(transactionManager = "saluhudAdminTransactionManager")
    public void deleteUser(@Valid SaluhudAdmin user) {
        try {
            if (this.saluhudUserRepository.existsById(user.getId())) {
                saluhudUserRepository.delete(user);
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting user", e);
            throw e;
        }
    }
}
