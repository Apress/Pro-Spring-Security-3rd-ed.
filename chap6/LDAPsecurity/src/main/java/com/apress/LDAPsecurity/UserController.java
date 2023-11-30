package com.apress.LDAPsecurity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    @GetMapping("/")
    public String getLoginPage() {
        return "You have successfully logged in Using Spring Security 6 LDAP Authentication!";
    }

}
