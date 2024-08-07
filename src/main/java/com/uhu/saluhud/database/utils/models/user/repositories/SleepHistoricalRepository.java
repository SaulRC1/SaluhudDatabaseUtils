package com.uhu.saluhud.database.utils.models.user.repositories;

import com.uhu.saluhud.database.utils.models.user.SleepHistorical;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * This is the sleep repository to implement basic CRUD operations
 *
 * @author Juan Alberto Domínguez Vázquez
 */
@Repository
public interface SleepHistoricalRepository extends JpaRepository<SleepHistorical, Long> {

    @Query("SELECT s FROM SleepHistorical s WHERE s.user.id = :userId")
    SleepHistorical findByUserId(@Param("userId") Long userId);

    @Query("SELECT s FROM SleepHistorical s JOIN s.entries e WHERE e.entryDate BETWEEN :startDate AND :endDate")
    List<SleepHistorical> findWithEntriesInDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT s FROM SleepHistorical s JOIN s.entries e WHERE s.user.id = :userId AND e.entryDate BETWEEN :startDate AND :endDate")
    List<SleepHistorical> findByUserIdWithEntriesInDateRange(@Param("userId") Long userId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT s FROM SleepHistorical s WHERE SIZE(s.entries) >= :minEntries")
    List<SleepHistorical> findByMinEntries(@Param("minEntries") int minEntries);

    @Query("SELECT SUM(e.sleepHours) FROM SleepHistorical s JOIN s.entries e WHERE s.user.id = :userId AND e.entryDate BETWEEN :startDate AND :endDate")
    Double findTotalSleepHoursByUserIdAndDateRange(@Param("userId") Long userId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
