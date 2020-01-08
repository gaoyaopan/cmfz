package com.baizhi.dao;

import com.baizhi.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BannerDao {

    public void insert(Banner banner);

    public void delete(String id);

    public void update(Banner banner);



    //start起始页  rows每页显示的条数
    public List<Banner> findByPage(@Param("start") Integer start,@Param("rows")Integer rows);

    //总条数
    public Integer totalCount();



    List<Banner> queryBannersByTime();




}
