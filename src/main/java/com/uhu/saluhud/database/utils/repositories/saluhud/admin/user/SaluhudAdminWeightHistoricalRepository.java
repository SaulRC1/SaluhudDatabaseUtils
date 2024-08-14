package com.uhu.saluhud.database.utils.repositories.saluhud.admin.user;

import com.uhu.saluhud.database.utils.models.user.SaluhudUser;
import com.uhu.saluhud.database.utils.models.user.WeightHistorical;
import jakarta.persistence.LockModeType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
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

    @Lock(LockModeType.OPTIMISTIC)
    List<WeightHistorical> findByUser(SaluhudUser user);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT w FROM WeightHistorical w WHERE w.user.id = :userId")
    WeightHistorical findByUserId(@Param("userId") long userId);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT w FROM WeightHistorical w JOIN w.entries e WHERE e.id = :entryId")
    List<WeightHistorical> findByEntryId(@Param("entryId") long entryId);
}
