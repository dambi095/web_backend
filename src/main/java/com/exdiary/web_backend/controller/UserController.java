package com.exdiary.web_backend.controller;

import com.exdiary.web_backend.dto.UserDTO;
import com.exdiary.web_backend.security.JwtTokenProvider;
import com.exdiary.web_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


/**
 * 유저 관련 컨트롤러
 **/
@RestController
@RequestMapping(value = "/user/*")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    /*
     * @method Name : insertUser()
     * @date * @author :2019.12.03 : 권담비
     * @description : 유저 회원가입기 하기
     */
    @RequestMapping(value = "insertUser", method = RequestMethod.POST)
    public int insertUser(@RequestBody UserDTO user) {

        System.out.println("*-*-*-* UserController user: " + user);
        int result;

        try {
            user.setJoindate(new Date(System.currentTimeMillis()));

            if (user.getToken() == null) user.setToken("");

            result = service.insertUser(user);

        } catch (Exception e) {
            result = 0;
        }

        System.out.println("*-*-*-* UserController result: " + result);

        return result;
    }
}
