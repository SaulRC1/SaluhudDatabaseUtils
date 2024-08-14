package com.uhu.saluhud.database.utils.repositories.saluhud.admin.user;

import com.uhu.saluhud.database.utils.models.user.WeightHistoricalEntry;
import jakarta.persistence.LockModeType;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
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
public interface SaluhudAdminWeightHistoricalEntryRepository extends JpaRepository<WeightHistoricalEntry, Long> {

    @Lock(LockModeType.OPTIMISTIC)
    List<WeightHistoricalEntry> findByWeightHistoricalId(long weightHistoricalId);

    @Lock(LockModeType.OPTIMISTIC)
    List<WeightHistoricalEntry> findByEntryDate(LocalDate entryDate);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT e FROM WeightHistoricalEntry e WHERE e.weightHistorical.id = :weightHistoricalId AND e.entryDate BETWEEN :startDate AND :endDate")
    List<WeightHistoricalEntry> findByDateRangeAndWeightHistoricalId(
            @Param("weightHistoricalId") long weightHistoricalId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT e FROM WeightHistoricalEntry e WHERE e.weightHistorical.id = :weightHistoricalId ORDER BY e.entryDate DESC")
    WeightHistoricalEntry findMostRecentEntryByWeightHistoricalId(@Param("weightHistoricalId") long weightHistoricalId);
}
