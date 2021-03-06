package com.exdiary.web_backend.service;

import com.exdiary.web_backend.dto.DiaryDTO;
import com.exdiary.web_backend.dto.UserDTO;
import com.exdiary.web_backend.mapper.UserMapper;
import com.exdiary.web_backend.utils.TempKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    JavaMailSender emailSender;

    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.emailSender = javaMailSender;
    }

    // 회원가입하기
    @Transactional
    public int insertUser(UserDTO user) throws Exception {
        try {

            user.setPassword(passwordEncoder.encode(user.getPassword()));

            int result = mapper.insetUser(user);

            if(result > 0) {
                System.out.println("ssss"+mapper.deleteLoginAuth(user.getEmail()));
                mapper.deleteLoginAuth(user.getEmail());
                return 1;
            }
        } catch (Exception es) {
            es.printStackTrace();
        }
        return 0;
    }

    // 가입된 유저인지 체크하기
    public int registerCheck(String email) {
        return mapper.registerCheck(email);
    }

    // 유저정보 가져온 후 권한설정 하기
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDTO user = mapper.findUserByEmail(email);

        //권한설정
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new User(user.getEmail(), user.getPassword(), authorities);
    }

    // 유저 정보 가져오기
    public UserDTO getUserInfo(String email) {
        UserDTO userInfo = mapper.findUserByEmail(email);
        return userInfo;
    }

    // 유저 정보 업데이트 하기
    public int updateUserInfo(UserDTO user) {
        return mapper.updateUserInfo(user);
    }

    // 이메일 인증을 위해 인증코드 값 저장하기
    @Transactional
    public int sendEmail(UserDTO user, String authKey) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();

            int result;

            // 인증 이메일을 이미 보낸 이메일인지 확인
            int isExist = mapper.sendEmailCheck(user.getEmail());

            if (isExist > 0) {
                // 존재한다면 이메일 재전송
                result = mapper.updateLoginAuth(user.getEmail(), authKey);
            } else {
                // 존재하지 않는다면 새로 저장
                result = mapper.setAuthKey(authKey, user.getEmail());
            }

            if (result == 1) {
                // 메일 전송 폼 데이터 set
                message.setFrom("test@dambi.co.kr"); // 보내는 사람

                // 받는 사람
                message.setTo(user.getEmail());

                //제목
                message.setSubject("회원가입을 위한 이메일 인증코드입니다. ");

                //내용
                message.setText(
                        new StringBuffer()
                                .append("회원 가입을 위한 메일인증 입니다 ")
                                .append("'" + authKey + "'").append(" 코드를 입력해주세요 :D").toString()
                );

                // 메일 전송
                emailSender.send(message);

                return 1;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // 이메일 인증코드 값 확인하기
    @Transactional
    public int confirmLoginSecret(String email, String authKey) {
        try {
            return mapper.confirmLoginSecret(email, authKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // 로그인 하기
    public boolean logIn(UserDTO user){
        //UserDetails :: Spring Security에서 사용자의 정보를 담는 인터페이스
        UserDetails userDetails = this.loadUserByUsername(user.getEmail());
        return passwordEncoder.matches(user.getPassword(), userDetails.getPassword());
    }

    // mysql에서 현재값 가져오기
    public String getCurDate() {
        return mapper.getCurDate();
    }

}
