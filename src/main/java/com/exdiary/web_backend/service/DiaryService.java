package com.exdiary.web_backend.service;

import com.exdiary.web_backend.dto.DiaryDTO;
import com.exdiary.web_backend.mapper.DiaryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class DiaryService {

    @Autowired
    DiaryMapper diaryMapper;

    public List<DiaryDTO> getDiary(String email) {

        List<DiaryDTO> diaryList = diaryMapper.getDiary(email);

        return diaryList;
    }

    @Transactional
    public int insertDiaryInfo(@RequestBody DiaryDTO diary) {
        int result;
        try {
            diaryMapper.insertDiaryInfo(diary);
            result = diary.getDiary_num(); // insert 후 증가된 diary_num 값 가져오기
        } catch (Exception e) {
            e.printStackTrace();
            result = 0;
        }

        System.out.println("-------------------- result: " + result);

        return result;
    }
}