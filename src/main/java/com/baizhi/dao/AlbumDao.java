package com.baizhi.dao;

import com.baizhi.entity.Album;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlbumDao {

    public void insert(Album album);

    public void delete(String id);

    public void update(Album album);

    public List<Album> findByPage(@Param("start") Integer start,@Param("rows") Integer rows);

    public Integer totalCount();

}
