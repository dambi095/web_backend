package com.exdiary.web_backend.dto;

import lombok.Data;

@Data
public class SecretDTO {
    // 시크릿 코드 DTO
    private String email;
    private String login_secret ;
}
