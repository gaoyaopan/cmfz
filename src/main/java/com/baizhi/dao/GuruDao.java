package com.baizhi.dao;

import com.baizhi.entity.Guru;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GuruDao {

    public List<Guru> queryAll();

}
