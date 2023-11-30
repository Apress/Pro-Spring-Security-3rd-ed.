package com.apress.JWT_Security_Authentication.presentation;

import com.apress.JWT_Security_Authentication.controllers.IUserService;
import com.apress.JWT_Security_Authentication.dto.LoginDto;
import com.apress.JWT_Security_Authentication.dto.RegisterDto;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestController {

    private final IUserService iUserService ;

    @PostMapping("/register")
    public ResponseEntity<?> register (@RequestBody RegisterDto registerDto)
    { return  iUserService.register(registerDto);}


    @PostMapping("/authenticate")
    public String authenticate(@RequestBody LoginDto loginDto)
    { return  iUserService.authenticate(loginDto);}
}
