package com.uhu.saluhud.database.utils.repositories.saluhud.admin.user;

import com.uhu.saluhud.database.utils.models.user.SaluhudAdmin;
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
 * This is the saluhud user repository to implement basic CRUD operations
 *
 * @author Juan Alberto Domínguez Vázquez
 */
@Repository
public interface SaluhudAdminUserRepository extends JpaRepository<SaluhudAdmin, Long>
{
  
    @Lock(LockModeType.OPTIMISTIC)
    Optional<SaluhudAdmin> findByName(String name);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends SaluhudAdmin> List<S> findAll(Example<S> example, Sort sort);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends SaluhudAdmin> List<S> findAll(Example<S> example);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public SaluhudAdmin getReferenceById(Long id);
    
    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAllInBatch();

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAllByIdInBatch(Iterable<Long> ids);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAllInBatch(Iterable<SaluhudAdmin> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends SaluhudAdmin> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends SaluhudAdmin> S saveAndFlush(S entity);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void flush();

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public List<SaluhudAdmin> findAllById(Iterable<Long> ids);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public List<SaluhudAdmin> findAll();

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends SaluhudAdmin> List<S> saveAll(Iterable<S> entities);
    
    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public List<SaluhudAdmin> findAll(Sort sort);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAll();

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAll(Iterable<? extends SaluhudAdmin> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAllById(Iterable<? extends Long> ids);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void delete(SaluhudAdmin entity);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteById(Long id);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public long count();

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public boolean existsById(Long id);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public Optional<SaluhudAdmin> findById(Long id);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends SaluhudAdmin> S save(S entity);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public Page<SaluhudAdmin> findAll(Pageable pageable);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends SaluhudAdmin, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends SaluhudAdmin> boolean exists(Example<S> example);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends SaluhudAdmin> long count(Example<S> example);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends SaluhudAdmin> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends SaluhudAdmin> Optional<S> findOne(Example<S> example);
      
}
