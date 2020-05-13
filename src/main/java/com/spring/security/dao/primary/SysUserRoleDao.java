package com.spring.security.dao.primary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.security.entity.primary.SysUserRole;

@Repository
public interface SysUserRoleDao extends JpaRepository<SysUserRole, Integer> {

}
