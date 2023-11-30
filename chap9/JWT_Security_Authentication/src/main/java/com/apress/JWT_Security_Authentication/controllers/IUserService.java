package com.apress.JWT_Security_Authentication.controllers;


import com.apress.JWT_Security_Authentication.dto.LoginDto;
import com.apress.JWT_Security_Authentication.dto.RegisterDto;
import com.apress.JWT_Security_Authentication.models.User;
import com.apress.JWT_Security_Authentication.models.Role;


import org.springframework.http.ResponseEntity;


public interface IUserService {

   String authenticate(LoginDto loginDto);
   ResponseEntity<?> register (RegisterDto registerDto);
   Role saveRole(Role role);

   User saverUser (User user) ;
}
