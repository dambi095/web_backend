package com.exdiary.web_backend.service;

import com.exdiary.web_backend.dto.DiaryListDTO;
import com.exdiary.web_backend.mapper.DiaryListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaryListService {

    @Autowired
    DiaryListMapper diaryListMapper;


    public List<DiaryListDTO> getDiaryList(int diary_num, String email) {

        List<DiaryListDTO> diaryList = diaryListMapper.getDiaryList(diary_num, email);

        return diaryList;
    }

    public int insertContents(DiaryListDTO list) {
        return diaryListMapper.insertContents(list);
    }
}