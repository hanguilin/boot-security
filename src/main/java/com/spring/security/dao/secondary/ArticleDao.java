package com.spring.security.dao.secondary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.security.entity.secondary.Article;

@Repository
public interface ArticleDao extends JpaRepository<Article, Integer>, JpaSpecificationExecutor<Article> {

	@Query(value = "select * from article order by create_date desc limit 5", nativeQuery = true)
	List<Article> findLeatest();

}
