package com.exdiary.web_backend.mapper;

import com.exdiary.web_backend.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    // 유저 회원가입 하기
    public int insetUser(UserDTO user);

}
