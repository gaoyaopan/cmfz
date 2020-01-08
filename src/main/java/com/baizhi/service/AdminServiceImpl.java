package com.baizhi.service;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.HashMap;


@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminDao adminDao;

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public HashMap<String, Object> adminByUsername(String code, String username, String password, HttpSession session) {
        HashMap<String,Object> map=new HashMap<>();
        String code1=(String)session.getAttribute("code");

        //判断验证码
        if(code1.equals(code)){
            //判断用户名
            Admin admin=adminDao.queryOne(username);
            if(admin!=null){
                //判断密码
                if(admin.getPassword().equals(password)){
                    map.put("msg","ok");
                    return map;
                }else{
                    map.put("msg","密码输入不正确,请重新输入!");
                    return map;
                }
            }else{
                map.put("msg","用户名不存在,请重新输入!");
                return map;
            }
        }else{
            map.put("msg","验证码有误,请重新输入!");
            return map;
        }
    }
}
