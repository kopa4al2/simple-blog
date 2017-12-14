package com.example.website.controllers;

import com.example.website.models.User;
import com.example.website.models.bindingModels.UserBindingModel;
import com.example.website.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String home(Model model) {
        model.addAttribute("view", "test/users");
        model.addAttribute("users", userService.findAllUsers());
        return "index";
    }

    @PostMapping("/")
    public String addUser(UserBindingModel userBindingModel) {
        User u = new User();
        u.setFirstName(userBindingModel.getFirstName());
        u.setLastName(userBindingModel.getLastName());
        this.userService.save(u);
        return "";
    }

    @ModelAttribute("users")
    public List<User> users() {
        return userService.findAllUsers();
    }

}
