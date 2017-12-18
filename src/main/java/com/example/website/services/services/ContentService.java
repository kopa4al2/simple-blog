package com.example.website.services.services;

import com.example.website.models.entities.Content;
import com.example.website.models.entities.Post;

import java.util.List;
import java.util.Set;

public interface ContentService {
    void save(Content post);

    List<Content> findAllPosts();

    Content findById(Integer id);

    void deleteAll(Set<Content> likes);

    void deleteOne(Integer id);
}
