package com.baizhi.service;

import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@Service
@Transactional
public class BannerServiceImpl implements BannerService {

    @Autowired
    BannerDao bannerDao;

    @Override
    public void insert(Banner banner) {
        bannerDao.insert(banner);
    }

    @Override
    public void delete(String id) {
        bannerDao.delete(id);
    }

    @Override
    public void update(Banner banner) {
        bannerDao.update(banner);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public HashMap<String, Object> showAllPage(Integer page, Integer rows) {

        //创建一个map集合
        HashMap<String,Object> map=new HashMap<>();
        //当前页
        Integer start=(page-1)*rows;
        List<Banner> list=bannerDao.findByPage(start,rows);
        map.put("rows",list);
        //页号
        map.put("page",page);
        //总条数
        Integer totalCount=bannerDao.totalCount();
        map.put("records",totalCount);
        //令总页数为0   如果总条数/每页显示得条数为0，有总页数
        //总页数
        Integer pageCount=0;
        if(totalCount % rows==0){
            pageCount=totalCount/rows;
        }else{
            pageCount=totalCount/rows+1;
        }
        map.put("total",pageCount);
        return map;

    }

    @Override
    public Integer count() {
        Integer totalCount=bannerDao.totalCount();
        return totalCount;
    }

    //上传
    @Override
    public void bannerUpload(MultipartFile url, String id, HttpServletRequest request) {
        //根据相对路径获取绝对路径
        String realPath=request.getServletContext().getRealPath("/upload/photo");
        File file = new File(realPath);
        //创建文件
        if(!file.exists()){
            file.mkdir();
        }
        //获取文件名
        String filename=url.getOriginalFilename();
        //防止图片发生覆盖，重新给图片命名
        String newName=new Date().getTime()+"-"+filename;
        //文件上传
        try {
            url.transferTo(new File(realPath, newName));
            //修改轮播图得信息
            Banner banner = new Banner();
            banner.setId(id);
            banner.setUrl(newName);
            //调用修改方法
            bannerDao.update(banner);
        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
