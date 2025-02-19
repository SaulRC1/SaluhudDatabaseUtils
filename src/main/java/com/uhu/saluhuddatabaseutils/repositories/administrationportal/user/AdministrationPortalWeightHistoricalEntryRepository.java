package com.uhu.saluhuddatabaseutils.repositories.administrationportal.user;

import com.uhu.saluhuddatabaseutils.models.user.WeightHistoricalEntry;
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
 * This is the weight historical entry repository to implement basic CRUD
 * operations
 *
 * @author Juan Alberto Domínguez Vázquez
 */
@Repository
public interface AdministrationPortalWeightHistoricalEntryRepository extends JpaRepository<WeightHistoricalEntry, Long> {

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

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends WeightHistoricalEntry> List<S> findAll(Example<S> example, Sort sort);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends WeightHistoricalEntry> List<S> findAll(Example<S> example);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public WeightHistoricalEntry getReferenceById(Long id);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAllInBatch();

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAllByIdInBatch(Iterable<Long> ids);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAllInBatch(Iterable<WeightHistoricalEntry> entities);

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
    public List<WeightHistoricalEntry> findAllById(Iterable<Long> ids);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public List<WeightHistoricalEntry> findAll();

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends WeightHistoricalEntry> List<S> saveAll(Iterable<S> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public List<WeightHistoricalEntry> findAll(Sort sort);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAll();

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAll(Iterable<? extends WeightHistoricalEntry> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAllById(Iterable<? extends Long> ids);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void delete(WeightHistoricalEntry entity);

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
    public Optional<WeightHistoricalEntry> findById(Long id);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends WeightHistoricalEntry> S save(S entity);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public Page<WeightHistoricalEntry> findAll(Pageable pageable);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends WeightHistoricalEntry, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends WeightHistoricalEntry> boolean exists(Example<S> example);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends WeightHistoricalEntry> long count(Example<S> example);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends WeightHistoricalEntry> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends WeightHistoricalEntry> Optional<S> findOne(Example<S> example);
    
}
