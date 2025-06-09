package com.uhu.saluhuddatabaseutils.repositories.mobileapp.user;

import com.uhu.saluhuddatabaseutils.models.user.DailyStepsHistoricalEntry;
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
public interface MobileAppDailyStepsHistoricalEntryRepository extends JpaRepository<DailyStepsHistoricalEntry, Long>
{

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends DailyStepsHistoricalEntry> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends DailyStepsHistoricalEntry> S saveAndFlush(S entity);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void flush();

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends DailyStepsHistoricalEntry> List<S> saveAll(Iterable<S> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends DailyStepsHistoricalEntry> S save(S entity);
    
    @Query("""
        SELECT e 
        FROM DailyStepsHistoricalEntry e 
        WHERE e.dailyStepsHistorical.id = :dailyStepsHistoricalId 
        AND e.entryDate BETWEEN :startDate AND :endDate
        ORDER BY e.entryDate
    """)
    List<DailyStepsHistoricalEntry> findByDateRange(
            @Param("dailyStepsHistoricalId") Long dailyStepsHistoricalId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
    
    @Query("""
    SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END
    FROM DailyStepsHistoricalEntry e
    WHERE e.entryDate = :entryDate
      AND e.dailyStepsHistorical.id = :dailyStepsHistoricalId
    """)
    boolean existsByEntryDate(
            @Param("entryDate") LocalDate entryDate,
            @Param("dailyStepsHistoricalId") Long dailyStepsHistoricalId
    );
    
}
