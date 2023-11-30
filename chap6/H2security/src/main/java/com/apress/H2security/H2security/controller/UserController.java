package com.apress.H2security.H2security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UserController {

    @GetMapping("/")
    public String homePage() {
        return "welcome";
    }

    @GetMapping("/welcome")
    public String welcomePage() {
        return "welcome";
    }

    @GetMapping ("/authenticated")
    public String AuthenticatedPage() {
        return "authenticated";
    }

    @GetMapping ("/logout")
    public String logoutPage() {
        return "redirect:/welcome";
    }


}