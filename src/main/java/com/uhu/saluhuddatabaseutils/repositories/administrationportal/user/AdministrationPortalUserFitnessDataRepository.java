package com.uhu.saluhuddatabaseutils.repositories.administrationportal.user;

import com.uhu.saluhuddatabaseutils.models.user.SaluhudUserFitnessData;
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
 * This is the saluhud user fitness data repository to implement basic CRUD
 * operations
 *
 * @author Juan Alberto Domínguez Vázquez
 */
@Repository
public interface AdministrationPortalUserFitnessDataRepository extends JpaRepository<SaluhudUserFitnessData, Long>
{

    List<SaluhudUserFitnessData> findByBiologicalSex(String biologicalSex);

    List<SaluhudUserFitnessData> findByBodyMassIndex(String bodyMassIndex);

    @Query("SELECT f FROM SaluhudUserFitnessData f WHERE f.weight BETWEEN :minWeight AND :maxWeight")
    List<SaluhudUserFitnessData> findByWeightRange(@Param("minWeight") double minWeight, @Param("maxWeight") double maxWeight);

    @Query("SELECT f FROM SaluhudUserFitnessData f WHERE f.height BETWEEN :minHeight AND :maxHeight")
    List<SaluhudUserFitnessData> findByHeightRange(@Param("minHeight") double minHeight, @Param("maxHeight") double maxHeight);

    @Query("SELECT f FROM SaluhudUserFitnessData f WHERE f.age BETWEEN :minAge AND :maxAge")
    List<SaluhudUserFitnessData> findByAgeRange(@Param("minAge") int minAge, @Param("maxAge") int maxAge);

    @Override
    public <S extends SaluhudUserFitnessData> List<S> findAll(Example<S> example, Sort sort);

    @Override
    public <S extends SaluhudUserFitnessData> List<S> findAll(Example<S> example);

    @Override
    public SaluhudUserFitnessData getReferenceById(Long id);

    @Override
    public void deleteAllInBatch();

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> ids);

    @Override
    public void deleteAllInBatch(Iterable<SaluhudUserFitnessData> entities);

    @Override
    public <S extends SaluhudUserFitnessData> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends SaluhudUserFitnessData> S saveAndFlush(S entity);

    @Override
    public void flush();

    @Override
    public List<SaluhudUserFitnessData> findAllById(Iterable<Long> ids);

    @Override
    public List<SaluhudUserFitnessData> findAll();

    @Override
    public <S extends SaluhudUserFitnessData> List<S> saveAll(Iterable<S> entities);

    @Override
    public List<SaluhudUserFitnessData> findAll(Sort sort);

    @Override
    public void deleteAll();

    @Override
    public void deleteAll(Iterable<? extends SaluhudUserFitnessData> entities);

    @Override
    public void deleteAllById(Iterable<? extends Long> ids);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void delete(SaluhudUserFitnessData entity);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteById(Long id);

    @Override
    public long count();

    @Override
    public boolean existsById(Long id);

    @Override
    public Optional<SaluhudUserFitnessData> findById(Long id);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends SaluhudUserFitnessData> S save(S entity);

    @Override
    public Page<SaluhudUserFitnessData> findAll(Pageable pageable);

    @Override
    public <S extends SaluhudUserFitnessData, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    @Override
    public <S extends SaluhudUserFitnessData> boolean exists(Example<S> example);

    @Override
    public <S extends SaluhudUserFitnessData> long count(Example<S> example);

    @Override
    public <S extends SaluhudUserFitnessData> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    public <S extends SaluhudUserFitnessData> Optional<S> findOne(Example<S> example);

}
