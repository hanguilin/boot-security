package com.spring.security.common.utils;

import java.util.Date;
import java.util.List;

import com.spring.security.config.constant.SecurityConstant;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTUtils {
	
	public static String getToken(String userName, List<String> permissionList) {
        String token = SecurityConstant.TOKEN_SPLIT + Jwts.builder()
		// 主题 放入用户名
		.setSubject(userName)
		// 自定义属性 放入用户拥有请求权限
		.claim(SecurityConstant.AUTHORITIES, permissionList)
		// 失效时间
		.setExpiration(new Date(System.currentTimeMillis() + SecurityConstant.TOKEN_EXPIRE))
		// 签名算法和密钥
		.signWith(SignatureAlgorithm.HS512, SecurityConstant.JWT_SIGN_KEY)
		.compact();
        return token;
	}
	
	public static Claims getClaims(String token) {
		 return Jwts.parser()
			.setSigningKey(SecurityConstant.JWT_SIGN_KEY)
			.parseClaimsJws(token.replace(SecurityConstant.TOKEN_SPLIT, ""))
			.getBody();
	}
	
	public static String getSubject(String token) {
		Claims claims = getClaims(token);
		return claims.getSubject();
	}
}
