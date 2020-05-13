package com.spring.security.common.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.spring.security.config.jwt.JWTAuthenticationFilter;

public class ResponseUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JWTAuthenticationFilter.class);
	
	public static void out(HttpServletResponse response, Object object) {
		try {
			response.setContentType("text/json;charset=utf-8");
			response.getWriter().write(JSON.toJSONString(object));
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}
