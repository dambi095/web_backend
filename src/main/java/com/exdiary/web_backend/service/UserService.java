package com.exdiary.web_backend.service;

import com.exdiary.web_backend.dto.UserDTO;
import com.exdiary.web_backend.mapper.UserMapper;
import com.exdiary.web_backend.utils.TempKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
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
    public int insertUser(UserDTO user) throws Exception{
        try {

            System.out.println("*-*-*-*-* UserService insertUser");
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            mapper.insetUser(user);

            SimpleMailMessage message = new SimpleMailMessage();

            // 인증키 생성
            String authKey = new TempKey().getKey(40,false);

            System.out.println("auth Key :" + authKey + "user.getEmail :"  + user.getEmail());

            // 인증키 db 저장
            mapper.setAuthKey(authKey, user.getEmail());

            System.out.println("***********************");

            // 메일 전송 폼 데이터 set
            message.setFrom("test@dambi.co.kr"); // 보내는 사람

            // 받는 사람
            message.setTo(user.getEmail());

            //제목
            message.setSubject("회원가입을 위한 이메일 인증코드입니다. ");

            //내용
            message.setText(
                    new StringBuffer()
                            .append("<h1>메일인증</h1>").append("<a href='http://localhost:8088/user/emailConfirm?userEmail=")
                            .append(user.getEmail()).append("&memberAuthKey=")
                            .append(authKey).append("'target='_blank'>이메일 인증 확인 </a>").toString()
            );
            // 메일 전송
            emailSender.send(message);

            return 1;
        } catch (Exception es) {
            System.out.println("es " + es);
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
        System.out.println("*-*-*-*-* getUserInfo userInfo" + userInfo + "email: " + email);
        return userInfo;
    }

    // 유저 정보 업데이트 하기
    public int updateUserInfo(UserDTO user) {
        System.out.println("*-*-*-*-* getUserInfo updateUserInfo" + user);
        return mapper.updateUserInfo(user);
    }

}
