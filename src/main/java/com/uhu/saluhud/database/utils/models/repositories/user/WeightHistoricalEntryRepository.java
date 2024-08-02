package com.uhu.saluhud.database.utils.models.repositories.user;

import com.uhu.saluhud.database.utils.models.user.WeightHistoricalEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This is the weight historical entry repository to implement basic CRUD operations
 *
 * @author Juan Alberto Domínguez Vázquez
 */
@Repository
public interface WeightHistoricalEntryRepository extends JpaRepository<WeightHistoricalEntry, Long>{
    
}
