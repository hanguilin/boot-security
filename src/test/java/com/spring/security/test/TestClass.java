package com.spring.security.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.spring.security.SecurityApplication;
import com.spring.security.common.utils.SpringUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SecurityApplication.class})// 指定启动类
public class TestClass {
	
	public String getEncoderPass(String pass) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(pass);
	}
	
	@Test
	public void test1() {
		String message = SpringUtils.getContext().getMessage("hello", null, LocaleContextHolder.getLocale());
		System.out.println(message);
	}
}
