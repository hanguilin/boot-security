package com.spring.security.config.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.spring.security.dao.primary.SysUserPermissionViewDao;
import com.spring.security.entity.primary.SysUser;
import com.spring.security.entity.primary.SysUserPermissionView;

@Service
public class GrantedAuthorityServiceImpl {
	
    @Autowired
    private SysUserPermissionViewDao sysUserPermissionViewDao;
    
    public List<GrantedAuthority> getUserAuthority(SysUser sysUser) {
    	List<String> codeList = getUserAuthorityCode(sysUser);
    	List<GrantedAuthority> grantedAuthorities = codeList.stream().map(o->{
    		return new SimpleGrantedAuthority(o);
    	}).collect(Collectors.toList());
        return grantedAuthorities;
    }
    
    public List<String> getUserAuthorityCode(SysUser sysUser) {
        if (sysUser != null) {
            //获取该用户所拥有的权限
        	SysUserPermissionView exampleUser = new SysUserPermissionView();
        	exampleUser.setId(sysUser.getId());
            List<SysUserPermissionView> sysPermissions = sysUserPermissionViewDao.findAll(Example.of(exampleUser));
            List<String> codeList = sysPermissions.stream().map(SysUserPermissionView::getPermissionCode).collect(Collectors.toList());
            return codeList;
        }
        return Collections.emptyList();
    }
    
    public static List<GrantedAuthority> getUserAuthorityByCode(List<String> permissionCodeList) {
    	List<GrantedAuthority> grantedAuthorities = permissionCodeList.stream().map(o->{
    		return new SimpleGrantedAuthority(o);
    	}).collect(Collectors.toList());
        return grantedAuthorities;
    }
}
