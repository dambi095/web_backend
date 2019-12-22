package com.exdiary.web_backend.mapper;

import com.exdiary.web_backend.dto.DiaryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DiaryMapper {
    // 다이어리 가져오기
    public List<DiaryDTO> getDiary(String email);

    // 다이어리 만들기
    public int insertDiaryInfo(DiaryDTO diary);

    // 다이어리 삭제하기
    public int deleteDiary(int diary_num);

    // 다이어리에 해당하는 일기 리스트들 싹 다 삭제하기
    public int deleteDiaryList(int diary_num);

    // 다이어리 수정하기
    public int updateDiaryInfo(int diary_num, String diary_title, String explanation);
}
