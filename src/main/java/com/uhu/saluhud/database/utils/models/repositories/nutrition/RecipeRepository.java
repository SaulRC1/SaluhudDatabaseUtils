package com.uhu.saluhud.database.utils.models.repositories.nutrition;

import com.uhu.saluhud.database.utils.models.nutrition.Recipe;
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
}
