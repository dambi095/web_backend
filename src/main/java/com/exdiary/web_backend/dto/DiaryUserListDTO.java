package com.exdiary.web_backend.dto;

import lombok.Data;

@Data
public class DiaryUserListDTO {
    private String write_yn;
    private String email;
    private int diary_num;
    // private int user_num;
    private int write_order;
}
