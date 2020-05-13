package com.spring.security.dao.primary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.security.entity.primary.SysRole;

@Repository
public interface SysRoleDao extends JpaRepository<SysRole, Integer> {

}
