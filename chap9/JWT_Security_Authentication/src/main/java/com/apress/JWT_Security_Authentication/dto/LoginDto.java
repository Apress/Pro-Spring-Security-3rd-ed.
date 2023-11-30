package com.apress.JWT_Security_Authentication.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginDto {


    private String email ;
    private String password ;
}
