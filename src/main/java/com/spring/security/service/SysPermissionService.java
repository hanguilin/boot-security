package com.spring.security.service;

import java.util.List;

import com.spring.security.entity.primary.SysPermission;

/**
 * 权限表(SysPermission)表服务接口
 *
 * @author makejava
 * @since 2019-08-29 21:13:29
 */
public interface SysPermissionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysPermission queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SysPermission> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param sysPermission 实例对象
     * @return 实例对象
     */
    SysPermission insert(SysPermission sysPermission);

}