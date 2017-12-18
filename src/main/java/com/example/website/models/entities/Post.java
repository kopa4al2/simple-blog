package com.example.website.models.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "post")
@DiscriminatorValue("post")
public class Post extends Content {

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "post")
    private Set<Comment> comments;

    public Post() {
        super();
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Comment> getComments() {
        if(this.comments == null)
            return new HashSet<>();
        return this.comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
