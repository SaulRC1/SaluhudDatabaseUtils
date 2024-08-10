package com.uhu.saluhud.database.utils.repositories.saluhud.admin.user;

import com.uhu.saluhud.database.utils.models.user.HistoricalEvaluation;
import com.uhu.saluhud.database.utils.models.user.SleepHistoricalEntry;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
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
public interface SleepHistoricalEntryRepository extends JpaRepository<SleepHistoricalEntry, Long> {

    @Query("SELECT s FROM SleepHistoricalEntry s WHERE s.entryDate BETWEEN :startDate AND :endDate")
    List<SleepHistoricalEntry> findEntriesByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT s FROM SleepHistoricalEntry s WHERE s.sleepHistorical.id = :historicalId")
    List<SleepHistoricalEntry> findEntriesBySleepHistoricalId(@Param("historicalId") Long historicalId);

    @Query("SELECT s FROM SleepHistoricalEntry s WHERE s.sleepEvaluation = :evaluation")
    List<SleepHistoricalEntry> findEntriesBySleepEvaluation(@Param("evaluation") HistoricalEvaluation evaluation);

    @Query("SELECT SUM(s.hoursSlept) FROM SleepHistoricalEntry s WHERE s.entryDate BETWEEN :startDate AND :endDate")
    int findTotalHoursSleptInDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT SUM(s.minutesSlept) FROM SleepHistoricalEntry s WHERE s.entryDate BETWEEN :startDate AND :endDate")
    double findTotalMinutesSleptInDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
