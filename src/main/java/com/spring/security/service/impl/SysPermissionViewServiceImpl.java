package com.spring.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.spring.security.dao.primary.SysUserPermissionViewDao;
import com.spring.security.entity.primary.SysUserPermissionView;
import com.spring.security.service.SysPermissionViewService;

@Service("sysPermissionViewService")
public class SysPermissionViewServiceImpl implements SysPermissionViewService{
	
	@Autowired
	private SysUserPermissionViewDao sysPermissionViewDao;

	@Override
	public List<SysUserPermissionView> findByUrl(String url) {
		SysUserPermissionView query = new SysUserPermissionView();
		query.setMenuUrl(url);
		return sysPermissionViewDao.findAll(Example.of(query));
	}


}
