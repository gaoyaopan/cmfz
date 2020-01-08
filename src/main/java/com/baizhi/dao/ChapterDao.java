package com.baizhi.dao;

import com.baizhi.entity.Chapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChapterDao {
    public void insert(Chapter chapter);

    public void delete(String id);

    public void update(Chapter chapter);

    public List<Chapter> findByPage(String id);

    public Integer totalCount();
}
