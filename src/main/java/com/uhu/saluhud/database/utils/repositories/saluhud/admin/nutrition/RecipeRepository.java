package com.uhu.saluhud.database.utils.repositories.saluhud.admin.nutrition;

import com.uhu.saluhud.database.utils.models.nutrition.Ingredient;
import com.uhu.saluhud.database.utils.models.nutrition.Recipe;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * This is the recipe repository to implement basic CRUD operations
 *
 * @author Juan Alberto Domínguez Vázquez
 */
@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    @Query("SELECT i FROM Recipe i WHERE i.id = :id")
    Recipe findOne(@Param("id") long id);

    @Query("SELECT i FROM Recipe i WHERE i.name = :name")
    Recipe findByName(@Param("name") String name);

    @Query("SELECT r FROM Recipe r WHERE :ingredient MEMBER OF r.ingredients")
    List<Recipe> findByIngredient(@Param("ingredient") Ingredient ingredient);

    @Query("SELECT r FROM Recipe r JOIN r.allergenics a WHERE a.id = :allergenId")
    List<Recipe> findByAllergenic(@Param("allergenId") Long allergenId);

    @Query("SELECT r FROM Recipe r WHERE r.ingredientsDescription LIKE %:keyword%")
    List<Recipe> findByIngredientsDescriptionContaining(@Param("keyword") String keyword);

    @Query("SELECT r FROM Recipe r WHERE r.description LIKE %:keyword%")
    List<Recipe> findByDescriptionContaining(@Param("keyword") String keyword);

    @Query("SELECT DISTINCT r FROM Recipe r JOIN r.ingredients i WHERE i.kilocalories <= :maxKilocalories")
    List<Recipe> findByIngredientMaxKilocalories(@Param("maxKilocalories") int maxKilocalories);

    @Query("SELECT DISTINCT r FROM Recipe r JOIN r.ingredients i WHERE i.proteinAmount >= :minProteinAmount")
    List<Recipe> findByIngredientMinProteinAmount(@Param("minProteinAmount") int minProteinAmount);
}
