package com.uhu.saluhuddatabaseutils.repositories.mobileapp.user;

import com.uhu.saluhuddatabaseutils.models.user.WeightHistoricalEntry;
import jakarta.persistence.LockModeType;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
public interface MobileAppWeightHistoricalEntryRepository extends JpaRepository<WeightHistoricalEntry, Long>
{

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends WeightHistoricalEntry> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends WeightHistoricalEntry> S saveAndFlush(S entity);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void flush();

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends WeightHistoricalEntry> List<S> saveAll(Iterable<S> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends WeightHistoricalEntry> S save(S entity);
    
    @Query("""
        SELECT e 
        FROM WeightHistoricalEntry e 
        WHERE e.weightHistorical.id = :weightHistoricalId 
        AND e.entryDate BETWEEN :startDate AND :endDate
        ORDER BY e.entryDate
    """)
    List<WeightHistoricalEntry> findByDateRange(
            @Param("weightHistoricalId") Long weightHistoricalId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
    
    @Query("""
    SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END
    FROM WeightHistoricalEntry e
    WHERE e.entryDate = :entryDate
      AND e.weightHistorical.id = :weightHistoricalId
    """)
    boolean existsByEntryDate(
            @Param("entryDate") LocalDate entryDate,
            @Param("weightHistoricalId") Long weightHistoricalId
    );

}
