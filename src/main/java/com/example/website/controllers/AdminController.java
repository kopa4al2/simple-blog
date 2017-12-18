package com.example.website.controllers;

import com.example.website.models.bindingModels.PostDto;
import com.example.website.models.entities.*;
import com.example.website.services.services.ContentService;
import com.example.website.services.services.RoleService;
import com.example.website.services.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private ContentService contentServie;
    @Autowired
    private RoleService roleService;

    @GetMapping("/list_users")
    public String listUsers(Model model) {
        List<User> allUsers = this.userService.findAllUsers();
        allUsers.remove(this.getLoggedInUser());
        model.addAttribute("users", allUsers);
        model.addAttribute("view", "/admin/all_users");
        return "layout";
    }

    @PostMapping("/post/create")
    @PreAuthorize("isAuthenticated()")
    public String createPost(PostDto content) {
        Post post = new Post();
        User u = getLoggedInUser();
        u.getContent().add(post);
        post.setContent(content.getContent());
        post.setTitle(content.getTitle());
        post.setWroteBy(u);
        post.setCreationDate(new Date());
        this.contentServie.save(post);
        return "redirect:/";
    }

    @GetMapping("/user/delete/{id}")
    @PreAuthorize("isAuthenticated()")
    public String deleteUser(@PathVariable Integer id) {
        User user = this.userService.findById(id);
        user.getRoles().clear();
        user.getLikes().forEach(cont -> cont.getLikedBy().remove(user));

        this.contentServie.deleteAll(user.getContent());
        this.userService.deleteUser(id);
        return "redirect:/list_users";
    }

    @GetMapping("/content/delete/{id}")
    @PreAuthorize("isAuthenticated()")
    public String deleteContent(@PathVariable Integer id) {
        Content c = this.contentServie.findById(id);
        User u = this.userService.findById(c.getWroteBy().getId());
        u.getContent().remove(c);
        c.getLikedBy().clear();
        if(c.getDiscriminatorValue().equals("post")) {
            Post p = (Post)c;
            for (Comment comment : p.getComments()) {
                comment.getWroteBy().getContent().remove(comment);
                comment.setPost(null);
                this.contentServie.deleteOne(comment.getId());
            }
            this.contentServie.deleteOne(p.getId());
        }
        else if(c.getDiscriminatorValue().equals("comment")) {
            Comment comment = (Comment) c;
            Post p = (Post) this.contentServie.findById(comment.getPost().getId());
            p.getComments().remove(comment);
            this.contentServie.deleteOne(c.getId());
        }
        return "redirect:/";
    }

    @GetMapping("/user/make_admin/{id}")
    @PreAuthorize("isAuthenticated()")
    public String makeUserAdmin(@PathVariable Integer id) {
        User u = this.userService.findById(id);
        Role r = this.roleService.findByName("ROLE_ADMIN");
        u.getRoles().add(r);
        this.userService.save(u);
        return "redirect:/list_users";
    }

    @GetMapping("/user/unmake_admin/{id}")
    @PreAuthorize("isAuthenticated()")
    public String unmakeUserAdmin(@PathVariable Integer id) {
        User u = this.userService.findById(id);
        Role r = this.roleService.findByName("ROLE_USER");
        Role rAdmin = this.roleService.findByName("ROLE_ADMIN");
        u.getRoles().remove(rAdmin);
        u.getRoles().add(r);
        this.userService.save(u);
        return "redirect:/list_users";
    }


    private User getLoggedInUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return this.userService.findByEmail(userDetails.getUsername());
    }
}
