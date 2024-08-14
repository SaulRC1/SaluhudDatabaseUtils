package com.uhu.saluhud.database.utils.repositories.saluhud.admin.user;

import com.uhu.saluhud.database.utils.models.user.HistoricalEvaluation;
import com.uhu.saluhud.database.utils.models.user.SleepHistoricalEntry;
import jakarta.persistence.LockModeType;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * This is the sleep historical entry repository to implement basic CRUD
 * operations
 *
 * @author Juan Alberto Domínguez Vázquez
 */
@Repository
public interface SaluhudAdminSleepHistoricalEntryRepository extends JpaRepository<SleepHistoricalEntry, Long> {

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT s FROM SleepHistoricalEntry s WHERE s.entryDate BETWEEN :startDate AND :endDate")
    List<SleepHistoricalEntry> findEntriesByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT s FROM SleepHistoricalEntry s WHERE s.sleepHistorical.id = :historicalId")
    List<SleepHistoricalEntry> findEntriesBySleepHistoricalId(@Param("historicalId") long historicalId);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT s FROM SleepHistoricalEntry s WHERE s.sleepEvaluation = :evaluation")
    List<SleepHistoricalEntry> findEntriesBySleepEvaluation(@Param("evaluation") HistoricalEvaluation evaluation);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT SUM(s.hoursSlept) FROM SleepHistoricalEntry s WHERE s.entryDate BETWEEN :startDate AND :endDate")
    int findTotalHoursSleptInDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT SUM(s.minutesSlept) FROM SleepHistoricalEntry s WHERE s.entryDate BETWEEN :startDate AND :endDate")
    double findTotalMinutesSleptInDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
