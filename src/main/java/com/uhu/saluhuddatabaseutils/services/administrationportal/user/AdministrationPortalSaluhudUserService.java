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
import java.util.NoSuchElementException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * Service class for managing users in the Saluhud Administration Portal.
 * Provides methods for retrieving, creating, updating, and deleting users.
 */
@Service
@Transactional(readOnly = true, transactionManager = "saluhudAdministrationPortalTransactionManager")
public class AdministrationPortalSaluhudUserService
{

    @Autowired
    private AdministrationPortalSaluhudUserRepository saluhudUserRepository;

    private static final Logger logger = Logger.getLogger(AdministrationPortalSaluhudUserService.class.getName());

    /**
     * Retrieves a list of all users stored in the repository.
     *
     * @return a {@link List} containing all {@link SaluhudUser} instances.
     * @throws RuntimeException if an error occurs while retrieving users.
     */
    public List<SaluhudUser> findAllUsers()
    {
        try
        {
            return saluhudUserRepository.findAll();
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error finding all users", e);
            throw e;
        }
    }

    /**
     * Retrieves a user by their unique identifier.
     *
     * @param id the unique ID of the user to retrieve.
     * @return the {@link SaluhudUser} if found.
     * @throws NoSuchElementException if no user with the given ID exists.
     * @throws RuntimeException if an error occurs while retrieving the user.
     */
    public SaluhudUser getUserById(long id)
    {
        try
        {
            return saluhudUserRepository.findById(id).orElseThrow();
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error finding user by ID: " + id, e);
            throw e;
        }
    }

    /**
     * Retrieves a user by their name.
     *
     * @param name the name of the user to retrieve.
     * @return the {@link SaluhudUser} if found.
     * @throws NoSuchElementException if no user with the given name exists.
     * @throws RuntimeException if an error occurs while retrieving the user.
     */
    public SaluhudUser getUserByName(String name)
    {
        try
        {
            return saluhudUserRepository.findByName(name).orElseThrow();
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error finding user by name: " + name, e);
            throw e;
        }
    }

    /**
     * Saves a new user to the repository.
     *
     * @param user the {@link SaluhudUser} object to be saved.
     * @throws IllegalArgumentException if the user is invalid.
     * @throws RuntimeException if an error occurs while saving the user.
     */
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    public void saveUser(@Valid SaluhudUser user)
    {
        try
        {
            saluhudUserRepository.save(user);
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error saving user: " + user, e);
            throw e;
        }
    }

    /**
     * Updates an existing user in the repository.
     *
     * @param user the {@link SaluhudUser} object containing updated
     * information.
     * @throws NoSuchElementException if the user does not exist.
     * @throws IllegalArgumentException if the user is invalid.
     * @throws RuntimeException if an error occurs while updating the user.
     */
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    public void updateUser(@Valid SaluhudUser user)
    {
        try
        {
            Optional<SaluhudUser> result = saluhudUserRepository.findById(user.getId());

            if (result.isPresent())
            {
                SaluhudUser existingUser = result.get();
                saluhudUserRepository.save(existingUser);
            }

        } catch (NoSuchElementException e)
        {
            logger.log(Level.SEVERE, "Error updating user: " + user, e);
            throw e;
        }
    }

    /**
     * Deletes a user from the repository.
     *
     * @param user the {@link SaluhudUser} object to delete.
     * @throws NoSuchElementException if the user does not exist.
     * @throws RuntimeException if an error occurs while deleting the user.
     */
    @Transactional(transactionManager = "saluhudAdministrationPortalTransactionManager")
    public void deleteUser(@Valid SaluhudUser user)
    {
        try
        {
            if (saluhudUserRepository.existsById(user.getId()))
            {
                saluhudUserRepository.delete(user);
            }

        } catch (NoSuchElementException e)
        {
            logger.log(Level.SEVERE, "Error deleting user: " + user, e);
            throw e;
        }
    }

    /**
     * Retrieves a page of users stored in the repository.
     *
     * @param page the page number to retrieve (starting from 0).
     * @param size the number of elements per page.
     * @return a {@link Page} object containing a paginated list of
     * {@link SaluhudUser}.
     */
    public Page<SaluhudUser> getUsers(int page, int size)
    {
        Pageable pageable = PageRequest.of(page, size);
        return saluhudUserRepository.findAll(pageable);
    }

    /**
     * Checks if a user exists by their username.
     *
     * @param username the username to check.
     * @return {@code true} if a user with the given username exists,
     * {@code false} otherwise.
     */
    public boolean existsByUsername(String username)
    {
        return saluhudUserRepository.existsByUsername(username);
    }

    /**
     * Checks if a user exists by their email.
     *
     * @param email the email to check.
     * @return {@code true} if a user with the given email exists, {@code false}
     * otherwise.
     */
    public boolean existsByEmail(String email)
    {
        return saluhudUserRepository.existsByEmail(email);
    }

    /**
     * Checks if a user exists by their phone number.
     *
     * @param phoneNumber the phone number to check.
     * @return {@code true} if a user with the given phone number exists,
     * {@code false} otherwise.
     */
    public boolean existsByPhoneNumber(String phoneNumber)
    {
        return saluhudUserRepository.existsByPhoneNumber(phoneNumber);
    }

}
