package com.baizhi.dao;

import com.baizhi.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleDao {

    public List<Article> findByPage(@Param("start")Integer start,@Param("rows")Integer rows);

    public Integer totalCount();

    public void insert(Article article);

    public void delete(String id);

    public void update(Article article);

}
