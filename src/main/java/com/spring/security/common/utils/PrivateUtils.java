package com.spring.security.common.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PrivateUtils {
	
	private static final DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	public static String getDateString(LocalDateTime time) {
		return pattern.format(time);
	}
	
	public static String getNowDateString() {
		return pattern.format(LocalDateTime.now());
	}
	
}
