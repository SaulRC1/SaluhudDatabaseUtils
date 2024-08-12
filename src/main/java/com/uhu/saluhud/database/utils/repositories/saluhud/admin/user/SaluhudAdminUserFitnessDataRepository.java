package com.uhu.saluhud.database.utils.repositories.saluhud.admin.user;

import com.uhu.saluhud.database.utils.models.user.SaluhudUserFitnessData;
import jakarta.persistence.LockModeType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * This is the saluhud user fitness data repository to implement basic CRUD
 * operations
 *
 * @author Juan Alberto Domínguez Vázquez
 */
@Repository
public interface SaluhudAdminUserFitnessDataRepository extends JpaRepository<SaluhudUserFitnessData, Long> {

    @Lock(LockModeType.OPTIMISTIC)
    List<SaluhudUserFitnessData> findByBiologicalSex(String biologicalSex);

    @Lock(LockModeType.OPTIMISTIC)
    List<SaluhudUserFitnessData> findByBodyMassIndex(String bodyMassIndex);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT f FROM SaluhudUserFitnessData f WHERE f.weight BETWEEN :minWeight AND :maxWeight")
    List<SaluhudUserFitnessData> findByWeightRange(@Param("minWeight") double minWeight, @Param("maxWeight") double maxWeight);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT f FROM SaluhudUserFitnessData f WHERE f.height BETWEEN :minHeight AND :maxHeight")
    List<SaluhudUserFitnessData> findByHeightRange(@Param("minHeight") double minHeight, @Param("maxHeight") double maxHeight);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT f FROM SaluhudUserFitnessData f WHERE f.age BETWEEN :minAge AND :maxAge")
    List<SaluhudUserFitnessData> findByAgeRange(@Param("minAge") int minAge, @Param("maxAge") int maxAge);
}
