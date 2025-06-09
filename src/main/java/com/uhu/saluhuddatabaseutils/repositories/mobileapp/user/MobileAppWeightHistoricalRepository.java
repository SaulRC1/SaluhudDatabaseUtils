package com.uhu.saluhuddatabaseutils.repositories.mobileapp.user;

import com.uhu.saluhuddatabaseutils.models.user.WeightHistorical;
import jakarta.persistence.LockModeType;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Saúl Rodríguez Naranjo
 */
public interface MobileAppWeightHistoricalRepository extends JpaRepository<WeightHistorical, Long>
{

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends WeightHistorical> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends WeightHistorical> S saveAndFlush(S entity);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void flush();

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends WeightHistorical> List<S> saveAll(Iterable<S> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends WeightHistorical> S save(S entity);

    @Query("SELECT w FROM WeightHistorical w WHERE w.user.username = :username")
    Optional<WeightHistorical> findByUsername(@Param("username") String username);
}
