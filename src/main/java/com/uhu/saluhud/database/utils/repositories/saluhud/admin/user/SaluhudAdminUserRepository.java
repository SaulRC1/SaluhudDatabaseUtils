package com.uhu.saluhud.database.utils.repositories.saluhud.admin.user;

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
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * This is the saluhud user repository to implement basic CRUD operations
 *
 * @author Juan Alberto Domínguez Vázquez
 */
@Repository
public interface SaluhudAdminUserRepository extends JpaRepository<SaluhudUser, Long>
{
    @Lock(LockModeType.OPTIMISTIC)
    Optional<SaluhudUser> findByEmailIgnoreCase(String email); //Emails are case insensitive
    
    @Lock(LockModeType.OPTIMISTIC)
    Optional<SaluhudUser> findByUsername(String username);

    @Lock(LockModeType.OPTIMISTIC)
    Optional<SaluhudUser> findByEmail(String email);

    @Lock(LockModeType.OPTIMISTIC)
    boolean existsByEmailIgnoreCase(String email);

    @Lock(LockModeType.OPTIMISTIC)
    Optional<SaluhudUser> findByPhoneNumber(String phoneNumber);

    @Lock(LockModeType.OPTIMISTIC)
    boolean existsByPhoneNumber(String phoneNumber);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT u FROM SaluhudUser u WHERE u.fitnessData.id = :fitnessDataId")
    List<SaluhudUser> findByFitnessDataId(@Param("fitnessDataId") long fitnessDataId);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT p FROM SaluhudUser p WHERE p.phoneNumber > :phoneNumber")
    List<SaluhudUser> findByPhoneNumberGreaterThan(@Param("phoneNumber") String phoneNumber);

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
    public void deleteAllInBatch();

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAllByIdInBatch(Iterable<Long> ids);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAllInBatch(Iterable<SaluhudUser> entities);

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
    public void deleteAll();

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAll(Iterable<? extends SaluhudUser> entities);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void deleteAllById(Iterable<? extends Long> ids);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public void delete(SaluhudUser entity);

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
    public Optional<SaluhudUser> findById(Long id);

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends SaluhudUser> S save(S entity);

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
