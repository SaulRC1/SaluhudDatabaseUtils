package com.uhu.saluhud.database.utils.models.user.repositories;

import com.uhu.saluhud.database.utils.models.user.WeightHistoricalEntry;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * This is the weight historical entry repository to implement basic CRUD
 * operations
 *
 * @author Juan Alberto Domínguez Vázquez
 */
@Repository
public interface WeightHistoricalEntryRepository extends JpaRepository<WeightHistoricalEntry, Long> {

    List<WeightHistoricalEntry> findByWeightHistoricalId(Long weightHistoricalId);

    List<WeightHistoricalEntry> findByEntryDate(LocalDate entryDate);

    @Query("SELECT e FROM WeightHistoricalEntry e WHERE e.weightHistorical.id = :weightHistoricalId AND e.entryDate BETWEEN :startDate AND :endDate")
    List<WeightHistoricalEntry> findByDateRangeAndWeightHistoricalId(
            @Param("weightHistoricalId") Long weightHistoricalId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    @Query("SELECT e FROM WeightHistoricalEntry e WHERE e.weightHistorical.id = :weightHistoricalId ORDER BY e.entryDate DESC")
    WeightHistoricalEntry findMostRecentEntryByWeightHistoricalId(@Param("weightHistoricalId") Long weightHistoricalId);
}
