package com.uhu.saluhud.database.utils.repositories.saluhud.admin.nutrition;

import com.uhu.saluhud.database.utils.models.nutrition.Allergenic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * This is the allergenic repository to implement basic CRUD operations
 *
 * @author Juan Alberto Domínguez Vázquez
 */
@Repository
public interface SaluhudAdminAllergenicRepository extends JpaRepository<Allergenic, Long> {

    @Query("SELECT i FROM Allergenic i WHERE i.id = :id")
    Allergenic findOne(@Param("id") long id);

    @Query("SELECT i FROM Allergenic i WHERE i.name = :name")
    Allergenic findByName(@Param("name") String name);
}
