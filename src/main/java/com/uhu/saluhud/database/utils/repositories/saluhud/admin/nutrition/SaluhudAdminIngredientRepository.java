package com.uhu.saluhud.database.utils.repositories.saluhud.admin.nutrition;

import com.uhu.saluhud.database.utils.models.nutrition.Ingredient;
import jakarta.persistence.LockModeType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * This is the ingredient repository to implement basic CRUD operations
 *
 * @author Juan Alberto Domínguez Vázquez
 */
@Repository
public interface SaluhudAdminIngredientRepository extends JpaRepository<Ingredient, Long> {
    
    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT i FROM Ingredient i WHERE i.id = :id")
    Ingredient findOne(@Param("id") long id);
    
    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT i FROM Ingredient i WHERE i.name = :name")
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
}
