package com.spring.security.service;

import java.util.List;

import com.spring.security.entity.primary.SysUserPermissionView;

public interface SysPermissionViewService {
	
	List<SysUserPermissionView> findByUrl(String url);
	
}
