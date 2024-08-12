package com.uhu.saluhud.database.utils.repositories.saluhud.admin.nutrition;

import com.uhu.saluhud.database.utils.models.nutrition.RecipeElaborationStep;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * This is the recipe elaboration step repository to implement basic CRUD operations
 *
 * @author Juan Alberto Domínguez Vázquez
 */
@Repository
public interface SaluhudAdminRecipeElaborationStepRepository extends JpaRepository<RecipeElaborationStep, Long> {

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT i FROM RecipeElaborationStep i WHERE i.id = :id")
    RecipeElaborationStep findOne(@Param("id") long id);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT i FROM RecipeElaborationStep i WHERE i.stepNumber = :stepNumber")
    RecipeElaborationStep findByStepNumber(@Param("stepNumber") int stepNumber);
}
