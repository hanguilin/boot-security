package com.spring.security.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.alibaba.fastjson.JSON;
import com.spring.security.common.entity.JsonResult;
import com.spring.security.common.enums.ResultCode;

@RestControllerAdvice
public class ExceptionAdvice {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionAdvice.class);
	
	@ExceptionHandler(Exception.class)
	public void advice(Exception e, HttpServletRequest request, HttpServletResponse response) {
		JsonResult<String> result = new JsonResult<String>();
		result.setErrorCode(ResultCode.PROGROM_ERROR.getCode());
		result.setErrorMsg(ResultCode.PROGROM_ERROR.getMessage());
		result.setData(e.getMessage());
		try {
			response.setContentType("text/json;charset=utf-8");
			response.getWriter().write(JSON.toJSONString(result));
		} catch (IOException e1) {
			LOGGER.error(e1.getMessage(), e);
		}
	}
}
