package com.uhu.saluhud.database.utils.repositories.saluhud.admin.user;

import com.uhu.saluhud.database.utils.models.user.SaluhudUserPersonalData;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * This is the saluhud user personal data repository to implement basic CRUD
 * operations
 *
 * @author Juan Alberto Domínguez Vázquez
 */
@Repository
public interface SaluhudAdminUserPersonalDataRepository extends JpaRepository<SaluhudUserPersonalData, Long> {

    List<SaluhudUserPersonalData> findByName(String name);

    List<SaluhudUserPersonalData> findBySurname(String surname);

    Optional<SaluhudUserPersonalData> findByPhoneNumber(int phoneNumber);

    boolean existsByPhoneNumber(int phoneNumber);

    @Query("SELECT p FROM SaluhudUserPersonalData p WHERE p.name = :name AND p.surname = :surname")
    List<SaluhudUserPersonalData> findByNameAndSurname(@Param("name") String name, @Param("surname") String surname);

    @Query("SELECT p FROM SaluhudUserPersonalData p WHERE p.phoneNumber > :phoneNumber")
    List<SaluhudUserPersonalData> findByPhoneNumberGreaterThan(@Param("phoneNumber") int phoneNumber);
}
