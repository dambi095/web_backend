package com.exdiary.web_backend.mapper;

import com.exdiary.web_backend.dto.SecretDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SecretMapper {
    public int insertLoginSecret(SecretDTO secret);
}
