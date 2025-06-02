package com.uhu.saluhuddatabaseutils.repositories.mobileapp.nutrition;

import com.uhu.saluhuddatabaseutils.models.nutrition.MenuDayRecipe;
import jakarta.persistence.LockModeType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

/**
 *
 * @author SaulRC1
 */
public interface MobileAppMenuDayRecipeRepository extends JpaRepository<MenuDayRecipe, Long>
{

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends MenuDayRecipe> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends MenuDayRecipe> S saveAndFlush(S entity);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends MenuDayRecipe> List<S> saveAll(Iterable<S> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends MenuDayRecipe> S save(S entity);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void flush();
    
}
