package com.uhu.saluhud.database.utils.models.repositories.user;

import com.uhu.saluhud.database.utils.models.user.DailyStepsHistoricalEntry;
import com.uhu.saluhud.database.utils.models.user.HistoricalEvaluation;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * This is the daily step historical entry repository to implement basic CRUD
 * operations
 *
 * @author Juan Alberto Domínguez Vázquez
 */
@Repository
public interface DailyStepsHistoricalEntryRepository extends JpaRepository<DailyStepsHistoricalEntry, Long> {

    @Query("SELECT d FROM daily_steps_historical_Entry d WHERE d.entryDate BETWEEN :startDate AND :endDate")
    List<DailyStepsHistoricalEntry> findEntriesByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT d FROM daily_steps_historical_entry d WHERE d.stepEvaluation = :evaluation")
    List<DailyStepsHistoricalEntry> findEntriesByStepEvaluation(@Param("evaluation") HistoricalEvaluation evaluation);

    @Query("SELECT SUM(d.doneSteps) FROM daily_steps_historical_entry d WHERE d.entryDate BETWEEN :startDate AND :endDate")
    int findTotalStepsInDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT SUM(d.kiloCaloriesBurned) FROM daily_steps_historical_entry d WHERE d.entryDate BETWEEN :startDate AND :endDate")
    double findTotalCaloriesBurnedInDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT d FROM daily_steps_historical_entry d WHERE d.dailyStepsHistorical.id = :historicalId")
    List<DailyStepsHistoricalEntry> findEntriesByDailyStepsHistoricalId(@Param("historicalId") Long historicalId);

    @Query("SELECT d FROM daily_steps_historical_entry d WHERE d.doneSteps > :steps")
    List<DailyStepsHistoricalEntry> findEntriesWithStepsGreaterThan(@Param("steps") int steps);
}
