package com.exdiary.web_backend.service;

import com.exdiary.web_backend.dto.SecretDTO;
import com.exdiary.web_backend.mapper.SecretMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecretService {

    @Autowired
    private SecretMapper secretMapper;

    public int insertLoginSecret(SecretDTO secret){
        return secretMapper.insertLoginSecret(secret);
    }
}
