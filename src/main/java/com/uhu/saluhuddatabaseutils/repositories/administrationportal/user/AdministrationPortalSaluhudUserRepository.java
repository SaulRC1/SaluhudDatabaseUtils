package com.uhu.saluhuddatabaseutils.repositories.administrationportal.user;

import com.uhu.saluhuddatabaseutils.models.user.SaluhudUser;
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
 * This is the Saluhud administrator repository to implement basic CRUD
 * operations
 *
 * @author Juan Alberto Domínguez Vázquez
 */
@Repository
public interface AdministrationPortalSaluhudUserRepository extends JpaRepository<SaluhudUser, Long>
{

    Optional<SaluhudUser> findByName(String name);

    @Override
    public <S extends SaluhudUser> List<S> findAll(Example<S> example, Sort sort);

    @Override
    public <S extends SaluhudUser> List<S> findAll(Example<S> example);

    @Override
    public SaluhudUser getReferenceById(Long id);

    @Override
    public void deleteAllInBatch();

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> ids);

    @Override
    public void deleteAllInBatch(Iterable<SaluhudUser> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends SaluhudUser> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends SaluhudUser> S saveAndFlush(S entity);

    @Override
    public void flush();

    @Override
    public List<SaluhudUser> findAllById(Iterable<Long> ids);

    @Override
    public List<SaluhudUser> findAll();

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends SaluhudUser> List<S> saveAll(Iterable<S> entities);

    @Override
    public List<SaluhudUser> findAll(Sort sort);

    @Override
    public void deleteAll();

    @Override
    public void deleteAll(Iterable<? extends SaluhudUser> entities);

    @Override
    public void deleteAllById(Iterable<? extends Long> ids);

    @Override
    public void delete(SaluhudUser entity);

    @Override
    public void deleteById(Long id);

    @Override
    public long count();

    @Override
    public boolean existsById(Long id);

    @Override
    public Optional<SaluhudUser> findById(Long id);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends SaluhudUser> S save(S entity);

    @Override
    public Page<SaluhudUser> findAll(Pageable pageable);

    @Override
    public <S extends SaluhudUser, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    @Override
    public <S extends SaluhudUser> boolean exists(Example<S> example);

    @Override
    public <S extends SaluhudUser> long count(Example<S> example);

    @Override
    public <S extends SaluhudUser> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    public <S extends SaluhudUser> Optional<S> findOne(Example<S> example);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

}
