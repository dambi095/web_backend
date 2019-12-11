package com.exdiary.web_backend.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DiaryDTO {
    // 일기장 DTO
    private int diary_num;
    private String email;
    private String diary_type;
    private String diary_title;
    private String explanation;
    private Date create_date;
    private Date deadline;
}
