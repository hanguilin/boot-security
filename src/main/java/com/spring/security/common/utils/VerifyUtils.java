package com.spring.security.common.utils;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;

/**
 * 校验工具
 * 
 * @author Administrator
 *
 */
public class VerifyUtils {
	
	/**
	 * 集合
	 * @param collection
	 * @return
	 */
	public static boolean isEmpty(Collection<?> collection) {
		return null == collection || collection.isEmpty();
	}
	
	/**
	 * 实体
	 * @param obj
	 * @return
	 */
	public static boolean isEmpty(Object obj) {
		return null == obj;
	}
	
	/**
	 * 字符
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return StringUtils.isBlank(str);
	}
	
	/**
	 * 字符
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return StringUtils.isNotBlank(str);
	}
}
