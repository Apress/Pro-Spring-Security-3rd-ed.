package com.apress.JWT_Security_Authentication.presentation;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminRestController {

    @GetMapping("/hello")
    public String sayHello ()
    { return "Welcome you are authenticated as Admin!" ;}


}
