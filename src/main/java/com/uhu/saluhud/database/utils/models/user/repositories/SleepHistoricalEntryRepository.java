package com.uhu.saluhud.database.utils.models.user.repositories;

import com.uhu.saluhud.database.utils.models.user.SleepHistoricalEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This is the sleep historical entry repository to implement basic CRUD operations
 *
 * @author Juan Alberto Domínguez Vázquez
 */
@Repository
public interface SleepHistoricalEntryRepository extends JpaRepository<SleepHistoricalEntry, Long> {
    
}
