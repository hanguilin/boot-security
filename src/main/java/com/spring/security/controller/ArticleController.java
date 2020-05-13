package com.spring.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.common.entity.JsonResult;
import com.spring.security.common.utils.ResultTool;
import com.spring.security.entity.secondary.Article;
import com.spring.security.service.ArticleService;

@RestController
@RequestMapping("/article")
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	@PostMapping("/get")
	public JsonResult<List<Article>> doGet(@RequestBody Article article){
		List<Article> list = articleService.findList(Example.of(article));
		return ResultTool.success(list);
	}
	
	@GetMapping("/leatest")
	public JsonResult<List<Article>> doLeatest(){
		List<Article> list = articleService.getLeatest();
		return ResultTool.success(list);
	}
}
