package com.baizhi.service;

import com.baizhi.entity.Chapter;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ChapterService {

    public String insert(Chapter chapter);

    public void delete(String id);

    public void update(Chapter chapter);

    public List<Chapter> selectByPage(String id);

    //上传
    public void ChapterUpload(MultipartFile url, String id, HttpServletRequest request) throws TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException;

    //下载
    public void audioDownload(String audioName, HttpSession session, HttpServletResponse response) throws IOException;
}
