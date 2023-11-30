package com.apress.JWT_Security_Authentication.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterDto implements Serializable {

    String firstName ;
    String lastName ;
    String email;
    String password ;
    String userRole ;
}
