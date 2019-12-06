package com.exdiary.web_backend.controller;

import com.exdiary.web_backend.dto.SecretDTO;
import com.exdiary.web_backend.service.SecretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/secret/*")
public class SecretController {

    @Autowired
    private SecretService secretService;

    @RequestMapping(value = "insertLoginSecret")
    public int insertLoginSecret (SecretDTO secret) {
        System.out.println(" Secret : " + secret);
        return secretService.insertLoginSecret(secret);
    }

}
