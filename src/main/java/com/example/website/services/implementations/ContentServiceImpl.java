package com.example.website.services.implementations;

import com.example.website.models.entities.Content;
import com.example.website.models.entities.Post;
import com.example.website.repositories.ContentRepository;
import com.example.website.services.services.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentRepository contentRepository;

    @Override
    public void save(Content post) {
        this.contentRepository.save(post);
    }

    @Override
    public List<Content> findAllPosts() {
        return this.contentRepository.findAllPosts();
    }

    @Override
    public Content findById(Integer id) {
        return this.contentRepository.findOne(id);
    }

    @Override
    public void deleteAll(Set<Content> likes) {
        this.contentRepository.delete(likes);
    }

    @Override
    public void deleteOne(Integer id) {
        this.contentRepository.delete(id);
    }
}
