package com.baizhi.service;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface UserService {


    public HashMap<String,Object> selectByPage(Integer page, Integer rows);

    public void insert(User user);

    public void delete(String id);

    void update(User user);
    //根据性别时间查用户数量
    Map showUserTime();




}