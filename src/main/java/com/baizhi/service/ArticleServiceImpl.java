package com.baizhi.service;

import com.baizhi.dao.ArticleDao;
import com.baizhi.entity.Article;
import com.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;


@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleDao articleDao;

    @Override
    public void insert(Article article) {
        article.setId(UUIDUtil.getUUID().toString());
        article.setCreateDate(new Date());
        article.setPublishDate(new Date());
        articleDao.insert(article);
    }

    @Override
    public void delete(String id) {
        articleDao.delete(id);
    }

    @Override
    public void update(Article article) {
        articleDao.update(article);
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public HashMap<String, Object> selectByPage(Integer page, Integer rows) {
        HashMap<String,Object> map=new HashMap<>();
        //当前页
        Integer start=(page-1)*rows;
        List<Article> list=articleDao.findByPage(start,rows);
        //页号
        map.put("page",page);
        //总条数
        Integer totalCount=articleDao.totalCount();
        map.put("count",totalCount);
        //总页数
        Integer pageCount=0;
        if(totalCount%rows==0){
            pageCount=totalCount/rows;
        }else{
            pageCount=totalCount/rows+1;
        }
        map.put("total",pageCount);
        map.put("rows",list);
        return map;
    }

    @Override
    public void upload(MultipartFile img, String id, HttpServletRequest request) {
        String realPath = request.getServletContext().getRealPath("/upload/articleImg");
        File file=new File(realPath);
        if(!file.exists()){
            file.mkdirs();
        }
        String fileName=img.getOriginalFilename();
        String newName=new Date().getTime()+"-"+fileName;
        try{
            img.transferTo(new File(realPath,newName));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public String articleUploa(MultipartFile inputfile, String id, HttpServletRequest request) {
        //获取http
        String scheme = request.getScheme();
        //获取IP
        String localhost =null;
        try {
            localhost = InetAddress.getLocalHost().toString();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        //获取端口号
        int serverPort = request.getServerPort();
        //获取项目名
        String contextPath = request.getContextPath();


        //根据相对路径获取绝对路径
        String realPath = request.getServletContext().getRealPath("/upload/article/");
        File file = new File(realPath);
        //创建文件
        if(!file.exists()){
            file.mkdir();
        }
        //获取文件名
        String filname = inputfile.getOriginalFilename();
        //防止图片发生覆盖，重新给图片命名
        String newName=new Date().getTime()+"-"+filname;

        String uri = scheme +"://"+ localhost.split("/")[1] + ":" + serverPort + contextPath + "/upload/article/" + newName;
        //文件上传
        try {
            inputfile.transferTo(new File(realPath, newName));
            //修改轮播图得信息
        }catch(IOException e){
            e.printStackTrace();
        }
        return uri;
    }




}
