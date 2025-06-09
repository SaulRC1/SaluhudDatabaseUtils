package com.uhu.saluhuddatabaseutils.repositories.mobileapp.user;

import com.uhu.saluhuddatabaseutils.models.user.SleepHistoricalEntry;
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
public interface MobileAppSleepHistoricalEntryRepository extends JpaRepository<SleepHistoricalEntry, Long>
{

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends SleepHistoricalEntry> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends SleepHistoricalEntry> S saveAndFlush(S entity);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void flush();

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends SleepHistoricalEntry> List<S> saveAll(Iterable<S> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends SleepHistoricalEntry> S save(S entity);
    
    @Query("""
        SELECT e 
        FROM SleepHistoricalEntry e 
        WHERE e.sleepHistorical.id = :sleepHistoricalId 
        AND e.entryDate BETWEEN :startDate AND :endDate
        ORDER BY e.entryDate
    """)
    List<SleepHistoricalEntry> findByDateRange(
            @Param("sleepHistoricalId") Long sleepHistoricalId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
    
    @Query("""
    SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END
    FROM SleepHistoricalEntry e
    WHERE e.entryDate = :entryDate
      AND e.sleepHistorical.id = :sleepHistoricalId
    """)
    boolean existsByEntryDate(
            @Param("entryDate") LocalDate entryDate,
            @Param("sleepHistoricalId") Long sleepHistoricalId
    );

}
