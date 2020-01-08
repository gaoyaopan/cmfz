package com.baizhi.controller;


import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import com.util.HttpUtil;
import com.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/album")
public class AlbumController {

    @Autowired
    AlbumService albumService;

    @RequestMapping("/selectByPage")
    public HashMap<String,Object> selectByPage(Integer page,Integer rows){
        HashMap<String,Object> map=albumService.selectByPage(page,rows);
        return map;
    }

    @RequestMapping("/edit")
    public Map edit(Album album,String id,String oper){
        HashMap hashMap = new HashMap();
        if(oper.equals("add")){
            String uuid = UUIDUtil.getUUID();
            album.setId(uuid);
            hashMap.put("albumId",uuid);
            albumService.insert(album);
        }
        if(oper.equals("del")){
            albumService.delete(id);
        }
        if(oper.equals("edit")){
            albumService.update(album);
            hashMap.put("albumId",album.getId());
        }
        return hashMap;
    }

    @RequestMapping("/upload")
    public Map upload(HttpSession session,MultipartFile src, String albumId, HttpServletRequest request){
        HashMap hashMap = new HashMap();
        String realPath = session.getServletContext().getRealPath("/upload/albumImg/");
        File file = new File(realPath);
        if (!file.exists()){
            file.mkdirs();
        }
        // 网络路径
        String http = HttpUtil.getHttp(src, request, "/upload/albumImg/");
        Album album = new Album();
        album.setId(albumId);
        album.setSrc(http);

        albumService.update(album);
        hashMap.put("status",200);
        return hashMap;
    }


}
