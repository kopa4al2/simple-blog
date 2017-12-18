package com.example.website.models.entities;

import javax.persistence.*;

@Entity(name = "comment")
@DiscriminatorValue("comment")
public class Comment extends Content{

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Post post;

    public Comment() {
        super();
    }

    public Post getPost() {
        return this.post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
