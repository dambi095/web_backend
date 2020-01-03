package com.exdiary.web_backend.mapper;

import com.exdiary.web_backend.dto.DiaryListDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface DiaryListMapper {

    // 일기장에 해당하는 일기 리스트 가져오기
    public List<DiaryListDTO> getDiaryList(int diary_num, String write_date);

    // 일기 내용 저장하기
    public int insertContents(DiaryListDTO list);

    // 일기 내용 가져오기
    public List<DiaryListDTO> getDiaryContent(int diary_num, int page_num);

    // 일기 삭제하기
    public int deleteDiaryContents(int diary_num, int page_num);

    // 일기 내용 수정하기
    public int updateDiaryContents(DiaryListDTO list);
}
