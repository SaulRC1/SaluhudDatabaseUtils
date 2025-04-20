package com.uhu.saluhuddatabaseutils.repositories.administrationportal.nutrition;

import com.uhu.saluhuddatabaseutils.models.nutrition.Recipe;
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
 * This is the recipe repository to implement basic CRUD operations
 *
 * @author Juan Alberto Domínguez Vázquez
 */
@Repository
public interface AdministrationPortalRecipeRepository extends JpaRepository<Recipe, Long>
{

    @Query("SELECT i FROM Recipe i WHERE i.id = :id")
    Recipe findOne(@Param("id") long id);

    @Query("SELECT i FROM Recipe i WHERE i.name = :name")
    List<Recipe> findByName(@Param("name") String name);

    @Query("SELECT r FROM Recipe r JOIN r.allergenics a WHERE a.id = :allergenId")
    List<Recipe> findByAllergenic(@Param("allergenId") Long allergenId);

    @Query("SELECT r FROM Recipe r WHERE r.description LIKE %:keyword%")
    List<Recipe> findByDescriptionContaining(@Param("keyword") String keyword);

    // Buscar por nombre
    Page<Recipe> findByNameContainingIgnoreCase(String name, Pageable pageable);

    // Buscar por calorías máximas
    Page<Recipe> findByKilocaloriesLessThanEqual(int maxKilocalories, Pageable pageable);

    // Buscar recetas que contengan un ingrediente específico
    @Query("""
        SELECT r FROM Recipe r 
        JOIN r.recipeIngredients ri 
        WHERE ri.ingredient.id = :ingredientId
    """)
    Page<Recipe> findByIngredientId(@Param("ingredientId") Long ingredientId, Pageable pageable);

    // Buscar recetas que NO contengan un alérgeno específico
    @Query("""
    SELECT r FROM Recipe r 
    WHERE NOT EXISTS (
        SELECT 1 FROM r.allergenics a 
        WHERE a.id = :allergenicId
        )
    """)
    Page<Recipe> findByAllergenicExclusion(@Param("allergenicId") Long allergenicId, Pageable pageable);

    // Buscar recetas que contengan un alérgeno específico
    @Query("""
        SELECT r FROM Recipe r 
        JOIN r.allergenics a
        WHERE a.id = :allergenicId
    """)
    Page<Recipe> findByAllergenic(@Param("allergenicId") Long allergenicId, Pageable pageable);

    @Override
    public <S extends Recipe> List<S> findAll(Example<S> example, Sort sort);

    @Override
    public <S extends Recipe> List<S> findAll(Example<S> example);

    @Override
    public Recipe getReferenceById(Long id);

    @Override
    public void deleteAllInBatch();

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> ids);

    @Override
    public void deleteAllInBatch(Iterable<Recipe> entities);

    @Override
    public <S extends Recipe> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends Recipe> S saveAndFlush(S entity);

    @Override
    public void flush();

    @Override
    public List<Recipe> findAllById(Iterable<Long> ids);

    @Override
    public List<Recipe> findAll();

    @Override
    public <S extends Recipe> List<S> saveAll(Iterable<S> entities);

    @Override
    public List<Recipe> findAll(Sort sort);

    @Override
    public void deleteAll();

    @Override
    public void deleteAll(Iterable<? extends Recipe> entities);

    @Override
    public void deleteAllById(Iterable<? extends Long> ids);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void delete(Recipe entity);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteById(Long id);

    @Override
    public long count();

    @Override
    public boolean existsById(Long id);

    @Override
    public Optional<Recipe> findById(Long id);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends Recipe> S save(S entity);

    @Override
    public Page<Recipe> findAll(Pageable pageable);

    @Override
    public <S extends Recipe, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    @Override
    public <S extends Recipe> boolean exists(Example<S> example);

    @Override
    public <S extends Recipe> long count(Example<S> example);

    @Override
    public <S extends Recipe> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    public <S extends Recipe> Optional<S> findOne(Example<S> example);

}
