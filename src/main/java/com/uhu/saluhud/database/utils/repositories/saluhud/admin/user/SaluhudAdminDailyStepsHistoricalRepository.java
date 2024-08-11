package com.uhu.saluhud.database.utils.repositories.saluhud.admin.user;

import com.uhu.saluhud.database.utils.models.user.DailyStepsHistorical;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * This is the daily step historical repository to implement basic CRUD
 * operations
 *
 * @author Juan Alberto Domínguez Vázquez
 */
@Repository
public interface SaluhudAdminDailyStepsHistoricalRepository extends JpaRepository<DailyStepsHistorical, Long> {

    @Query("SELECT d FROM DailyStepsHistorical d WHERE d.user.id = :userId")
    List<DailyStepsHistorical> findAllByUserId(@Param("userId") Long userId);

    @Query("SELECT d FROM DailyStepsHistorical d WHERE SIZE(d.entries) >= :minEntries")
    List<DailyStepsHistorical> findAllWithMinEntries(@Param("minEntries") int minEntries);

    @Query("SELECT d FROM DailyStepsHistorical d JOIN FETCH d.entries WHERE d.user.id = :userId")
    List<DailyStepsHistorical> findAllWithEntriesByUserId(@Param("userId") Long userId);

    @Query("SELECT COUNT(d) FROM DailyStepsHistorical d WHERE d.user.id = :userId")
    int countByUserId(@Param("userId") Long userId);
}
