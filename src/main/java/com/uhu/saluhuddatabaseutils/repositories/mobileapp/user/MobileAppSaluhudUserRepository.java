package com.uhu.saluhuddatabaseutils.repositories.mobileapp.user;

import com.uhu.saluhuddatabaseutils.models.user.SaluhudUser;
import com.uhu.saluhuddatabaseutils.models.user.SaluhudUserFitnessData;
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
 * {@link JpaRepository} for operations against the database regarding {@link SaluhudUser}
 * and with database user "saluhud_mobile_app" permissions.
 * 
 * @author SaulRC1
 */
@Repository
public interface MobileAppSaluhudUserRepository extends JpaRepository<SaluhudUser, Long>
{
    public boolean existsByEmailIgnoreCase(String email); //Emails are case insensitive
    
    public boolean existsByUsername(String username);
    
    public boolean existsByPhoneNumber(String phoneNumber);
    
    public Optional<SaluhudUser> findByUsername(String username);
    
    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends SaluhudUser> S save(S entity);

    @Override
    public <S extends SaluhudUser> List<S> findAll(Example<S> example, Sort sort);

    @Override
    public <S extends SaluhudUser> List<S> findAll(Example<S> example);

    @Override
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
    public List<SaluhudUser> findAllById(Iterable<Long> ids);

    @Override
    public List<SaluhudUser> findAll();

    @Override
    @Lock(LockModeType.OPTIMISTIC)
    public <S extends SaluhudUser> List<S> saveAll(Iterable<S> entities);

    @Override
    public List<SaluhudUser> findAll(Sort sort);

    @Override
    public long count();

    @Override
    public boolean existsById(Long id);

    @Override
    public Optional<SaluhudUser> findById(Long id);

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
    
}
