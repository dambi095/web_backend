package com.exdiary.web_backend.service;

import com.exdiary.web_backend.dto.DiaryDTO;
import com.exdiary.web_backend.mapper.DiaryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaryService {

    @Autowired
    private DiaryMapper diaryMapper;

    public List<DiaryDTO> getDiary(String email){

        List<DiaryDTO> diaryList = diaryMapper.getDiary(email);

        return diaryList;
    }
}
