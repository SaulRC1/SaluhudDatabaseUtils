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
public interface AdministrationPortalWeightHistoricalRepository extends JpaRepository<WeightHistorical, Long>
{

    List<WeightHistorical> findByUser(SaluhudUser user);

    @Query("SELECT w FROM WeightHistorical w WHERE w.user.id = :userId")
    WeightHistorical findByUserId(@Param("userId") long userId);

    @Query("SELECT w FROM WeightHistorical w JOIN w.entries e WHERE e.id = :entryId")
    List<WeightHistorical> findByEntryId(@Param("entryId") long entryId);

    @Override
    public <S extends WeightHistorical> List<S> findAll(Example<S> example, Sort sort);

    @Override
    public <S extends WeightHistorical> List<S> findAll(Example<S> example);

    @Override
    public WeightHistorical getReferenceById(Long id);

    @Override
    public void deleteAllInBatch();

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> ids);

    @Override
    public void deleteAllInBatch(Iterable<WeightHistorical> entities);

    @Override
    public <S extends WeightHistorical> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends WeightHistorical> S saveAndFlush(S entity);

    @Override
    public void flush();

    @Override
    public List<WeightHistorical> findAllById(Iterable<Long> ids);

    @Override
    public List<WeightHistorical> findAll();

    @Override
    public <S extends WeightHistorical> List<S> saveAll(Iterable<S> entities);

    @Override
    public List<WeightHistorical> findAll(Sort sort);

    @Override
    public void deleteAll();

    @Override
    public void deleteAll(Iterable<? extends WeightHistorical> entities);

    @Override
    public void deleteAllById(Iterable<? extends Long> ids);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void delete(WeightHistorical entity);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteById(Long id);

    @Override
    public long count();

    @Override
    public boolean existsById(Long id);

    @Override
    public Optional<WeightHistorical> findById(Long id);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends WeightHistorical> S save(S entity);

    @Override
    public Page<WeightHistorical> findAll(Pageable pageable);

    @Override
    public <S extends WeightHistorical, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    @Override
    public <S extends WeightHistorical> boolean exists(Example<S> example);

    @Override
    public <S extends WeightHistorical> long count(Example<S> example);

    @Override
    public <S extends WeightHistorical> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    public <S extends WeightHistorical> Optional<S> findOne(Example<S> example);

}
