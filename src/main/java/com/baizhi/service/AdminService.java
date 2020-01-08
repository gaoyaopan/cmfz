package com.baizhi.service;


import javax.servlet.http.HttpSession;
import java.util.HashMap;

public interface AdminService {
    public HashMap<String,Object> adminByUsername(String code, String username, String password, HttpSession session);
}
