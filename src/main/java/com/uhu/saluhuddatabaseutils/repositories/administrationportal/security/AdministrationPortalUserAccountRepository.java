package com.uhu.saluhuddatabaseutils.repositories.administrationportal.security;

import com.uhu.saluhuddatabaseutils.models.security.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Juan Alberto Domínguez Vázquez
 */
@Repository
public interface AdministrationPortalUserAccountRepository extends JpaRepository<UserAccount, Long>
{

    @Query("SELECT u FROM UserAccount u WHERE u.username = :username")
    UserAccount findByUsername(@Param("username") String username);

}
