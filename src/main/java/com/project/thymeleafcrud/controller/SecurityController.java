package com.project.thymeleafcrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping("/LoginPage")
    public String showLoginPage() {
        return "security/login";
    }

    @GetMapping("/AccessDeniedPage")
    public String showAccessDeniedPage() {
        return "security/access-denied";
    }
}
