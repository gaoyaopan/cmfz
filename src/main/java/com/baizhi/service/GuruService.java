package com.baizhi.service;

import com.baizhi.entity.Guru;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

public interface GuruService {

    public List<Guru> queryAll();

}
