package com.exdiary.web_backend.controller;

import com.exdiary.web_backend.dto.AuthDTO;
import com.exdiary.web_backend.dto.UserDTO;
import com.exdiary.web_backend.security.JwtTokenProvider;
import com.exdiary.web_backend.service.UserService;
import com.exdiary.web_backend.utils.TempKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
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
    public int registerCheck(@RequestBody UserDTO user) {
        System.out.println("*-*-*-* UserController registerCheck user: " + user);

        int result = service.registerCheck(user.getEmail());

        return result;
    }

    /*
     * @method Name : updateUserInfo()
     * @date * @author :2019.12.04 : 권담비
     * @description : 유저정보 업데이트 하기
     */
    @RequestMapping(value = "updateUserInfo", method = RequestMethod.POST)
    public int updateUserInfo(@RequestBody UserDTO user) {
        return service.updateUserInfo(user);
    }


    /*
     * @method Name : sendEmail()
     * @date * @author :2019.12.09 : 권담비
     * @description : 이메일 인증을 위해 인증코드 값 저장하기
     */
    @RequestMapping(value = "sendEmail", method = RequestMethod.POST)
    public Map<String, String> sendEmail(@RequestBody UserDTO user) {
        // 인증키 생성
        String authKey = new TempKey().getKey(20, false);

        int result = service.sendEmail(user, authKey);

        Map<String, String> map = new HashMap<>();

        if (result == 1) {
            map.put("authKey", authKey);
        } else {
            map.put("authKey", "none");
        }

        return map;
    }


    /*
     * @method Name : confirmLoginSecret()
     * @date * @author :2019.12.09 : 권담비
     * @description : 이메일 인증코드 값 확인하기
     */
    @RequestMapping(value="confirmLoginSecret", method = RequestMethod.POST)
    public int confirmLoginSecret(@RequestBody AuthDTO auth){
        return service.confirmLoginSecret(auth.getEmail(),auth.getAuth_key());
    }

    /*
     * @method Name : logIn()
     * @date * @author :2019.12.09 : 권담비
     * @description : 로그인 하기
     */
    @RequestMapping(value = "logIn", method = RequestMethod.POST)
    public Map<String, Object> logIn(@RequestBody UserDTO user){

        Map<String, Object> map = new HashMap<>();

        if(service.logIn(user)){
            String token = jwtTokenProvider.createToken(user.getEmail());

            UserDTO dbUser = service.getUserInfo(user.getEmail());

            // Map<String, Object> map = new HashMap<>();

            dbUser.setPassword("");

            dbUser.setToken(token);

            map.put("user", dbUser);

            return map;
        }

        map.put("Failed login...",null);
        return map;
    }

}
