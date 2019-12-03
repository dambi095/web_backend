package com.exdiary.web_backend.service;

import com.exdiary.web_backend.dto.UserDTO;
import com.exdiary.web_backend.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public int insertUser(UserDTO user){
        System.out.println("*-*-*-*-* UserService ");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return mapper.insetUser(user);
    }


    // 유저 정보 불러옴
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO user = mapper.findUserByEmail(username);

        //권한설정
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new User(user.getEmail(), user.getPassword(), authorities);
    }

    /*
    public boolean logIn(UserDTO user) {

        //UserDetails :: Spring Security에서 사용자의 정보를 담는 인터페이스
        UserDetails userDetails = this.loadUserByUsername(user.getEmail());
        return passwordEncoder.matches(user.getPassword(), userDetails.getPassword());
    }
    */
}
