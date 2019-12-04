package com.exdiary.web_backend.controller;

import com.exdiary.web_backend.dto.UserDTO;
import com.exdiary.web_backend.security.JwtTokenProvider;
import com.exdiary.web_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;


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

        int result;

        try {
            user.setJoindate(new Date(System.currentTimeMillis()));

            if (user.getToken() == null) user.setToken("");

            result = service.insertUser(user);

        } catch (Exception e) {
            result = 0;
        }

        System.out.println("*-*-*-* UserController insertUser result: " + result);

        return result;
    }

    /*
     * @method Name : getUserInfo()
     * @date * @author :2019.12.04 : 권담비
     * @description : 유저 정보 가져오기
     */
    @RequestMapping(value = "getUserInfo", method = RequestMethod.POST)
    public UserDTO getUserInfo(@RequestBody UserDTO email) {
        System.out.println("*-*-*-* UserController getUserInfo user: " + email);

        UserDTO userInfo = service.getUserInfo(email.getEmail());

        return userInfo;
    }


    /*
     * @method Name : registerCheck()
     * @date * @author :2019.12.04 : 권담비
     * @description : 가입된 유저인지 체크하기
     */
    @RequestMapping(value = "registerCheck", method = RequestMethod.POST)
    public int registerCheck(@RequestBody UserDTO email) {
        System.out.println("*-*-*-* UserController registerCheck user: " + email);

        int result = service.registerCheck(email.getEmail());

        return result;
    }
}
