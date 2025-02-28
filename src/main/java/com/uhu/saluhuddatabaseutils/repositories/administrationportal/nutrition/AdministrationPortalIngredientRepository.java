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
    
    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT i FROM Ingredient i WHERE i.id = :id")
    Ingredient findOne(@Param("id") long id);
    
    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT i FROM Ingredient i WHERE i.name LIKE :name")
    Ingredient findByName(@Param("name") String name);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT i FROM Ingredient i WHERE i.kilocalories < :maxKilocalories")
    List<Ingredient> findByMaxKilocalories(@Param("maxKilocalories") int maxKilocalories);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT i FROM Ingredient i WHERE i.proteinAmount >= :minProteinAmount")
    List<Ingredient> findByMinProteinAmount(@Param("minProteinAmount") int minProteinAmount);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT i FROM Ingredient i WHERE i.carbohydratesAmount >= :minCarbohydratesAmount")
    List<Ingredient> findByMinCarbohydratesAmount(@Param("minCarbohydratesAmount") int minCarbohydratesAmount);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT i FROM Ingredient i WHERE i.fatAmount >= :minFatAmount")
    List<Ingredient> findByMinFatAmount(@Param("minFatAmount") int minFatAmount);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT i FROM Ingredient i WHERE i.kilocalories BETWEEN :minKilocalories AND :maxKilocalories")
    List<Ingredient> findByKilocaloriesRange(@Param("minKilocalories") int minKilocalories, @Param("maxKilocalories") int maxKilocalories);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT i FROM Ingredient i WHERE i.proteinAmount BETWEEN :minProtein AND :maxProtein")
    List<Ingredient> findByProteinRange(@Param("minProtein") int minProtein, @Param("maxProtein") int maxProtein);
    
    @Lock(LockModeType.OPTIMISTIC)
    Page<Ingredient> findByNameContainingIgnoreCase(String name, Pageable pageable);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends Ingredient> List<S> findAll(Example<S> example, Sort sort);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends Ingredient> List<S> findAll(Example<S> example);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
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
    @Lock(LockModeType.OPTIMISTIC)
    public List<Ingredient> findAllById(Iterable<Long> ids);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public List<Ingredient> findAll();

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends Ingredient> List<S> saveAll(Iterable<S> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
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
    @Lock(LockModeType.OPTIMISTIC)
    public long count();

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public boolean existsById(Long id);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public Optional<Ingredient> findById(Long id);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends Ingredient> S save(S entity);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public Page<Ingredient> findAll(Pageable pageable);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends Ingredient, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends Ingredient> boolean exists(Example<S> example);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends Ingredient> long count(Example<S> example);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends Ingredient> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends Ingredient> Optional<S> findOne(Example<S> example);
    
}
