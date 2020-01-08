package com.baizhi.dao;

import com.baizhi.entity.User;
import com.baizhi.entity.UserDto;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface UserDao {


    User queryById(String id);

    public List<User> findByPage(@Param("start")Integer start,@Param("rows")Integer rows);

    public Integer totalCount();


    List<User> queryAll(User user);


    int insert(User user);


    int update(User user);


   public  int deleteById(String id);
    //按性别和天数查
    public Integer queryUserByTime(@Param("sex") String sex,@Param("day") Integer day);

    //按性别和地区查
    public List<UserDto> selectByLocation(String sex);


    public HashMap login(@Param("phone") String phone,@Param("password") String password);

}