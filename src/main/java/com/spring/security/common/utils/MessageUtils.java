package com.spring.security.common.utils;

import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

public class MessageUtils {
	
	public static String getMessage(String code) {
		 Locale locale = LocaleContextHolder.getLocale();
		 ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
	     String message = reloadableResourceBundleMessageSource.getMessage(code, null, locale);
	     return message;
	}
}
