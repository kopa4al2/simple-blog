package com.example.website.controllers;

import com.example.website.models.bindingModels.CommentDTO;
import com.example.website.models.entities.Comment;
import com.example.website.models.entities.Post;
import com.example.website.models.entities.User;
import com.example.website.services.services.ContentService;
import com.example.website.services.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Controller
public class ContentController {

    @Autowired
    private ContentService contentService;
    @Autowired
    private UserService userSerivce;


    @PostMapping("/create_comment/{id}")
    @PreAuthorize("isFullyAuthenticated()")
    public String createComment(CommentDTO dto, @PathVariable Integer id) {
        Post post = (Post) this.contentService.findById(id);
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User u = this.userSerivce.findByEmail(userDetails.getUsername());        Comment comment = new Comment();
        comment.setContent(dto.getContent());
        comment.setCreationDate(new Date());
        comment.setPost(post);
        comment.setWroteBy(u);
        post.getComments().add(comment);
        this.contentService.save(comment);
        return "redirect:/post/{id}";
    }
}
