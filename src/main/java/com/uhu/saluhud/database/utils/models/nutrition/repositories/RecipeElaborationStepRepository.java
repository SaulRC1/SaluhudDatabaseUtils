package com.uhu.saluhud.database.utils.models.nutrition.repositories;

import com.uhu.saluhud.database.utils.models.nutrition.RecipeElaborationStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * This is the recipe elaboration step repository to implement basic CRUD operations
 *
 * @author Juan Alberto Domínguez Vázquez
 */
@Repository
public interface RecipeElaborationStepRepository extends JpaRepository<RecipeElaborationStep, Long> {

    @Query("SELECT i FROM RecipeElaborationStep i WHERE i.id = :id")
    RecipeElaborationStep findOne(@Param("id") long id);

    @Query("SELECT i FROM RecipeElaborationStep i WHERE i.step_number = :stepNumber")
    RecipeElaborationStep findByStepNumber(@Param("stepNumber") int stepNumber);
}
