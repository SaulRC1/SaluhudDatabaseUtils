package com.uhu.saluhud.database.utils.repositories.saluhud.user.user;

import com.uhu.saluhud.database.utils.models.user.SaluhudUser;
import jakarta.persistence.LockModeType;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

/**
 * {@link JpaRepository} for operations against the database regarding {@link SaluhudUser}
 * and with database user "SaluhudUser" (mobile app) permissions.
 * 
 * @author SaulRC1
 */
@Repository
public interface SaluhudUserUserRepository extends JpaRepository<SaluhudUser, Long>
{
    @Lock(LockModeType.OPTIMISTIC)
    boolean existsByEmailIgnoreCase(String email); //Emails are case insensitive
    
    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends SaluhudUser> S save(S entity);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends SaluhudUser> List<S> findAll(Example<S> example, Sort sort);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends SaluhudUser> List<S> findAll(Example<S> example);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public SaluhudUser getReferenceById(Long id);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends SaluhudUser> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends SaluhudUser> S saveAndFlush(S entity);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void flush();

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public List<SaluhudUser> findAllById(Iterable<Long> ids);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public List<SaluhudUser> findAll();

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends SaluhudUser> List<S> saveAll(Iterable<S> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public List<SaluhudUser> findAll(Sort sort);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public long count();

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public boolean existsById(Long id);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public Optional<SaluhudUser> findById(Long id);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public Page<SaluhudUser> findAll(Pageable pageable);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends SaluhudUser, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends SaluhudUser> boolean exists(Example<S> example);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends SaluhudUser> long count(Example<S> example);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends SaluhudUser> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends SaluhudUser> Optional<S> findOne(Example<S> example);
    
}
