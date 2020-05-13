package com.spring.security.config.handler;

import com.google.common.collect.Maps;
import com.spring.security.common.entity.JsonResult;
import com.spring.security.common.utils.JWTUtils;
import com.spring.security.common.utils.PrivateUtils;
import com.spring.security.common.utils.ResponseUtil;
import com.spring.security.common.utils.ResultTool;
import com.spring.security.config.service.GrantedAuthorityServiceImpl;
import com.spring.security.entity.primary.SysUser;
import com.spring.security.service.SysUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Description: 登录成功处理逻辑
 */
@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
    @Autowired
    private SysUserService sysUserService;
    
    @Autowired
    private GrantedAuthorityServiceImpl grantedAuthorityServiceImpl;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //更新用户表上次登录时间、更新人、更新时间等字段
        User userDetails = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SysUser sysUser = sysUserService.selectByAccount(userDetails.getUsername());
        String now = PrivateUtils.getNowDateString();
        sysUser.setLastLoginTime(now);
        sysUser.setUpdateTime(now);
        sysUser.setUpdateUser(sysUser.getId());
        sysUserService.persistence(sysUser);
        
        
        List<String> permissionCodeList = grantedAuthorityServiceImpl.getUserAuthorityCode(sysUser);
        //此处还可以进行一些处理，比如登录成功之后可能需要返回给前台当前用户有哪些菜单权限，
        //进而前台动态的控制菜单的显示等，具体根据自己的业务需求进行扩展
        String token = JWTUtils.getToken(userDetails.getUsername(), permissionCodeList);
        
        Map<String, Object> data = Maps.newHashMap();
        data.put("token", token);
        data.put("user", sysUser.getUserName());
        JsonResult<Map<String, Object>> result = ResultTool.success(data);
        result.setErrorMsg("登录成功");
        ResponseUtil.out(httpServletResponse, result);
    }
}
