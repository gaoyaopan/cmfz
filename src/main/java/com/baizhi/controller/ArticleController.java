package com.baizhi.controller;


import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @RequestMapping("/selectByPage")
    public HashMap<String,Object> selectByPage(Integer page,Integer rows){
        HashMap<String,Object> map=articleService.selectByPage(page,rows);
        return map;
    }

    @RequestMapping("/upload")
    public void upload(MultipartFile img, String id, HttpServletRequest request){
        articleService.upload(img,id,request);
    }

    @RequestMapping("/showAllImg")
    public Map showAllImg(HttpServletRequest request, HttpSession session){
        HashMap hashMap = new HashMap();
        hashMap.put("current_url",request.getContextPath()+"/upload/articleImg/");
        String realPath = session.getServletContext().getRealPath("/upload/articleImg/");
        File file = new File(realPath);
        File[] files = file.listFiles();
        hashMap.put("total_count",files.length);
        ArrayList arrayList = new ArrayList();
        for (File file1 : files) {
            HashMap fileMap = new HashMap();
            fileMap.put("is_dir",false);
            fileMap.put("has_file",false);
            fileMap.put("filesize",file1.length());
            fileMap.put("is_photo",true);
            String name = file1.getName();
            String extension = FilenameUtils.getExtension(name);
            fileMap.put("filetype",extension);
            fileMap.put("filename",name);
            // 通过字符串拆分获取时间戳
            String time = name.split("_")[0];
            // 创建SimpleDateFormat对象 指定yyyy-MM-dd hh:mm:ss 样式
            //  simpleDateFormat.format() 获取指定样式的字符串(yyyy-MM-dd hh:mm:ss)
            // format(参数)  参数:时间类型   new Date(long类型指定时间)long类型  现有数据:字符串类型时间戳
            // 需要将String类型 转换为Long类型 Long.valueOf(str);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String format = simpleDateFormat.format(new Date(Long.valueOf(time)));
            fileMap.put("datetime",format);
            arrayList.add(fileMap);
        }
        hashMap.put("file_list",arrayList);
        return hashMap;
    }

    @RequestMapping("/insertArticle")
    public String insertArticle(Article article,MultipartFile inputfile,String id,HttpServletRequest request){

        if ("".equals(article.getId())){
            String uri = articleService.articleUploa(inputfile, id, request);
            article.setImg(uri);
            // insert
            articleService.insert(article);
        }else{
            //修改
            if (article.getImg()=="") {
                article.setImg(null);
                articleService.update(article);
            }else{
                String uri = articleService.articleUploa(inputfile, id, request);
                article.setImg(uri);
                articleService.update(article);
            }

        }

        return article.getId();
    }
    //删除
    @RequestMapping("/deleteArticle")
    public void deleteArticle(String id){
        articleService.delete(id);
    }
}
