package com.exdiary.web_backend.controller;


import com.exdiary.web_backend.dto.DiaryDTO;
import com.exdiary.web_backend.dto.DiaryUserListDTO;
import com.exdiary.web_backend.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/********************
 일기장 관련 컨트롤러
 ********************/

@RestController
@RequestMapping(value = "/diary/*")
public class DiaryController {

    @Autowired
    private DiaryService diaryService;

    /*
     * @method Name : getDiary()
     * @date * @author :2019.12.11 : 권담비
     * @description : 일기장 리스트 가져오기
     */

    @PostMapping(value = "getDiary")
    public List<DiaryDTO> getDiary(@RequestBody DiaryUserListDTO user){
        List<DiaryDTO> diary = diaryService.getDiary(user.getEmail());
        return diary;
    }
}