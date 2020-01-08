package com.baizhi.service;

import com.baizhi.dao.GuruDao;
import com.baizhi.entity.Guru;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class GuruServiceImpl implements GuruService{

    @Autowired
    GuruDao guruDao;

    @Override
    public List<Guru> queryAll() {
        return guruDao.queryAll();
    }
}
