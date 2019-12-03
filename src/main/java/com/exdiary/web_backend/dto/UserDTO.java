package com.exdiary.web_backend.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {
    // 유저 DTO
    private int user_num;
    private String email;
    private String password;
    private Date joindate;
    private String phone;
    private String profile_img;
    private String nickname;
    private String gender;
    private String token;

}
