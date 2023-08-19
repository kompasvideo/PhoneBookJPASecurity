package ru.andreybaryshnikov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.andreybaryshnikov.services.PhoneBookService;

@Controller
public class AccountController {
    private PhoneBookService phoneBookService;

    public AccountController(PhoneBookService phoneBookService) {
        this.phoneBookService = phoneBookService;
    }
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("phoneBook", phoneBookService.getNewPhoneBook());
        return "Account/login";
    }
}
