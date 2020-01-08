package com.baizhi.service;

import com.baizhi.entity.Article;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public interface ArticleService {

    public void insert(Article article);

    public void delete(String id);

    public void update(Article article);

    public HashMap<String,Object> selectByPage(Integer page,Integer rows);

    //上传
    public void upload(MultipartFile img, String id, HttpServletRequest request);

    public String articleUploa(MultipartFile inputfile, String id, HttpServletRequest request);

}
