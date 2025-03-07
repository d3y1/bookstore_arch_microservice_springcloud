package com.github.taosoft.bookstore.domain.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Optional;

/**
 * 认证用户信息查询服务
 * <p>
 * {@link UserDetailsService}接口定义了从外部（数据库、LDAP，任何地方）根据用户名查询到
 */
@Named
public class AuthenticAccountDetailsService implements UserDetailsService {

    @Inject
    private AuthenticAccountRepository accountRepository;

    /**
     * 根据用户名查询用户角色、权限等信息
     * 如果用户名无法查询到对应的用户，或者权限不满足，请直接抛出{@link UsernameNotFoundException}，勿返回null
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return Optional.ofNullable(accountRepository.findByUsername(username)).orElseThrow(() -> new UsernameNotFoundException("未找到该用户:" + username));
    }

}
