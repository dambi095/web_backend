package com.exdiary.web_backend.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DiaryListDTO {
    // 일기 DTO
    private int page_num;
    private int diary_num;
    private String title;
    private String contents;
    private String nickname;
    private Date write_date;
    private String email;
    private String image;
}