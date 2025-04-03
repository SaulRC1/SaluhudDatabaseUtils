package com.uhu.saluhuddatabaseutils.repositories.administrationportal.user;

import com.uhu.saluhuddatabaseutils.models.user.SleepHistorical;
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
 * This is the sleep repository to implement basic CRUD operations
 *
 * @author Juan Alberto Domínguez Vázquez
 */
@Repository
public interface AdministrationPortalSleepHistoricalRepository extends JpaRepository<SleepHistorical, Long>
{

    @Query("SELECT s FROM SleepHistorical s WHERE s.user = :userId")
    SleepHistorical findByUserId(@Param("userId") long userId);

    @Query("SELECT s FROM SleepHistorical s JOIN s.entries e WHERE e.entryDate BETWEEN :startDate AND :endDate")
    List<SleepHistorical> findWithEntriesInDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT s FROM SleepHistorical s JOIN s.entries e WHERE s.user = :userId AND e.entryDate BETWEEN :startDate AND :endDate")
    List<SleepHistorical> findByUserIdWithEntriesInDateRange(@Param("userId") long userId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT s FROM SleepHistorical s WHERE SIZE(s.entries) >= :minEntries")
    List<SleepHistorical> findByMinEntries(@Param("minEntries") int minEntries);

    @Query("SELECT SUM(e.hoursSlept) FROM SleepHistorical s JOIN s.entries e WHERE s.user = :userId AND e.entryDate BETWEEN :startDate AND :endDate")
    double findTotalSleepHoursByUserIdAndDateRange(@Param("userId") Long userId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Override
    public <S extends SleepHistorical> List<S> findAll(Example<S> example, Sort sort);

    @Override
    public <S extends SleepHistorical> List<S> findAll(Example<S> example);

    @Override
    public SleepHistorical getReferenceById(Long id);

    @Override
    public void deleteAllInBatch();

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> ids);

    @Override
    public void deleteAllInBatch(Iterable<SleepHistorical> entities);

    @Override
    public <S extends SleepHistorical> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends SleepHistorical> S saveAndFlush(S entity);

    @Override
    public void flush();

    @Override
    public List<SleepHistorical> findAllById(Iterable<Long> ids);

    @Override
    public List<SleepHistorical> findAll();

    @Override
    public <S extends SleepHistorical> List<S> saveAll(Iterable<S> entities);

    @Override
    public List<SleepHistorical> findAll(Sort sort);

    @Override
    public void deleteAll();

    @Override
    public void deleteAll(Iterable<? extends SleepHistorical> entities);

    @Override
    public void deleteAllById(Iterable<? extends Long> ids);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void delete(SleepHistorical entity);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteById(Long id);

    @Override
    public long count();

    @Override
    public boolean existsById(Long id);

    @Override
    public Optional<SleepHistorical> findById(Long id);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends SleepHistorical> S save(S entity);

    @Override
    public Page<SleepHistorical> findAll(Pageable pageable);

    @Override
    public <S extends SleepHistorical, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    @Override
    public <S extends SleepHistorical> boolean exists(Example<S> example);

    @Override
    public <S extends SleepHistorical> long count(Example<S> example);

    @Override
    public <S extends SleepHistorical> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    public <S extends SleepHistorical> Optional<S> findOne(Example<S> example);

}
