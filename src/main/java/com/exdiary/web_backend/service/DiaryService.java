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

        return result;
    }

    @Transactional
    public int deleteDiary(int diary_num) {
        try {
            // 다이어리에 해당하는 일기리스트들 먼저 삭제하기
            diaryMapper.deleteDiaryList(diary_num);
            int deleteResult = diaryMapper.deleteDiary(diary_num);
            if (deleteResult > 0) {
                return deleteResult;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


}