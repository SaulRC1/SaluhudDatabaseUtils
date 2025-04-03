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

    @Query("SELECT i FROM RecipeElaborationStep i WHERE i.id = :id")
    RecipeElaborationStep findOne(@Param("id") long id);

    @Query("SELECT i FROM RecipeElaborationStep i WHERE i.stepNumber = :stepNumber")
    RecipeElaborationStep findByStepNumber(@Param("stepNumber") int stepNumber);

    @Override
    public <S extends RecipeElaborationStep> List<S> findAll(Example<S> example, Sort sort);

    @Override
    public <S extends RecipeElaborationStep> List<S> findAll(Example<S> example);

    @Override
    public RecipeElaborationStep getReferenceById(Long id);

    @Override
    public void deleteAllInBatch();

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> ids);

    @Override
    public void deleteAllInBatch(Iterable<RecipeElaborationStep> entities);

    @Override
    public <S extends RecipeElaborationStep> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends RecipeElaborationStep> S saveAndFlush(S entity);

    @Override
    public void flush();

    @Override
    public List<RecipeElaborationStep> findAllById(Iterable<Long> ids);

    @Override
    public List<RecipeElaborationStep> findAll();

    @Override
    public <S extends RecipeElaborationStep> List<S> saveAll(Iterable<S> entities);

    @Override
    public List<RecipeElaborationStep> findAll(Sort sort);

    @Override
    public void deleteAll();

    @Override
    public void deleteAll(Iterable<? extends RecipeElaborationStep> entities);

    @Override
    public void deleteAllById(Iterable<? extends Long> ids);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void delete(RecipeElaborationStep entity);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteById(Long id);

    @Override
    public long count();

    @Override
    public boolean existsById(Long id);

    @Override
    public Optional<RecipeElaborationStep> findById(Long id);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends RecipeElaborationStep> S save(S entity);

    @Override
    public Page<RecipeElaborationStep> findAll(Pageable pageable);

    @Override
    public <S extends RecipeElaborationStep, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    @Override
    public <S extends RecipeElaborationStep> boolean exists(Example<S> example);

    @Override
    public <S extends RecipeElaborationStep> long count(Example<S> example);

    @Override
    public <S extends RecipeElaborationStep> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    public <S extends RecipeElaborationStep> Optional<S> findOne(Example<S> example);
    
}
