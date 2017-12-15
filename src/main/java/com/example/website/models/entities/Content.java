package com.example.website.models.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "content")
public class Content {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="content")
    private String content;

    @OneToOne
    private User wroteBy;

    @ManyToOne(targetEntity = User.class)
    private Set<User> likedBy;

    public Content() {

    }

    public int getId() {
        return this.id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getWroteBy() {
        return this.wroteBy;
    }

    public void setWroteBy(User wroteBy) {
        this.wroteBy = wroteBy;
    }

    public Set<User> gotLikedBy() {
        if(this.gotLikedBy() == null)
            return new HashSet<>();
        return this.likedBy;
    }

    public void setLikedBy(Set<User> likedBy) {
        this.likedBy = likedBy;
    }
}
