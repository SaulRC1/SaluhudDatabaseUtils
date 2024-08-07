package com.uhu.saluhud.database.utils.models.repositories.nutrition;

import com.uhu.saluhud.database.utils.models.nutrition.Ingredient;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * This is the ingredient repository to implement basic CRUD operations
 *
 * @author Juan Alberto Domínguez Vázquez
 */
@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    
    @Query("SELECT i FROM Ingredient i WHERE i.id = :id")
    Ingredient findOne(@Param("id") long id);
    
    @Query("SELECT i FROM Ingredient i WHERE i.name = :name")
    Ingredient findByName(@Param("name") String name);

    @Query("SELECT i FROM Ingredient i WHERE i.kilocalories < :maxKilocalories")
    List<Ingredient> findByMaxKilocalories(@Param("maxKilocalories") int maxKilocalories);

    @Query("SELECT i FROM Ingredient i WHERE i.proteinAmount >= :minProteinAmount")
    List<Ingredient> findByMinProteinAmount(@Param("minProteinAmount") int minProteinAmount);

    @Query("SELECT i FROM Ingredient i WHERE i.carbohydratesAmount >= :minCarbohydratesAmount")
    List<Ingredient> findByMinCarbohydratesAmount(@Param("minCarbohydratesAmount") int minCarbohydratesAmount);

    @Query("SELECT i FROM Ingredient i WHERE i.fatAmount >= :minFatAmount")
    List<Ingredient> findByMinFatAmount(@Param("minFatAmount") int minFatAmount);

    @Query("SELECT i FROM Ingredient i WHERE i.kilocalories BETWEEN :minKilocalories AND :maxKilocalories")
    List<Ingredient> findByKilocaloriesRange(@Param("minKilocalories") int minKilocalories, @Param("maxKilocalories") int maxKilocalories);

    @Query("SELECT i FROM Ingredient i WHERE i.proteinAmount BETWEEN :minProtein AND :maxProtein")
    List<Ingredient> findByProteinRange(@Param("minProtein") int minProtein, @Param("maxProtein") int maxProtein);
}
