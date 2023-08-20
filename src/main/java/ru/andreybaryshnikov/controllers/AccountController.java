package ru.andreybaryshnikov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccountController {
    @RequestMapping("/login")
    String login() {
        return "login";
    }
    @RequestMapping("/logout")
    String logout() {
        return "logout";
    }
}
