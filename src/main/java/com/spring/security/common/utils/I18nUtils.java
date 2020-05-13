package com.spring.security.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;

public class I18nUtils {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(I18nUtils.class);

    public static String getMessage(String code) {
        try {
            return SpringUtils.getContext().getMessage(code, null, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException e) {
            LOGGER.error("获取国际化资源{}失败" + e.getMessage(), code, e);
            return "";
        }
    }
}
