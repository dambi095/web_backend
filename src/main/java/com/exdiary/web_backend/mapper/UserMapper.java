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

    // 유저 정보 업데이트 하기
    public int updateUserInfo(UserDTO user);

    // 권한 테이블에 이메일, 인증번호 저장하기
    public int setAuthKey(String auth_key, String email);

    // 인증번호 비교하기
    public int confirmLoginSecret(String email, String auth_key);

    public int sendEmailCheck(String email);

    public int updateLoginAuth(String email, String auth_key);

    public int deleteLoginAuth(String email);

}
