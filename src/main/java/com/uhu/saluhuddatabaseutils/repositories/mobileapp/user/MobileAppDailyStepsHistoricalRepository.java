package com.uhu.saluhuddatabaseutils.repositories.mobileapp.user;

import com.uhu.saluhuddatabaseutils.models.user.DailyStepsHistorical;
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
public interface MobileAppDailyStepsHistoricalRepository extends JpaRepository<DailyStepsHistorical, Long>
{

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends DailyStepsHistorical> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends DailyStepsHistorical> S saveAndFlush(S entity);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void flush();

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends DailyStepsHistorical> List<S> saveAll(Iterable<S> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends DailyStepsHistorical> S save(S entity);
    
    @Query("SELECT d FROM DailyStepsHistorical d WHERE d.user.username = :username")
    Optional<DailyStepsHistorical> findByUsername(@Param("username") String username);
    
}
