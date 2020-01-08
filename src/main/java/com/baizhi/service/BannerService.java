package com.baizhi.service;

import com.baizhi.entity.Banner;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public interface BannerService {

    public void insert(Banner banner);

    public void delete(String id);

    public void update(Banner banner);

    public HashMap<String,Object> showAllPage(Integer page,Integer rows);

    //总条数
    public Integer count();

    //上传
    public void bannerUpload(MultipartFile url, String id, HttpServletRequest request);

}
