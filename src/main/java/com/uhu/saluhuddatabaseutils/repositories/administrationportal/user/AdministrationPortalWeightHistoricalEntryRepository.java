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
public interface AdministrationPortalWeightHistoricalEntryRepository extends JpaRepository<WeightHistoricalEntry, Long>
{

    List<WeightHistoricalEntry> findByWeightHistoricalId(long weightHistoricalId);

    List<WeightHistoricalEntry> findByEntryDate(LocalDate entryDate);

    @Query("SELECT e FROM WeightHistoricalEntry e WHERE e.weightHistorical.id = :weightHistoricalId AND e.entryDate BETWEEN :startDate AND :endDate")
    List<WeightHistoricalEntry> findByDateRangeAndWeightHistoricalId(
            @Param("weightHistoricalId") long weightHistoricalId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    @Query("SELECT e FROM WeightHistoricalEntry e WHERE e.weightHistorical.id = :weightHistoricalId ORDER BY e.entryDate DESC")
    WeightHistoricalEntry findMostRecentEntryByWeightHistoricalId(@Param("weightHistoricalId") long weightHistoricalId);

    @Override
    public <S extends WeightHistoricalEntry> List<S> findAll(Example<S> example, Sort sort);

    @Override
    public <S extends WeightHistoricalEntry> List<S> findAll(Example<S> example);

    @Override
    public WeightHistoricalEntry getReferenceById(Long id);

    @Override
    public void deleteAllInBatch();

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> ids);

    @Override
    public void deleteAllInBatch(Iterable<WeightHistoricalEntry> entities);

    @Override
    public <S extends WeightHistoricalEntry> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends WeightHistoricalEntry> S saveAndFlush(S entity);

    @Override
    public void flush();

    @Override
    public List<WeightHistoricalEntry> findAllById(Iterable<Long> ids);

    @Override
    public List<WeightHistoricalEntry> findAll();

    @Override
    public <S extends WeightHistoricalEntry> List<S> saveAll(Iterable<S> entities);

    @Override
    public List<WeightHistoricalEntry> findAll(Sort sort);

    @Override
    public void deleteAll();

    @Override
    public void deleteAll(Iterable<? extends WeightHistoricalEntry> entities);

    @Override
    public void deleteAllById(Iterable<? extends Long> ids);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void delete(WeightHistoricalEntry entity);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteById(Long id);

    @Override
    public long count();

    @Override
    public boolean existsById(Long id);

    @Override
    public Optional<WeightHistoricalEntry> findById(Long id);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends WeightHistoricalEntry> S save(S entity);

    @Override
    public Page<WeightHistoricalEntry> findAll(Pageable pageable);

    @Override
    public <S extends WeightHistoricalEntry, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    @Override
    public <S extends WeightHistoricalEntry> boolean exists(Example<S> example);

    @Override
    public <S extends WeightHistoricalEntry> long count(Example<S> example);

    @Override
    public <S extends WeightHistoricalEntry> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    public <S extends WeightHistoricalEntry> Optional<S> findOne(Example<S> example);

}
