package com.uhu.saluhud.database.utils.services.saluhud.admin.security;

import com.uhu.saluhud.database.utils.repositories.saluhud.admin.security.UserAccountRepository;
import com.uhu.saluhud.database.utils.models.security.UserAccount;
import com.uhu.saluhud.database.utils.security.PasswordEncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Transactional(readOnly = true, transactionManager = "saluhudAdminTransactionManager")
public class LoginService implements UserDetailsService
{

    @Autowired
    private UserAccountRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException
    {
        Assert.notNull(username, "Username must not be null");

        UserDetails result = userRepository.findByUsername(username);

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
}
