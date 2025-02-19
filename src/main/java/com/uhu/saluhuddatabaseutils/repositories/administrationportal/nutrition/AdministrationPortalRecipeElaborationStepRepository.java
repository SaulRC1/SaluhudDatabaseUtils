package com.uhu.saluhuddatabaseutils.repositories.administrationportal.nutrition;

import com.uhu.saluhuddatabaseutils.models.nutrition.RecipeElaborationStep;
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
 * This is the recipe elaboration step repository to implement basic CRUD operations
 *
 * @author Juan Alberto Domínguez Vázquez
 */
@Repository
public interface AdministrationPortalRecipeElaborationStepRepository extends JpaRepository<RecipeElaborationStep, Long> {

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT i FROM RecipeElaborationStep i WHERE i.id = :id")
    RecipeElaborationStep findOne(@Param("id") long id);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT i FROM RecipeElaborationStep i WHERE i.stepNumber = :stepNumber")
    RecipeElaborationStep findByStepNumber(@Param("stepNumber") int stepNumber);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends RecipeElaborationStep> List<S> findAll(Example<S> example, Sort sort);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends RecipeElaborationStep> List<S> findAll(Example<S> example);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public RecipeElaborationStep getReferenceById(Long id);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAllInBatch();

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAllByIdInBatch(Iterable<Long> ids);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAllInBatch(Iterable<RecipeElaborationStep> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends RecipeElaborationStep> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends RecipeElaborationStep> S saveAndFlush(S entity);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void flush();

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public List<RecipeElaborationStep> findAllById(Iterable<Long> ids);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public List<RecipeElaborationStep> findAll();

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends RecipeElaborationStep> List<S> saveAll(Iterable<S> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public List<RecipeElaborationStep> findAll(Sort sort);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAll();

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAll(Iterable<? extends RecipeElaborationStep> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAllById(Iterable<? extends Long> ids);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void delete(RecipeElaborationStep entity);

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
    public Optional<RecipeElaborationStep> findById(Long id);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends RecipeElaborationStep> S save(S entity);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public Page<RecipeElaborationStep> findAll(Pageable pageable);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends RecipeElaborationStep, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends RecipeElaborationStep> boolean exists(Example<S> example);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends RecipeElaborationStep> long count(Example<S> example);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends RecipeElaborationStep> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends RecipeElaborationStep> Optional<S> findOne(Example<S> example);
    
}
