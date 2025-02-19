package com.uhu.saluhuddatabaseutils.services.administrationportal.security;

import com.uhu.saluhuddatabaseutils.models.security.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
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

@Service
@Transactional(readOnly = true, transactionManager = "saluhudAdministrationPortalTransactionManager")
public class AdministrationPortalLoginService implements UserDetailsService
{

    @Autowired
    private AdministrationPortalUserAccountRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException
    {
        Assert.notNull(username, "Username must not be null");

        UserAccount result = userRepository.findByUsername(username);

        if (result == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        // Ensure authorities are initialized
        Assert.notNull(result.getAuthorities(), "User authorities must not be null");

        return result;
    }

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
    
    public boolean matchPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword); // Compara contraseñas
    }
}
