package com.spring.security.config.constant;

public interface SecurityConstant {
	 
    /**
     * token分割
     */
    String TOKEN_SPLIT = "Bearer ";
 
    /**
     * JWT签名加密key
     */
    String JWT_SIGN_KEY = "niceyoo";
 
    /**
     * token参数头
     */
    String HEADER = "Authorization";
 
    /**
     * 权限参数头
     */
    String AUTHORITIES = "authorities";
 
    /**
     * 用户选择JWT保存时间参数头
     */
    String SAVE_LOGIN = "saveLogin";
 
    /**
     * 交互token前缀key
     */
    String TOKEN_PRE = "TMAX_TOKEN_PRE:";
 
    /**
     * 用户token前缀key 单点登录使用
     */
    String USER_TOKEN = "TMAX_USER_TOKEN:";
    
    /**
     * token失效时间毫秒
     */
    Long TOKEN_EXPIRE = 30 * 60 * 1000L;
}
