package com.uhu.saluhuddatabaseutils.repositories.administrationportal.user;

import com.uhu.saluhuddatabaseutils.models.user.SaluhudUser;
import com.uhu.saluhuddatabaseutils.models.user.WeightHistorical;
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
 * This is the weight historical repository to implement basic CRUD operations
 *
 * @author Juan Alberto Domínguez Vázquez
 */
@Repository
public interface AdministrationPortalWeightHistoricalRepository extends JpaRepository<WeightHistorical, Long> {

    @Lock(LockModeType.OPTIMISTIC)
    List<WeightHistorical> findByUser(SaluhudUser user);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT w FROM WeightHistorical w WHERE w.user.id = :userId")
    WeightHistorical findByUserId(@Param("userId") long userId);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT w FROM WeightHistorical w JOIN w.entries e WHERE e.id = :entryId")
    List<WeightHistorical> findByEntryId(@Param("entryId") long entryId);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends WeightHistorical> List<S> findAll(Example<S> example, Sort sort);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends WeightHistorical> List<S> findAll(Example<S> example);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public WeightHistorical getReferenceById(Long id);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAllInBatch();

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAllByIdInBatch(Iterable<Long> ids);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAllInBatch(Iterable<WeightHistorical> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends WeightHistorical> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends WeightHistorical> S saveAndFlush(S entity);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void flush();

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public List<WeightHistorical> findAllById(Iterable<Long> ids);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public List<WeightHistorical> findAll();

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends WeightHistorical> List<S> saveAll(Iterable<S> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public List<WeightHistorical> findAll(Sort sort);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAll();

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAll(Iterable<? extends WeightHistorical> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAllById(Iterable<? extends Long> ids);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void delete(WeightHistorical entity);

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
    public Optional<WeightHistorical> findById(Long id);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends WeightHistorical> S save(S entity);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public Page<WeightHistorical> findAll(Pageable pageable);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends WeightHistorical, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends WeightHistorical> boolean exists(Example<S> example);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends WeightHistorical> long count(Example<S> example);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends WeightHistorical> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends WeightHistorical> Optional<S> findOne(Example<S> example);
    
}
