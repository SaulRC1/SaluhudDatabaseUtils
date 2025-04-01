package com.uhu.saluhuddatabaseutils.services.administrationportal.security;

import com.uhu.saluhuddatabaseutils.models.security.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import com.uhu.saluhuddatabaseutils.repositories.administrationportal.security.AdministrationPortalUserAccountRepository;

/**
 * Service class for managing user login and authentication details. This
 * service implements the UserDetailsService interface and provides methods to
 * load user details by username, match passwords, and get the currently
 * authenticated user. It integrates with Spring Security for user
 * authentication and authorization.
 *
 * @author Juan Alberto Dominguez Vazquez
 */
@Service
@Transactional(readOnly = true, transactionManager = "saluhudAdministrationPortalTransactionManager")
public class AdministrationPortalLoginService implements UserDetailsService
{

    @Autowired
    private AdministrationPortalUserAccountRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Loads user details by username. This method retrieves a UserAccount
     * object from the repository using the provided username. If the username
     * is not found, it throws a UsernameNotFoundException.
     *
     * @param username The username of the user to load.
     * @return The user details if found.
     * @throws UsernameNotFoundException if the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException
    {
        Assert.notNull(username, "Username must not be null");

        UserAccount result = userRepository.findByUsername(username);

        if (result == null)
        {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        // Ensure authorities are initialized
        Assert.notNull(result.getAuthorities(), "User authorities must not be null");

        return result;
    }

    /**
     * Retrieves the currently authenticated user. This method returns the
     * UserAccount of the currently authenticated user from the SecurityContext.
     * It ensures that the authentication and security context are not null, and
     * the principal is of type UserAccount.
     *
     * @return The currently authenticated UserAccount.
     * @throws IllegalArgumentException if the principal is not a UserAccount or
     * if the SecurityContext or authentication is null.
     */
    public static UserAccount getPrincipal()
    {
        SecurityContext context = SecurityContextHolder.getContext();
        Assert.notNull(context, "SecurityContext must not be null");

        Authentication authentication = context.getAuthentication();
        Assert.notNull(authentication, "Authentication must not be null");

        Object principal = authentication.getPrincipal();
        Assert.isTrue(principal instanceof UserAccount, "Principal must be an instance of UserAccount");

        UserAccount result = (UserAccount) principal;
        Assert.notNull(result, "UserAccount must not be null");
        Assert.isTrue(result.getId() != 0, "UserAccount ID must be non-zero");

        return result;
    }

    /**
     * Compares a raw password with an encoded password. This method checks if
     * the provided raw password matches the encoded password using the
     * configured PasswordEncoder.
     *
     * @param rawPassword The raw password to check.
     * @param encodedPassword The encoded password to compare against.
     * @return True if the passwords match, otherwise false.
     */
    public boolean matchPassword(String rawPassword, String encodedPassword)
    {
        return passwordEncoder.matches(rawPassword, encodedPassword); // Compares passwords
    }
}
