package com.uhu.saluhud.database.utils.models.user.repositories;

import com.uhu.saluhud.database.utils.models.user.SaluhudUserPersonalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This is the saluhud user personal data repository to implement basic CRUD operations
 *
 * @author Juan Alberto Domínguez Vázquez
 */
@Repository
public interface SaluhudUserPersonalDataRepository extends JpaRepository<SaluhudUserPersonalData, Long> {
    
}
