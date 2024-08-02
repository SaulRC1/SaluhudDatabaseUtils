package com.uhu.saluhud.database.utils.models.repositories.user;

import com.uhu.saluhud.database.utils.models.user.SaluhudUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This is the saluhud user repository to implement basic CRUD operations
 *
 * @author Juan Alberto Domínguez Vázquez
 */
@Repository
public interface SaluhudUserRepository extends JpaRepository<SaluhudUser, Long> {
    
}
