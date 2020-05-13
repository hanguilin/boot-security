package com.spring.security.service.impl;

import com.spring.security.dao.primary.SysUserDao;
import com.spring.security.entity.primary.SysUser;
import com.spring.security.service.SysUserService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;


/**
 * 用户表(SysUser)表服务实现类
 * 
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
	
	@Autowired
	private SysUserDao sysUserDao;

	@Override
	public SysUser selectByAccount(String account) {
		SysUser sysUser = new SysUser();
		sysUser.setAccount(account);
		Optional<SysUser> optional = sysUserDao.findOne(Example.of(sysUser));
		return optional.orElse(null);
	}

	@Override
	public SysUser persistence(SysUser user) {
		return sysUserDao.save(user);
	}

	@Override
	public List<SysUser> all() {
		return sysUserDao.findAll();
	}

	@Override
	public SysUser getCurrentUser() {
		User userDetails = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SysUser sysUser = selectByAccount(userDetails.getUsername());
		return sysUser;
	}
}