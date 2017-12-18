package com.example.website.repositories;

import com.example.website.models.entities.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content, Integer> {

    @Query(value = "SELECT c FROM Content c WHERE post_id IS NOT NULL ORDER BY creation_date desc ")
    List<Content> findAllComments();

    @Query(value = "SELECT c FROM Content c WHERE post_id IS NULL ORDER BY creation_date desc ")
    List<Content> findAllPosts();

}
