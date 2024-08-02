package com.uhu.saluhud.database.utils.models.repositories.user;

import com.uhu.saluhud.database.utils.models.user.DailyStepsHistoricalEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This is the daily step historical entry repository to implement basic CRUD operations
 *
 * @author Juan Alberto Domínguez Vázquez
 */
@Repository
public interface DailyStepsHistoricalEntryRepository extends JpaRepository<DailyStepsHistoricalEntry, Long> {
    
}
