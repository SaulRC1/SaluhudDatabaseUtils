package com.uhu.saluhud.database.utils.repositories.saluhud.admin.user;

import com.uhu.saluhud.database.utils.models.user.DailyStepsHistoricalEntry;
import com.uhu.saluhud.database.utils.models.user.HistoricalEvaluation;
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
 * This is the daily step historical entry repository to implement basic CRUD
 * operations
 *
 * @author Juan Alberto Domínguez Vázquez
 */
@Repository
public interface SaluhudAdminDailyStepsHistoricalEntryRepository extends JpaRepository<DailyStepsHistoricalEntry, Long> {

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT d FROM DailyStepsHistoricalEntry d WHERE d.entryDate BETWEEN :startDate AND :endDate")
    List<DailyStepsHistoricalEntry> findEntriesByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT d FROM DailyStepsHistoricalEntry d WHERE d.stepEvaluation = :evaluation")
    List<DailyStepsHistoricalEntry> findEntriesByStepEvaluation(@Param("evaluation") HistoricalEvaluation evaluation);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT SUM(d.doneSteps) FROM DailyStepsHistoricalEntry d WHERE d.entryDate BETWEEN :startDate AND :endDate")
    int findTotalStepsInDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT SUM(d.kiloCaloriesBurned) FROM DailyStepsHistoricalEntry d WHERE d.entryDate BETWEEN :startDate AND :endDate")
    double findTotalCaloriesBurnedInDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT d FROM DailyStepsHistoricalEntry d WHERE d.dailyStepsHistorical.id = :historicalId")
    List<DailyStepsHistoricalEntry> findEntriesByDailyStepsHistoricalId(@Param("historicalId") Long historicalId);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT d FROM DailyStepsHistoricalEntry d WHERE d.doneSteps > :steps")
    List<DailyStepsHistoricalEntry> findEntriesWithStepsGreaterThan(@Param("steps") int steps);
    
    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT e FROM DailyStepsHistoricalEntry e WHERE e.dailyStepsHistorical.user.username = :username")
    List<DailyStepsHistoricalEntry> findAllByUserUsername(@Param("username") String username);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends DailyStepsHistoricalEntry> List<S> findAll(Example<S> example, Sort sort);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends DailyStepsHistoricalEntry> List<S> findAll(Example<S> example);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public DailyStepsHistoricalEntry getReferenceById(Long id);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAllInBatch();

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAllByIdInBatch(Iterable<Long> ids);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAllInBatch(Iterable<DailyStepsHistoricalEntry> entities);

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
    public List<DailyStepsHistoricalEntry> findAllById(Iterable<Long> ids);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public List<DailyStepsHistoricalEntry> findAll();

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends DailyStepsHistoricalEntry> List<S> saveAll(Iterable<S> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public List<DailyStepsHistoricalEntry> findAll(Sort sort);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAll();

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAll(Iterable<? extends DailyStepsHistoricalEntry> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAllById(Iterable<? extends Long> ids);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void delete(DailyStepsHistoricalEntry entity);

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
    public Optional<DailyStepsHistoricalEntry> findById(Long id);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends DailyStepsHistoricalEntry> S save(S entity);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public Page<DailyStepsHistoricalEntry> findAll(Pageable pageable);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends DailyStepsHistoricalEntry, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends DailyStepsHistoricalEntry> boolean exists(Example<S> example);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends DailyStepsHistoricalEntry> long count(Example<S> example);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends DailyStepsHistoricalEntry> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends DailyStepsHistoricalEntry> Optional<S> findOne(Example<S> example);
    
}
