package com.exdiary.web_backend.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AuthDTO {
    // 이메일 인증 관련 DTO
    private String email;
    private String auth_key;
    private Date send_date;
}
