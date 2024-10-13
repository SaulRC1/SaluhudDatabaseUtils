package com.uhu.saluhud.database.utils.services.saluhud.admin.user;

import com.uhu.saluhud.database.utils.models.user.SaluhudUser;
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

    private static final Logger logger = Logger.getLogger(SaluhudAdminUserService.class.getName());

    /**
     * Retrieve all SaluhudUsers.
     *
     * @return a list of all users.
     */
    public List<SaluhudUser> findAllUsers() {
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
    public SaluhudUser getUserById(long id) {
        try {
            return saluhudUserRepository.findById(id).orElseThrow();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error finding user by ID", e);
            throw e;
        }
    }

    /**
     * Find a user by their username.
     *
     * @param username the username of the user to find.
     * @return the found user, or null if not found.
     */
    public SaluhudUser getUserByUsername(String username) {
        try {
            return saluhudUserRepository.findByUsername(username).orElseThrow();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error finding user by username", e);
            throw e;
        }
    }

    /**
     * Find a user by their email.
     *
     * @param email the email of the user to find.
     * @return the found user, or null if not found.
     */
    public SaluhudUser getUserByEmail(String email) {
        try {
            return saluhudUserRepository.findByEmail(email).orElseThrow();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error finding user by email", e);
            throw e;
        }
    }

    /**
     * Check if a user exists by their email.
     *
     * @param email the email to check.
     * @return true if the user exists, false otherwise.
     */
    public boolean userExistsByEmail(String email) {
        try {
            return saluhudUserRepository.existsByEmail(email);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error checking if user exists by email", e);
            throw e;
        }
    }

    /**
     * Save a new SaluhudUser.
     *
     * @param user the user to save.
     */
    @Transactional(transactionManager = "saluhudAdminTransactionManager")
    public void saveUser(@Valid SaluhudUser user) {
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
    public void updateUser(@Valid SaluhudUser user) {
        try {
            Optional<SaluhudUser> result = saluhudUserRepository.findById(user.getId());

            if (result.isPresent()) {
                SaluhudUser existingUser = result.get();
                existingUser.setUsername(user.getUsername());
                existingUser.setPassword(user.getPassword());
                existingUser.setEmail(user.getEmail());
                existingUser.setFitnessData(user.getFitnessData());

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
    public void deleteUser(@Valid SaluhudUser user) {
        try {
            if (this.saluhudUserRepository.existsById(user.getId())) {
                saluhudUserRepository.delete(user);
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error deleting user", e);
            throw e;
        }
    }

    /**
     * Check if a personal data record exists by phone number.
     *
     * @param phoneNumber the phone number to check.
     * @return true if a record exists, false otherwise.
     */
    public boolean existsByPhoneNumber(String phoneNumber) {
        try {
            return this.saluhudUserRepository.existsByPhoneNumber(phoneNumber);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error checking existence by phone number", e);
            throw e;
        }
    }

    /**
     * Find personal data by phone number.
     *
     * @param phoneNumber the phone number to search for.
     * @return the personal data record, or null if not found.
     */
    public SaluhudUser findPersonalDataByPhoneNumber(String phoneNumber) {
        try {
            Optional<SaluhudUser> result = this.saluhudUserRepository.findByPhoneNumber(phoneNumber);
            return result.orElseThrow();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error finding personal data by phone number", e);
            throw e;
        }
    }
}
