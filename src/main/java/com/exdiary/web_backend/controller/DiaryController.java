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
 다이어리 관련 컨트롤러
 ********************/

@RestController
@RequestMapping(value = "/diary/*")
public class DiaryController {

    @Autowired
    private DiaryService diaryService;

    /*
     * @method Name : getDiary()
     * @date * @author :2019.12.11 : 권담비
     * @description : 다이어리 리스트 가져오기
     */
    @PostMapping(value = "getDiary")
    public List<DiaryDTO> getDiary(@RequestBody DiaryUserListDTO user) {
        System.out.println("user: " + user);
        List<DiaryDTO> diary = diaryService.getDiary(user.getEmail());
        System.out.println("diary: " + diary);
        return diary;
    }

    /*
     * @method Name : insertDiaryInfo()
     * @date * @author :2019.12.11 : 권담비
     * @description : 일기장 만들기
     */
    @PostMapping(value = "insertDiaryInfo")
    public int insertDiaryInfo(@RequestBody DiaryDTO diary) {
        return diaryService.insertDiaryInfo(diary);
    }

    /*
     * @method Name : deleteDiary()
     * @date * @author :2019.12.21 : 권담비
     * @description : 다이어리 삭제하
     */
    @PostMapping(value = "deleteDiary")
    public int deleteDiary(@RequestBody DiaryDTO diary) {
        return diaryService.deleteDiary(diary.getDiary_num());
    }

    /*
     * @method Name : updateDiaryInfo()
     * @date * @author :2019.12.23 : 권담비
     * @description : 다이어리 수정하기
     */
    @PostMapping(value = "updateDiaryInfo")
    public int updateDiaryInfo(@RequestBody DiaryDTO diary) {
        return diaryService.updateDiaryInfo(diary.getDiary_num(), diary.getDiary_title(), diary.getExplanation());
    }
}
