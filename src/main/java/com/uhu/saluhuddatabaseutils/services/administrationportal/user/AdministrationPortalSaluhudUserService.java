package com.uhu.saluhuddatabaseutils.services.administrationportal.user;

import com.uhu.saluhuddatabaseutils.models.user.SaluhudUser;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.Valid;
import com.uhu.saluhuddatabaseutils.repositories.administrationportal.user.AdministrationPortalSaluhudUserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Juan Alberto Dominguez Vazquez
 */
@Service
@Transactional(readOnly = true, transactionManager = "saluhudAdministrationPortalTransactionManager")
public class AdministrationPortalSaluhudUserService {

    @Autowired
    private AdministrationPortalSaluhudUserRepository saluhudUserRepository;
    
    private static final Logger logger = Logger.getLogger(AdministrationPortalSaluhudUserService.class.getName());

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
     * Find a user by their name.
     *
     * @param name the name of the user to find.
     * @return the found user, or null if not found.
     */
    public SaluhudUser getUserByName(String name) {
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
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
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
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    public void updateUser(@Valid SaluhudUser user) {
        try {
            Optional<SaluhudUser> result = saluhudUserRepository.findById(user.getId());

            if (result.isPresent()) {
                SaluhudUser existingUser = result.get();
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
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
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
     * Recupera una página usuarios almacenados en el repositorio.
     *
     * @param page el número de página a recuperar (comenzando desde 0).
     * @param size la cantidad de elementos por página.
     * @return un objeto {@link Page} que contiene una lista paginada de
     * {@link SaluhudUser}.
     */
    public Page<SaluhudUser> getUsers(int page, int size)
    {
        Pageable pageable = PageRequest.of(page, size);
        return saluhudUserRepository.findAll(pageable);
    }
}
