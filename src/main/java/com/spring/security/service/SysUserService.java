package com.spring.security.service;

import java.util.List;

import com.spring.security.entity.primary.SysUser;

/**
 * 用户表(SysUser)表服务接口
 *
 */
public interface SysUserService {

	SysUser selectByAccount(String account); 
	
	SysUser persistence(SysUser user);
	
	List<SysUser> all();

	SysUser getCurrentUser();
}