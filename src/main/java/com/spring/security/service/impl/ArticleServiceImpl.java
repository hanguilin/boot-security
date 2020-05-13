package com.spring.security.service.impl;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.spring.security.dao.primary.SysUserDao;
import com.spring.security.dao.secondary.ArticleDao;
import com.spring.security.entity.primary.SysUser;
import com.spring.security.entity.secondary.Article;
import com.spring.security.service.ArticleService;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleDao articleDao;
	
	@Autowired
	private SysUserDao sysUserDao;
	
	@Override
	public List<Article> findList(Example<Article> example) {
		List<Article> all = articleDao.findAll(example);
		setUserInfo(all);
		return all;
	}

	private void setUserInfo(List<Article> all) {
		List<Integer> userIds = all.stream().map(Article::getCreateId).collect(Collectors.toList());
		List<SysUser> sysUsers = sysUserDao.findAllById(userIds);
		Map<Integer, SysUser> userMap = sysUsers.stream().collect(Collectors.toMap(SysUser::getId, Function.identity()));
		all.forEach(o->o.setCreateUser(userMap.get(o.getCreateId())));
	}

	@Override
	public List<Article> getLeatest() {
		List<Article> findLeatest = articleDao.findLeatest();
		setUserInfo(findLeatest);
		return findLeatest;
	}

}
