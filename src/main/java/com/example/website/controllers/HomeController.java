package com.example.website.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

//    @RequestMapping("/")
//    @ResponseBody
//    public String showHome() {
//        return "<div> Home </div>   <button>Buton</button";
//    }

    @RequestMapping("/login")
    @ResponseBody
    public String showLogin(){
        return "<form><input type=text/> <button>login</button>";
    }
}
