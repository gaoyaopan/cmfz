package com.baizhi.service;

import com.baizhi.entity.User;
import com.baizhi.dao.UserDao;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;



    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Override
    public HashMap<String, Object> selectByPage(Integer page, Integer rows) {
        HashMap<String,Object> map=new HashMap<>();
        Integer start=(page-1)*rows;
        List<User> list=userDao.findByPage(start,rows);
        Integer totalCount=userDao.totalCount();
        Integer pageCount=0;
        if(totalCount%rows==0){
            pageCount=totalCount/rows;
        }else{
            pageCount=totalCount/rows+1;
        }
        map.put("total",pageCount);     //总页数
        map.put("records",totalCount);  //总条数
        map.put("rows",list);           //列表
        map.put("page",page);           //当前页
        return map;
    }

    @Override
    public void insert(User user) {
        String uuid= UUID.randomUUID().toString().replace("-","");
        user.setId(uuid);
        userDao.insert(user);
    }

    @Override
    public void delete(String id) {
        userDao.deleteById(id);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Map showUserTime(){
        HashMap hashMap = new HashMap();
        ArrayList manList = new ArrayList();
        manList.add(userDao.queryUserByTime("0",1));
        manList.add(userDao.queryUserByTime("0",7));
        manList.add(userDao.queryUserByTime("0",30));
        manList.add(userDao.queryUserByTime("0",365));
        ArrayList womenList = new ArrayList();
        womenList.add(userDao.queryUserByTime("1",1));
        womenList.add(userDao.queryUserByTime("1",7));
        womenList.add(userDao.queryUserByTime("1",30));
        womenList.add(userDao.queryUserByTime("1",365));
        hashMap.put("man",manList);
        hashMap.put("women",womenList);
        return hashMap;
    }

}