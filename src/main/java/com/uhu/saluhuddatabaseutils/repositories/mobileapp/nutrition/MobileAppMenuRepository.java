/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.uhu.saluhuddatabaseutils.repositories.mobileapp.nutrition;

import com.uhu.saluhuddatabaseutils.models.nutrition.Menu;
import jakarta.persistence.LockModeType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author SaulRC1
 */
public interface MobileAppMenuRepository extends JpaRepository<Menu, Long>
{

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends Menu> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends Menu> S saveAndFlush(S entity);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void flush();

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends Menu> List<S> saveAll(Iterable<S> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends Menu> S save(S entity);
    
    @Query("""
        SELECT m FROM Menu m
        JOIN SaluhudUser u ON u.id = m.userId
        WHERE u.username = :username
    """)
    public List<Menu> findByUsername(@Param("username") String username);
    
    public boolean existsByName(String name);
    
    @Query("""
        SELECT COUNT(m) > 0 FROM Menu m
        JOIN SaluhudUser u ON u.id = m.userId
        WHERE u.username = :username
        AND m.name = :menuName
    """)
    public boolean existsByNameForUsername(@Param("username") String username, @Param("menuName") String menuName);
}
