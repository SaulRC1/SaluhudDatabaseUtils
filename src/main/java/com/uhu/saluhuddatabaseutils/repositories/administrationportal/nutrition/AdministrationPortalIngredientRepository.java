package com.uhu.saluhuddatabaseutils.repositories.administrationportal.nutrition;

import com.uhu.saluhuddatabaseutils.models.nutrition.Ingredient;
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
 * This is the ingredient repository to implement basic CRUD operations
 *
 * @author Juan Alberto Domínguez Vázquez
 */
@Repository
public interface AdministrationPortalIngredientRepository extends JpaRepository<Ingredient, Long> {
    
    @Query("SELECT i FROM Ingredient i WHERE i.id = :id")
    Ingredient findOne(@Param("id") long id);
    
    @Query("SELECT i FROM Ingredient i WHERE i.name LIKE :name")
    Page<Ingredient> findByNamePageable(@Param("name") String name, Pageable pageable);
    
    @Query("SELECT i FROM Ingredient i WHERE i.name LIKE :name")
    Ingredient findByName(@Param("name") String name);

    @Query("SELECT i FROM Ingredient i WHERE i.kilocalories < :maxKilocalories")
    Page<Ingredient> findByMaxKilocalories(@Param("maxKilocalories") int maxKilocalories, Pageable pageable);

    @Query("SELECT i FROM Ingredient i WHERE i.proteinAmount >= :minProteinAmount")
    Page<Ingredient> findByMinProteinAmount(@Param("minProteinAmount") int minProteinAmount, Pageable pageable);

    @Query("SELECT i FROM Ingredient i WHERE i.carbohydratesAmount >= :minCarbohydratesAmount")
    Page<Ingredient> findByMinCarbohydratesAmount(@Param("minCarbohydratesAmount") int minCarbohydratesAmount, Pageable pageable);

    @Query("SELECT i FROM Ingredient i WHERE i.fatAmount >= :minFatAmount")
    Page<Ingredient> findByMinFatAmount(@Param("minFatAmount") int minFatAmount, Pageable pageable);

    @Query("SELECT i FROM Ingredient i WHERE i.kilocalories BETWEEN :minKilocalories AND :maxKilocalories")
    Page<Ingredient> findByKilocaloriesRange(@Param("minKilocalories") int minKilocalories, @Param("maxKilocalories") int maxKilocalories, Pageable pageable);

    @Query("SELECT i FROM Ingredient i WHERE i.proteinAmount BETWEEN :minProtein AND :maxProtein")
    Page<Ingredient> findByProteinRange(@Param("minProtein") int minProtein, @Param("maxProtein") int maxProtein, Pageable pageable);
    
    Page<Ingredient> findByNameContainingIgnoreCase(String name, Pageable pageable);
    
    Page<Ingredient> findByNameStartingWithIgnoreCase(String name, Pageable pageable);

    @Override
    public <S extends Ingredient> List<S> findAll(Example<S> example, Sort sort);

    @Override
    public <S extends Ingredient> List<S> findAll(Example<S> example);

    @Override
    public Ingredient getReferenceById(Long id);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAllInBatch();

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAllByIdInBatch(Iterable<Long> ids);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAllInBatch(Iterable<Ingredient> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends Ingredient> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends Ingredient> S saveAndFlush(S entity);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void flush();

    @Override
    public List<Ingredient> findAllById(Iterable<Long> ids);

    @Override
    public List<Ingredient> findAll();

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends Ingredient> List<S> saveAll(Iterable<S> entities);

    @Override
    public List<Ingredient> findAll(Sort sort);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAll();

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAll(Iterable<? extends Ingredient> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAllById(Iterable<? extends Long> ids);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void delete(Ingredient entity);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteById(Long id);

    @Override
    public long count();

    @Override
    public boolean existsById(Long id);

    @Override
    public Optional<Ingredient> findById(Long id);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends Ingredient> S save(S entity);

    @Override
    public Page<Ingredient> findAll(Pageable pageable);

    @Override
    public <S extends Ingredient, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    @Override
    public <S extends Ingredient> boolean exists(Example<S> example);

    @Override
    public <S extends Ingredient> long count(Example<S> example);

    @Override
    public <S extends Ingredient> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    public <S extends Ingredient> Optional<S> findOne(Example<S> example);
    
}
