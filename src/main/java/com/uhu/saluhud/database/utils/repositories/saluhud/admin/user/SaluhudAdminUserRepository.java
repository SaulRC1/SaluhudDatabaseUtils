package com.uhu.saluhud.database.utils.repositories.saluhud.admin.user;

import com.uhu.saluhud.database.utils.models.user.SaluhudUser;
import jakarta.persistence.LockModeType;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * This is the saluhud user repository to implement basic CRUD operations
 *
 * @author Juan Alberto Domínguez Vázquez
 */
@Repository
public interface SaluhudAdminUserRepository extends JpaRepository<SaluhudUser, Long> {

    @Lock(LockModeType.OPTIMISTIC)
    Optional<SaluhudUser> findByUsername(String username);

    @Lock(LockModeType.OPTIMISTIC)
    Optional<SaluhudUser> findByEmail(String email);

    @Lock(LockModeType.OPTIMISTIC)
    boolean existsByEmail(String email);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT u FROM SaluhudUser u WHERE u.fitnessData.id = :fitnessDataId")
    List<SaluhudUser> findByFitnessDataId(@Param("fitnessDataId") Long fitnessDataId);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT u FROM SaluhudUser u WHERE u.personalData.id = :personalDataId")
    List<SaluhudUser> findByPersonalDataId(@Param("personalDataId") Long personalDataId);
}
