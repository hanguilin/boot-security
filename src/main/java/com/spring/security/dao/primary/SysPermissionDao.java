package com.spring.security.dao.primary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.security.entity.primary.SysPermission;

@Repository
public interface SysPermissionDao extends JpaRepository<SysPermission, Integer>{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
	@Query(value = "select * from sys_permission where id = ?1", nativeQuery = true)
    SysPermission queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
	@Query(value = "select * from sys_permission limit ?1, ?2", nativeQuery = true)
    List<SysPermission> queryAllByLimit(int offset, int limit);
}