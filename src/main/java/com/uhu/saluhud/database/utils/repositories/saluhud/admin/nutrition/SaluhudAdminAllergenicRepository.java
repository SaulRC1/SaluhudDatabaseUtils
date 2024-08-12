package com.uhu.saluhud.database.utils.repositories.saluhud.admin.nutrition;

import com.uhu.saluhud.database.utils.models.nutrition.Allergenic;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
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

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT i FROM Allergenic i WHERE i.id = :id")
    Allergenic findOne(@Param("id") long id);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT i FROM Allergenic i WHERE i.name = :name")
    Allergenic findByName(@Param("name") String name);
}
