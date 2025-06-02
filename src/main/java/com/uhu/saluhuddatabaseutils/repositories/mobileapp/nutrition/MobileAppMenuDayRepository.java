package com.uhu.saluhuddatabaseutils.repositories.mobileapp.nutrition;

import com.uhu.saluhuddatabaseutils.models.nutrition.MenuDay;
import jakarta.persistence.LockModeType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

/**
 *
 * @author SaulRC1
 */
public interface MobileAppMenuDayRepository extends JpaRepository<MenuDay, Long>
{

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends MenuDay> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends MenuDay> S saveAndFlush(S entity);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends MenuDay> List<S> saveAll(Iterable<S> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends MenuDay> S save(S entity);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void flush();
    
}
