package com.spring.security.config.handler;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import com.spring.security.common.utils.VerifyUtils;
import com.spring.security.dao.primary.SysMenuPermissionViewDao;
import com.spring.security.entity.primary.SysMenuPermissionView;

/**
 * @Description:
 */
@Component
public class CustomizeFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	
    AntPathMatcher antPathMatcher = new AntPathMatcher();
    
    @Autowired
    private SysMenuPermissionViewDao sysMenuPermissionViewDao;
    
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        //获取请求地址
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        //查询具体某个接口的权限
        SysMenuPermissionView query = new SysMenuPermissionView();
        query.setMenuUrl(requestUrl);
        List<SysMenuPermissionView> permissionList = sysMenuPermissionViewDao.findAll(Example.of(query));
        if(VerifyUtils.isEmpty(permissionList)){
            //没有匹配上的资源，都是登录访问
        	if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
        		return SecurityConfig.createList("ROLE_LOGIN");
        	}
        }
        String[] attributes = new String[permissionList.size()];
        for(int i = 0; i < permissionList.size(); i++){
            attributes[i] = permissionList.get(i).getPermissionCode();
        }
        return SecurityConfig.createList(attributes);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
