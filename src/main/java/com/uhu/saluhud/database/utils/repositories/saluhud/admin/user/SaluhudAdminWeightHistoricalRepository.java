package com.uhu.saluhud.database.utils.repositories.saluhud.admin.user;

import com.uhu.saluhud.database.utils.models.user.SaluhudUser;
import com.uhu.saluhud.database.utils.models.user.WeightHistorical;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * This is the weight historical repository to implement basic CRUD operations
 *
 * @author Juan Alberto Domínguez Vázquez
 */
@Repository
public interface SaluhudAdminWeightHistoricalRepository extends JpaRepository<WeightHistorical, Long> {

    List<WeightHistorical> findByUser(SaluhudUser user);

    @Query("SELECT w FROM WeightHistorical w WHERE w.user.id = :userId")
    WeightHistorical findByUserId(@Param("userId") Long userId);

    @Query("SELECT w FROM WeightHistorical w JOIN w.entries e WHERE e.id = :entryId")
    List<WeightHistorical> findByEntryId(@Param("entryId") Long entryId);
}
