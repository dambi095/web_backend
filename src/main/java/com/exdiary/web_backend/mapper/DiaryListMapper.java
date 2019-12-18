package com.exdiary.web_backend.mapper;

import com.exdiary.web_backend.dto.DiaryListDTO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface DiaryListMapper {

    // 일기장에 해당하는 일기 리스트 가져오기
    public List<DiaryListDTO> getDiaryList(int diary_num, String email);

    // 일기 내용 저장하
    public int insertContents(DiaryListDTO list);

}
