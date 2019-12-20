package com.exdiary.web_backend.controller;


import com.exdiary.web_backend.dto.DiaryListDTO;
import com.exdiary.web_backend.service.DiaryListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/diaryList/*")
public class DiaryListController {

    @Autowired
    DiaryListService diaryListService;

    /*
     * @method Name : getDiaryList()
     * @date * @author :2019.12.18 : 권담비
     * @description : 일기장에 해당하는 일기 리스트 가져오기
     */
    @PostMapping(value = "/getDiaryList")
    public List<DiaryListDTO> getDiaryList(@RequestBody DiaryListDTO list) {
        System.out.println("DiaryListController : " + list);

        List<DiaryListDTO> diaryList = diaryListService.getDiaryList(list.getDiary_num(), list.getEmail());

        return diaryList;
    }

    /*
     * @method Name : insertContents()
     * @date * @author :2019.12.18 : 권담비
     * @description : 일기내용 저장하기
     */
    @PostMapping(value = "/insertContents")
    public int insertContents(@RequestBody DiaryListDTO list){
        return diaryListService.insertContents(list);
    }

    /*
     * @method Name : getDiaryContent()
     * @date * @author :2019.12.19 : 권담비
     * @description : 일기내용 가져오기
     */
    @PostMapping(value = "/getDiaryContent")
    public List<DiaryListDTO> getDiaryContent(@RequestBody DiaryListDTO list){
        return diaryListService.getDiaryContent(list.getDiary_num(), list.getPage_num());
    }

}
