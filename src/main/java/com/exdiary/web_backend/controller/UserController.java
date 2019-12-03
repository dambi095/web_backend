package com.exdiary.web_backend.controller;

import com.exdiary.web_backend.security.JwtTokenProvider;
import com.exdiary.web_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user/*")
public class UserController {

}
