package com.exdiary.web_backend.service;

import com.exdiary.web_backend.dto.DiaryListDTO;
import com.exdiary.web_backend.mapper.DiaryListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DiaryListService {

    @Autowired
    DiaryListMapper diaryListMapper;


    public List<DiaryListDTO> getDiaryList(int diary_num, String email) {

        List<DiaryListDTO> diaryList = diaryListMapper.getDiaryList(diary_num, email);

        return diaryList;
    }

    @Transactional
    public int insertContents(DiaryListDTO list) {
        int result;
        try {
            diaryListMapper.insertContents(list);
            result = list.getPage_num(); // insert 후 증가된 pageSeq 값 가져오기
        }catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return result;
    }

    public List<DiaryListDTO> getDiaryContent(int diary_num, int page_num){
        return diaryListMapper.getDiaryContent(diary_num, page_num);
    }

    public int deleteDiaryContents(int diary_num, int page_num){
        return diaryListMapper.deleteDiaryContents(diary_num, page_num);
    }
}