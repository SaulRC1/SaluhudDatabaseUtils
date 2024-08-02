package com.uhu.saluhud.database.utils.models.repositories.nutrition;

import com.uhu.saluhud.database.utils.models.nutrition.Ingredient;
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
}
