package com.apress.pss01_security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UserController {

    @GetMapping("/")
    public String homePage() { return "welcome";
    }

    @GetMapping("/welcome")
    public String welcomePage() {
        return "welcome";
    }

    @GetMapping ("/authenticated")
    public String AuthenticatedPage() {
        return "authenticated";
        }

    @GetMapping("/login")
    public String loginPage() { return "login"; }

    @GetMapping ("/logout")
    public String logoutPage() {
        return "redirect:/welcome";
    }

}
