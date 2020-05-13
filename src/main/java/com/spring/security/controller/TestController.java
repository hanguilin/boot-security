package com.spring.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.common.utils.I18nUtils;

@RestController
public class TestController {
	
	@GetMapping("/test")
	public String doTest() {
		return I18nUtils.getMessage("hello");
	}
}
