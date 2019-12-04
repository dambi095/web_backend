package com.exdiary.web_backend.mapper;

import com.exdiary.web_backend.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    // 유저 회원가입 하기
    public int insetUser(UserDTO user);

    // 가입된 유저인지 체크하기
    public int registerCheck(String email);

    // login 할 경우 email로 user 정보 가져오기
    public UserDTO findUserByEmail (String email);

}
