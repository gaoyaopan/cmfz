package com.baizhi.controller;


import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/chapter")
public class ChapterController {

    @Autowired
    ChapterService chapterService;

    @RequestMapping("/selectByPage")
    public List<Chapter> selectByPage(String id)throws  Exception{
        List<Chapter> chapters = chapterService.selectByPage(id);
        return chapters;
    }

    @RequestMapping("/edit")
    public String edit(Chapter chapter, String oper, String albumId, String id){
        HashMap hashMap = new HashMap();
        // 添加逻辑
        if (oper.equals("add")) {
            String chapterId = UUID.randomUUID().toString();
            chapter.setId(chapterId);
            chapter.setAlbumId(albumId);
            chapterService.insert(chapter);
            // 修改逻辑
        } else if (oper.equals("edit")) {
            chapterService.update(chapter);
            // 删除
        } else {
            chapterService.delete(id);
        }
        return chapter.getId();
    }

    @RequestMapping("/uploadChapter")
    public void uploadChapter(MultipartFile url, String id, HttpServletRequest request) throws ReadOnlyFileException, TagException, InvalidAudioFrameException, CannotReadException {
        chapterService.ChapterUpload(url,id,request);
    }

    @RequestMapping("/audioDownload")
    public void audioDownload(String audioName, HttpSession session, HttpServletResponse response) throws IOException {
        System.out.println(audioName);
        chapterService.audioDownload(audioName,session,response);
    }

}
