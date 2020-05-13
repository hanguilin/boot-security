package com.spring.security.config.jwt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.spring.security.common.enums.ResultCode;
import com.spring.security.common.utils.JWTUtils;
import com.spring.security.common.utils.ResponseUtil;
import com.spring.security.common.utils.ResultTool;
import com.spring.security.common.utils.VerifyUtils;
import com.spring.security.config.constant.SecurityConstant;
import com.spring.security.config.constant.UrlConstant;
import com.spring.security.config.service.GrantedAuthorityServiceImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;

public class JWTAuthenticationFilter extends BasicAuthenticationFilter   {

	private static final Logger LOGGER = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
		super(authenticationManager, authenticationEntryPoint);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

		String header = request.getHeader(SecurityConstant.HEADER);
		if(VerifyUtils.isEmpty(header)){
			header = request.getParameter(SecurityConstant.HEADER);
		}
		Boolean notValid = VerifyUtils.isEmpty(header) || (!header.startsWith(SecurityConstant.TOKEN_SPLIT));
		String uri = request.getRequestURI();
		if (notValid && !UrlConstant.LOGIN.equals(uri)) {
			ResponseUtil.out(response, ResultTool.fail(ResultCode.USER_NOT_LOGIN));
			return;
		}
		try {
			/* UsernamePasswordAuthenticationToken 继承 AbstractAuthenticationToken 实现 Authentication
            所以当在页面中输入用户名和密码之后首先会进入到 UsernamePasswordAuthenticationToken验证(Authentication)，*/
			UsernamePasswordAuthenticationToken authentication = getAuthentication(header, response);
			if(authentication == null) {
				return;
			}
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		catch (ExpiredJwtException e) {
			ResponseUtil.out(response, ResultTool.fail(ResultCode.USER_ACCOUNT_EXPIRED));
			return;
		}
		catch (Exception e){
			LOGGER.error(e.getMessage(), e);
			ResponseUtil.out(response, ResultTool.fail(ResultCode.PROGROM_ERROR));
			return;
		}

		chain.doFilter(request, response);
	}

	@SuppressWarnings("unchecked")
	private UsernamePasswordAuthenticationToken getAuthentication(String header, HttpServletResponse response) {

		// 用户名
		String username = null;
		// 权限
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

			// 解析token
			Claims claims = JWTUtils.getClaims(header);
			// 获取用户名
			username = claims.getSubject();
			// 获取权限
			Object object = claims.get(SecurityConstant.AUTHORITIES);
			if(!VerifyUtils.isEmpty(object)){
				List<String> codeList = (List<String>) object;
				List<GrantedAuthority> grantedAuthority = GrantedAuthorityServiceImpl.getUserAuthorityByCode(codeList);
				authorities.addAll(grantedAuthority);
			}

		if(!VerifyUtils.isEmpty(username)) {
			// 踩坑提醒 此处password不能为null
			User principal = new User(username, "", authorities);
			return new UsernamePasswordAuthenticationToken(principal, null, authorities);
		}
		return null;
	}
}
