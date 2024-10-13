package com.uhu.saluhud.database.utils.repositories.saluhud.admin.nutrition;

import com.uhu.saluhud.database.utils.models.nutrition.Ingredient;
import com.uhu.saluhud.database.utils.models.nutrition.Recipe;
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
public interface SaluhudAdminRecipeRepository extends JpaRepository<Recipe, Long> {

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT i FROM Recipe i WHERE i.id = :id")
    Recipe findOne(@Param("id") long id);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT i FROM Recipe i WHERE i.name = :name")
    List<Recipe> findByName(@Param("name") String name);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT r FROM Recipe r WHERE :ingredient MEMBER OF r.ingredients")
    List<Recipe> findByIngredient(@Param("ingredient") Ingredient ingredient);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT r FROM Recipe r JOIN r.allergenics a WHERE a.id = :allergenId")
    List<Recipe> findByAllergenic(@Param("allergenId") Long allergenId);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT r FROM Recipe r WHERE r.ingredientsDescription LIKE %:keyword%")
    List<Recipe> findByIngredientsDescriptionContaining(@Param("keyword") String keyword);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT r FROM Recipe r WHERE r.description LIKE %:keyword%")
    List<Recipe> findByDescriptionContaining(@Param("keyword") String keyword);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT DISTINCT r FROM Recipe r JOIN r.ingredients i WHERE i.kilocalories <= :maxKilocalories")
    List<Recipe> findByIngredientMaxKilocalories(@Param("maxKilocalories") int maxKilocalories);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT DISTINCT r FROM Recipe r JOIN r.ingredients i WHERE i.proteinAmount >= :minProteinAmount")
    List<Recipe> findByIngredientMinProteinAmount(@Param("minProteinAmount") int minProteinAmount);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends Recipe> List<S> findAll(Example<S> example, Sort sort);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends Recipe> List<S> findAll(Example<S> example);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public Recipe getReferenceById(Long id);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAllInBatch();

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAllByIdInBatch(Iterable<Long> ids);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAllInBatch(Iterable<Recipe> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends Recipe> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends Recipe> S saveAndFlush(S entity);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void flush();

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public List<Recipe> findAllById(Iterable<Long> ids);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public List<Recipe> findAll();

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends Recipe> List<S> saveAll(Iterable<S> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public List<Recipe> findAll(Sort sort);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAll();

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAll(Iterable<? extends Recipe> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAllById(Iterable<? extends Long> ids);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void delete(Recipe entity);

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
    public Optional<Recipe> findById(Long id);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends Recipe> S save(S entity);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public Page<Recipe> findAll(Pageable pageable);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends Recipe, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends Recipe> boolean exists(Example<S> example);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends Recipe> long count(Example<S> example);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends Recipe> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends Recipe> Optional<S> findOne(Example<S> example);
    
}
