package com.uhu.saluhuddatabaseutils.repositories.administrationportal.user;

import com.uhu.saluhuddatabaseutils.models.user.DailyStepsHistorical;
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
public interface AdministrationPortalDailyStepsHistoricalRepository extends JpaRepository<DailyStepsHistorical, Long>
{
    @Query("SELECT s FROM DailyStepsHistorical s WHERE s.user.id = :userId")
    DailyStepsHistorical findByUserId(@Param("userId") long userId);
    
    @Query("SELECT d FROM DailyStepsHistorical d WHERE d.user.id = :userId")
    List<DailyStepsHistorical> findAllByUserId(@Param("userId") long userId);

    @Query("SELECT d FROM DailyStepsHistorical d WHERE SIZE(d.entries) >= :minEntries")
    List<DailyStepsHistorical> findAllWithMinEntries(@Param("minEntries") int minEntries);

    @Query("SELECT d FROM DailyStepsHistorical d JOIN FETCH d.entries WHERE d.user.id = :userId")
    List<DailyStepsHistorical> findAllWithEntriesByUserId(@Param("userId") long userId);

    @Query("SELECT COUNT(d) FROM DailyStepsHistorical d WHERE d.user.id = :userId")
    int countByUserId(@Param("userId") long userId);

    @Override
    public <S extends DailyStepsHistorical> List<S> findAll(Example<S> example, Sort sort);

    @Override
    public <S extends DailyStepsHistorical> List<S> findAll(Example<S> example);

    @Override
    public DailyStepsHistorical getReferenceById(Long id);

    @Override
    public void deleteAllInBatch();

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> ids);

    @Override
    public void deleteAllInBatch(Iterable<DailyStepsHistorical> entities);

    @Override
    public <S extends DailyStepsHistorical> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends DailyStepsHistorical> S saveAndFlush(S entity);

    @Override
    public void flush();

    @Override
    public List<DailyStepsHistorical> findAllById(Iterable<Long> ids);

    @Override
    public List<DailyStepsHistorical> findAll();

    @Override
    public <S extends DailyStepsHistorical> List<S> saveAll(Iterable<S> entities);

    @Override
    public List<DailyStepsHistorical> findAll(Sort sort);

    @Override
    public void deleteAll();

    @Override
    public void deleteAll(Iterable<? extends DailyStepsHistorical> entities);

    @Override
    public void deleteAllById(Iterable<? extends Long> ids);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void delete(DailyStepsHistorical entity);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteById(Long id);

    @Override
    public long count();

    @Override
    public boolean existsById(Long id);

    @Override
    public Optional<DailyStepsHistorical> findById(Long id);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends DailyStepsHistorical> S save(S entity);

    @Override
    public Page<DailyStepsHistorical> findAll(Pageable pageable);

    @Override
    public <S extends DailyStepsHistorical, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    @Override
    public <S extends DailyStepsHistorical> boolean exists(Example<S> example);

    @Override
    public <S extends DailyStepsHistorical> long count(Example<S> example);

    @Override
    public <S extends DailyStepsHistorical> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    public <S extends DailyStepsHistorical> Optional<S> findOne(Example<S> example);

}
