package com.exdiary.web_backend.mapper;

import com.exdiary.web_backend.dto.DiaryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DiaryMapper {
    // 다이어리 가져오기
    public List<DiaryDTO> getDiary(String email);
}
