package com.uhu.saluhud.database.utils.repositories.saluhud.admin.user;

import com.uhu.saluhud.database.utils.models.user.DailyStepsHistorical;
import jakarta.persistence.LockModeType;
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
 * This is the daily step historical repository to implement basic CRUD
 * operations
 *
 * @author Juan Alberto Domínguez Vázquez
 */
@Repository
public interface SaluhudAdminDailyStepsHistoricalRepository extends JpaRepository<DailyStepsHistorical, Long> {

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT d FROM DailyStepsHistorical d WHERE d.user.id = :userId")
    List<DailyStepsHistorical> findAllByUserId(@Param("userId") long userId);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT d FROM DailyStepsHistorical d WHERE SIZE(d.entries) >= :minEntries")
    List<DailyStepsHistorical> findAllWithMinEntries(@Param("minEntries") int minEntries);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT d FROM DailyStepsHistorical d JOIN FETCH d.entries WHERE d.user.id = :userId")
    List<DailyStepsHistorical> findAllWithEntriesByUserId(@Param("userId") long userId);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT COUNT(d) FROM DailyStepsHistorical d WHERE d.user.id = :userId")
    int countByUserId(@Param("userId") long userId);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends DailyStepsHistorical> List<S> findAll(Example<S> example, Sort sort);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends DailyStepsHistorical> List<S> findAll(Example<S> example);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public DailyStepsHistorical getReferenceById(Long id);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAllInBatch();

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAllByIdInBatch(Iterable<Long> ids);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAllInBatch(Iterable<DailyStepsHistorical> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends DailyStepsHistorical> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends DailyStepsHistorical> S saveAndFlush(S entity);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void flush();

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public List<DailyStepsHistorical> findAllById(Iterable<Long> ids);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public List<DailyStepsHistorical> findAll();

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends DailyStepsHistorical> List<S> saveAll(Iterable<S> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public List<DailyStepsHistorical> findAll(Sort sort);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAll();

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAll(Iterable<? extends DailyStepsHistorical> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAllById(Iterable<? extends Long> ids);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void delete(DailyStepsHistorical entity);

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
    public Optional<DailyStepsHistorical> findById(Long id);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends DailyStepsHistorical> S save(S entity);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public Page<DailyStepsHistorical> findAll(Pageable pageable);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends DailyStepsHistorical, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends DailyStepsHistorical> boolean exists(Example<S> example);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends DailyStepsHistorical> long count(Example<S> example);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends DailyStepsHistorical> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends DailyStepsHistorical> Optional<S> findOne(Example<S> example);
    
}
