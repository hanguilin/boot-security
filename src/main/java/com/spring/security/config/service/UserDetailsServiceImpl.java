package com.spring.security.config.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.spring.security.common.utils.VerifyUtils;
import com.spring.security.entity.primary.SysUser;
import com.spring.security.service.SysUserService;

/**
 * @Description:
 */
public class UserDetailsServiceImpl implements UserDetailsService {
	
    @Autowired
    private SysUserService sysUserService;
    
    @Autowired
    private GrantedAuthorityServiceImpl grantedAuthorityServiceImpl;

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        if (VerifyUtils.isEmpty(account)) {
            throw new RuntimeException("用户不能为空");
        }
        //根据用户名查询用户
        SysUser sysUser = sysUserService.selectByAccount(account);
        if (sysUser == null) {
            throw new RuntimeException("用户不存在");
        }
        List<GrantedAuthority> grantedAuthorities = grantedAuthorityServiceImpl.getUserAuthority(sysUser);
        return new User(nullByDefault(sysUser.getAccount()), nullByDefault(sysUser.getPassword()), sysUser.getEnabled(), sysUser.getAccountNonExpired(), sysUser.getCredentialsNonExpired(), sysUser.getAccountNonLocked(), grantedAuthorities);
    }
    
    public String nullByDefault(String target) {
		if(VerifyUtils.isEmpty(target)) {
			return "";
		}
		return target;
	}
}
