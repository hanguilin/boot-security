package com.spring.security.config.interceptor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.spring.security.common.entity.JsonResult;
import com.spring.security.common.enums.ResultCode;
import com.spring.security.common.utils.VerifyUtils;

@Configuration
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String method = request.getMethod();
		if(!method.equalsIgnoreCase("option")) {
			String authorization = request.getHeader("Authorization");
			if(VerifyUtils.isEmpty(authorization)) {
				JsonResult<Object> result = new JsonResult<Object>(false, ResultCode.USER_NOT_LOGIN);
				response.setContentType("text/json;charset=utf-8");
				response.getWriter().write(JSON.toJSONString(result));
				return false;
			}else {
				String token = String.valueOf(request.getSession().getAttribute("token"));
				if(!authorization.equals(token)) {
					JsonResult<Object> result = new JsonResult<Object>(false, ResultCode.USER_ACCOUNT_EXPIRED);
					response.setContentType("text/json;charset=utf-8");
					response.getWriter().write(JSON.toJSONString(result));
					return false;
				}
				String expireTime = authorization.split("_")[1];
				LocalDateTime parse = LocalDateTime.parse(expireTime, formatter);
				boolean isAfter = LocalDateTime.now().isAfter(parse);
				if(isAfter) {
					JsonResult<Object> result = new JsonResult<Object>(false, ResultCode.USER_ACCOUNT_EXPIRED);
					response.setContentType("text/json;charset=utf-8");
					response.getWriter().write(JSON.toJSONString(result));
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}
	
}
