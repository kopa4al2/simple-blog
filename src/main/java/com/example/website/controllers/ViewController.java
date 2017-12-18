package com.example.website.controllers;

import com.example.website.models.entities.Content;
import com.example.website.models.entities.Post;
import com.example.website.models.entities.User;
import com.example.website.services.services.ContentService;
import com.example.website.services.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ViewController {

    @Autowired
    private UserService userService;
    @Autowired
    private ContentService contentService;

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String showHome(Model model) {
        model.addAttribute("view", "index");
        final List<Content> allOrderedByDate = this.contentService.findAllPosts();
        if (allOrderedByDate != null)
            model.addAttribute("posts", allOrderedByDate);
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

    @GetMapping("/post")
    public String showAllPost(Model model) {
        model.addAttribute("view", "/admin/create_post");
        return "layout";
    }

    @GetMapping("/post/{id}")
    @PreAuthorize("isAuthenticated()")
    public String showPost(HttpSession session, Model model, @PathVariable Integer id) {
        Post post = (Post)this.contentService.findById(id);
        User u = this.getLoggedInUser();
        model.addAttribute("view", "/posts/show_post");
        model.addAttribute(post);
        session.setAttribute("loggedUser", u);
        return "layout";
    }

    private User getLoggedInUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return this.userService.findByEmail(userDetails.getUsername());
    }
}
