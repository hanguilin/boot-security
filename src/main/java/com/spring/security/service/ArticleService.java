package com.spring.security.service;

import java.util.List;

import org.springframework.data.domain.Example;

import com.spring.security.entity.secondary.Article;

public interface ArticleService {
	
	public List<Article> findList(Example<Article> example);

	public List<Article> getLeatest();
	
}
