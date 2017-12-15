package com.example.website.controllers;

import com.example.website.models.bindingModels.UserDto;
import com.example.website.models.entities.Role;
import com.example.website.models.entities.User;
import com.example.website.services.RoleService;
import com.example.website.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

@Controller
public class UserController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostMapping("/register")
    public String register(UserDto userDto) {
        if (!userDto.getPassword().equals(userDto.getConfirmPassword()))
            return "redirect:/register";
        else {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            User u = new User();
            u.setEmail(userDto.getEmail());
            u.setPassword(encoder.encode(userDto.getPassword()));
            u.setFirstName(userDto.getFirstName());
            u.setLastName(userDto.getLastName());
            Role defaultUserRole = this.roleService.findByName("ROLE_USER");
            u.getRoles().add(defaultUserRole);
            try {
                this.userService.save(u);
            } catch (ConstraintViolationException e) {
                return e.getMessage();
            }
        }

        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response, @ModelAttribute UserDto loginForm,
                        BindingResult result) {
        try {
            request.login(loginForm.getEmail(), loginForm.getPassword());
            RequestCache requestCache = new HttpSessionRequestCache();
            SavedRequest savedRequest = requestCache.getRequest(request, response);
            if (savedRequest != null) {
                return "redirect:" + savedRequest.getRedirectUrl();
            } else {
                return "redirect:/";
            }

        } catch (ServletException authenticationFailed) {
            result.rejectValue(null, "authentication.failed");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null)
            new SecurityContextLogoutHandler().logout(request, response, auth);
        return "redirect:/login?logout";
    }

    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public String showProfile(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        User u = this.userService.findByEmail(userDetails.getUsername());
        model.addAttribute("view", "users/profile");
        model.addAttribute("user", u);
        return "layout";
    }
}
