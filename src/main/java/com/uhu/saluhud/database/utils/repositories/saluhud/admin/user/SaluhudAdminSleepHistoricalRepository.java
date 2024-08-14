package com.uhu.saluhud.database.utils.repositories.saluhud.admin.user;

import com.uhu.saluhud.database.utils.models.user.SleepHistorical;
import jakarta.persistence.LockModeType;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * This is the sleep repository to implement basic CRUD operations
 *
 * @author Juan Alberto Domínguez Vázquez
 */
@Repository
public interface SaluhudAdminSleepHistoricalRepository extends JpaRepository<SleepHistorical, Long> {

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT s FROM SleepHistorical s WHERE s.user = :userId")
    SleepHistorical findByUserId(@Param("userId") long userId);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT s FROM SleepHistorical s JOIN s.entries e WHERE e.entryDate BETWEEN :startDate AND :endDate")
    List<SleepHistorical> findWithEntriesInDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT s FROM SleepHistorical s JOIN s.entries e WHERE s.user = :userId AND e.entryDate BETWEEN :startDate AND :endDate")
    List<SleepHistorical> findByUserIdWithEntriesInDateRange(@Param("userId") long userId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT s FROM SleepHistorical s WHERE SIZE(s.entries) >= :minEntries")
    List<SleepHistorical> findByMinEntries(@Param("minEntries") int minEntries);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT SUM(e.hoursSlept) FROM SleepHistorical s JOIN s.entries e WHERE s.user = :userId AND e.entryDate BETWEEN :startDate AND :endDate")
    double findTotalSleepHoursByUserIdAndDateRange(@Param("userId") Long userId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
