package com.uhu.saluhuddatabaseutils.repositories.administrationportal.nutrition;

import com.uhu.saluhuddatabaseutils.models.nutrition.Allergenic;
import jakarta.persistence.LockModeType;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
 * This is the allergenic repository to implement basic CRUD operations
 *
 * @author Juan Alberto Domínguez Vázquez
 */
@Repository
public interface AdministrationPortalAllergenicRepository extends JpaRepository<Allergenic, Long> {

    @Query("SELECT i FROM Allergenic i WHERE i.id = :id")
    Allergenic findOne(@Param("id") long id);

    @Query("SELECT i FROM Allergenic i WHERE i.name = :name")
    Allergenic findByName(@Param("name") String name);
    
    @Query("SELECT a FROM Allergenic a JOIN a.ingredients i WHERE i.id = :ingredientId")
    Set<Allergenic> findByIngredientId(@Param("ingredientId") long ingredientId);

    @Override
    public <S extends Allergenic> List<S> findAll(Example<S> example, Sort sort);

    @Override
    public <S extends Allergenic> List<S> findAll(Example<S> example);

    @Override
    public Allergenic getReferenceById(Long id);

    @Override
    public void deleteAllInBatch();

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> ids);

    @Override
    public void deleteAllInBatch(Iterable<Allergenic> entities);

    @Override
    public <S extends Allergenic> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends Allergenic> S saveAndFlush(S entity);

    @Override
    public void flush();

    @Override
    public List<Allergenic> findAllById(Iterable<Long> ids);

    @Override
    public List<Allergenic> findAll();

    @Override
    public <S extends Allergenic> List<S> saveAll(Iterable<S> entities);

    @Override
    public List<Allergenic> findAll(Sort sort);

    @Override
    public void deleteAll();

    @Override
    public void deleteAll(Iterable<? extends Allergenic> entities);

    @Override
    public void deleteAllById(Iterable<? extends Long> ids);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void delete(Allergenic entity);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteById(Long id);

    @Override
    public long count();

    @Override
    public boolean existsById(Long id);

    @Override
    public Optional<Allergenic> findById(Long id);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends Allergenic> S save(S entity);

    @Override
    public Page<Allergenic> findAll(Pageable pageable);

    @Override
    public <S extends Allergenic, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    @Override
    public <S extends Allergenic> boolean exists(Example<S> example);

    @Override
    public <S extends Allergenic> long count(Example<S> example);

    @Override
    public <S extends Allergenic> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    public <S extends Allergenic> Optional<S> findOne(Example<S> example);
    
}
