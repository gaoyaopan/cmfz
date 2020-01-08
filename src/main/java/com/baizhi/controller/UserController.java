package com.baizhi.controller;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.Album;
import com.baizhi.entity.User;
import com.baizhi.entity.UserDto;
import com.baizhi.service.UserService;
import com.util.HttpUtil;
import com.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserDao userDao;


    @RequestMapping("/selectByPage")
    public HashMap<String,Object> selectByPage(Integer page, Integer rows){
        HashMap<String, Object> map = userService.selectByPage(page, rows);
        System.out.println(map);
        return map;
    }
    @RequestMapping("/edit")
    public Map edit(User  user,String id,String oper){
        HashMap hashMap = new HashMap();
        if(oper.equals("add")){
            String uuid = UUIDUtil.getUUID();
            user.setId(uuid);
            user.setRigestDate(new Date());
            user.setLastLogin(new Date());
            hashMap.put("userId",uuid);
            userService.insert(user);
        }
        if(oper.equals("del")){
            userService.delete(id);
        }
        if(oper.equals("edit")){
            userService.update(user);
            hashMap.put("userId",user.getId());
        }
        return hashMap;
    }

    //文件上传
    @RequestMapping("/upload")
    public Map upload(HttpSession session, MultipartFile photo, String id, HttpServletRequest request){
        HashMap hashMap = new HashMap();
        String realPath = session.getServletContext().getRealPath("/upload/albumImg/");
        File file = new File(realPath);
        if (!file.exists()){
            file.mkdirs();
        }
        // 网络路径
        String http = HttpUtil.getHttp(photo, request, "/upload/albumImg/");
        User user = new User();
        user.setId(id);
        user.setPhoto(http);
        userService.update(user);
        hashMap.put("status",200);
        return hashMap;
    }
    //根据性别时间查数量
    @RequestMapping("showUserTime")
    public Map showUserTime(){
        Map map = userService.showUserTime();
        return map;
    }


    //地图
    @RequestMapping("/showUserLocation")
    public Map showUserLocation(){
        HashMap hashMap = new HashMap();

        List<UserDto> manDto = userDao.selectByLocation("0");
        System.out.println(manDto);
        List<UserDto> womenDto = userDao.selectByLocation("1");
        System.out.println(womenDto);
        hashMap.put("man",manDto);
        hashMap.put("women",womenDto);


        return hashMap;
    }
    @RequestMapping("/saveUser")
    public Map saveUser(User user, String oper, String id) {
        HashMap hashMap = new HashMap();
        if ("add".equals(oper)) {
            userService.insert(user);
            hashMap.put("userId", user.getId());
        } else if ("edit".equals(oper)) {
            user.setPhoto(null);
            userService.update(user);
            hashMap.put("userId", user.getId());
        } else {
            userService.delete(id);
        }
        return hashMap;
    }



    @RequestMapping("login")
    public HashMap login(String phone,String password){
        HashMap login = userDao.login(phone, password);
        return login;
    }
}