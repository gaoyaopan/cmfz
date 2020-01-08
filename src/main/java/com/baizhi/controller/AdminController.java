package com.baizhi.controller;


import com.baizhi.service.AdminService;
import com.util.ImageCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @RequestMapping("/login")
    @ResponseBody
    public Map<String,Object> login(String code,String username,String password,HttpSession session){
        Map<String,Object> login=adminService.adminByUsername(code,username,password,session);
        return login;
    }

    @RequestMapping("/yzm")
    public void yzm(HttpSession session, HttpServletResponse response) throws IOException {
        //获取验证码的随机字符
        String securityCode = ImageCodeUtil.getSecurityCode();
        //将随机字符存进作用域
        session.setAttribute("code", securityCode);

        //将验证码字符生成验证码图片
        BufferedImage image = ImageCodeUtil.createImage(securityCode);
        //设置响应的格式
        response.setContentType("image/png");
        //将验证码响应给前台页面
        ImageIO.write(image,"png",response.getOutputStream());

    }
    //退出登陆
    @RequestMapping("/logout")
    public String  logout(HttpSession session){
        session.invalidate();
        return "redirect:/jsp/login.jsp";
    }

}
