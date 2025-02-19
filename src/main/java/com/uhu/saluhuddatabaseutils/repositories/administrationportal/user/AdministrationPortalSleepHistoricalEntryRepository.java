package com.uhu.saluhuddatabaseutils.repositories.administrationportal.user;

import com.uhu.saluhuddatabaseutils.models.user.HistoricalEvaluation;
import com.uhu.saluhuddatabaseutils.models.user.SleepHistoricalEntry;
import jakarta.persistence.LockModeType;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * This is the sleep historical entry repository to implement basic CRUD
 * operations
 *
 * @author Juan Alberto Domínguez Vázquez
 */
@Repository
public interface AdministrationPortalSleepHistoricalEntryRepository extends JpaRepository<SleepHistoricalEntry, Long> {

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

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends SleepHistoricalEntry> List<S> findAll(Example<S> example, Sort sort);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends SleepHistoricalEntry> List<S> findAll(Example<S> example);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public SleepHistoricalEntry getReferenceById(Long id);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAllInBatch();

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAllByIdInBatch(Iterable<Long> ids);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAllInBatch(Iterable<SleepHistoricalEntry> entities);

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
    public List<SleepHistoricalEntry> findAllById(Iterable<Long> ids);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public List<SleepHistoricalEntry> findAll();

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends SleepHistoricalEntry> List<S> saveAll(Iterable<S> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public List<SleepHistoricalEntry> findAll(Sort sort);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAll();

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAll(Iterable<? extends SleepHistoricalEntry> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAllById(Iterable<? extends Long> ids);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void delete(SleepHistoricalEntry entity);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteById(Long id);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public long count();

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public boolean existsById(Long id);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public Optional<SleepHistoricalEntry> findById(Long id);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends SleepHistoricalEntry> S save(S entity);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public Page<SleepHistoricalEntry> findAll(Pageable pageable);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends SleepHistoricalEntry, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends SleepHistoricalEntry> boolean exists(Example<S> example);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends SleepHistoricalEntry> long count(Example<S> example);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends SleepHistoricalEntry> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends SleepHistoricalEntry> Optional<S> findOne(Example<S> example);
    
}
