package com.github.taosoft.bookstore.account.domain;

import com.github.taosoft.bookstore.domain.account.Account;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

/**
 * 用户对象数据仓库
 **/
@CacheConfig(cacheNames = "repository.account")
public interface AccountRepository extends CrudRepository<Account, Integer> {

    @Override
    Iterable<Account> findAll();


    @Cacheable(key = "#username")
    Account findByUsername(String username);

    /**
     * 判断唯一性，用户名、邮箱、电话不允许任何一个重复
     */
    boolean existsByUsernameOrEmailOrTelephone(String username, String email, String telephone);

    /**
     * 判断唯一性，用户名、邮箱、电话不允许任何一个重复
     */
    Collection<Account> findByUsernameOrEmailOrTelephone(String username, String email, String telephone);

    /**
     * 判断存在性，用户名存在即为存在
     */
    @Cacheable(key = "#username")
    boolean existsByUsername(String username);


    // 覆盖以下父类中需要处理缓存失效的方法
    // 父类取不到CacheConfig的配置信息，所以不能抽象成一个通用的父类接口中完成
    @Caching(evict = {
            @CacheEvict(key = "#entity.id"),
            @CacheEvict(key = "#entity.username")
    })
    <S extends Account> S save(S entity);

    @CacheEvict
    <S extends Account> Iterable<S> saveAll(Iterable<S> entities);

    @Cacheable(key = "#id")
    Optional<Account> findById(ID id);

    @Cacheable(key = "#id")
    boolean existsById(ID id);

    @CacheEvict(key = "#id")
    void deleteById(ID id);

    @CacheEvict(key = "#entity.id")
    void delete(Account entity);

    @CacheEvict(allEntries = true)
    void deleteAll(Iterable<? extends Account> entities);

    @CacheEvict(allEntries = true)
    void deleteAll();
}
