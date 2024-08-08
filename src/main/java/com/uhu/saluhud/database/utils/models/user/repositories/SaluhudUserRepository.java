package com.uhu.saluhud.database.utils.models.user.repositories;

import com.uhu.saluhud.database.utils.models.user.SaluhudUser;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * This is the saluhud user repository to implement basic CRUD operations
 *
 * @author Juan Alberto Domínguez Vázquez
 */
@Repository
public interface SaluhudUserRepository extends JpaRepository<SaluhudUser, Long> {

    Optional<SaluhudUser> findByUsername(String username);

    Optional<SaluhudUser> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("SELECT u FROM SaluhudUser u WHERE u.fitnessData.id = :fitnessDataId")
    List<SaluhudUser> findByFitnessDataId(@Param("fitnessDataId") Long fitnessDataId);

    @Query("SELECT u FROM SaluhudUser u WHERE u.personalData.id = :personalDataId")
    List<SaluhudUser> findByPersonalDataId(@Param("personalDataId") Long personalDataId);
}
