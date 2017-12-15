package com.example.website.controllers;

import com.example.website.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String showHome(Model model) {
        model.addAttribute("view", "index");
        return "layout";
    }

    @GetMapping("/register")
    public String showRegister(Model model) {
        model.addAttribute("view", "users/register");
        return "layout";
    }

    @GetMapping("/login")
    public String showLogin(Model model) {
        model.addAttribute("view", "users/login");
        return "layout";
    }



}
