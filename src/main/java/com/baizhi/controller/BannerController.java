package com.baizhi.controller;


import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    BannerService bannerService;

    @RequestMapping("/queryByPage")
    public HashMap<String,Object> queryByPage(Integer page,Integer rows){
        HashMap<String,Object> map=bannerService.showAllPage(page,rows);
        return map;
    }

    @RequestMapping("/edit")
    public String edit(Banner banner,String oper) {
        if (oper.equals("add")) {
            String uuid = UUID.randomUUID().toString().replace("-", "");
            banner.setId(uuid);
            bannerService.insert(banner);
        }
        /*if (banner.getUrl()=="") {
            banner.setUrl(null);
            bannerService.update(banner);
        }else{
            bannerService.update(banner);
            return banner.getId();
        }*/
        if (oper.equals("del")) {
             bannerService.delete(banner.getId());
        }
        return banner.getId();
    }

    @RequestMapping("/uploadBanner")
    public void uploadBanner(MultipartFile url, String id, HttpServletRequest request) {
        bannerService.bannerUpload(url, id, request);
    }


}
