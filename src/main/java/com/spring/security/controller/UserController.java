package com.spring.security.controller;

import com.spring.security.common.entity.JsonResult;
import com.spring.security.common.utils.ResultTool;
import com.spring.security.entity.primary.SysUser;
import com.spring.security.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {
	
    @Autowired
    SysUserService sysUserService;

    @GetMapping("/getCurrentUser")
    public JsonResult<SysUser> getCurrentUser() {
        SysUser users = sysUserService.getCurrentUser();
        return ResultTool.success(users);
    }
    
    @GetMapping("/test")
    public JsonResult<Object> test() {
        return ResultTool.success("hello world");
    }
}
