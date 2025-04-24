package com.uhu.saluhuddatabaseutils.repositories.mobileapp.nutrition;

import com.uhu.saluhuddatabaseutils.models.nutrition.Recipe;
import jakarta.persistence.LockModeType;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
@Repository
public interface MobileAppRecipeRepository extends JpaRepository<Recipe, Long>
{

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends Recipe> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends Recipe> S saveAndFlush(S entity);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void flush();

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends Recipe> List<S> saveAll(Iterable<S> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends Recipe> S save(S entity);
    
    @Query("SELECT r FROM Recipe r WHERE r.kilocalories >= :minKcal AND r.kilocalories <= :maxKcal")
    Page<Recipe> findAllWithFilters(@Param("minKcal") int minimumKilocalories, 
            @Param("maxKcal") int maximumKilocalories, Pageable pageable);

    
}
