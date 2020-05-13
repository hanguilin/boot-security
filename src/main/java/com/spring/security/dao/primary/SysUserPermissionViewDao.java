package com.spring.security.dao.primary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.spring.security.entity.primary.SysUserPermissionView;

@Repository
public interface SysUserPermissionViewDao extends JpaRepository<SysUserPermissionView, Integer>, JpaSpecificationExecutor<SysUserPermissionView>{

}
