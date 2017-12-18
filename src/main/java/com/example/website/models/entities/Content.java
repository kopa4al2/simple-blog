package com.example.website.models.entities;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@Table(name = "content")
public abstract class Content {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "content")
    @Type(type = "text")
    private String content;

    @Column(name = "creation_date")
    private Date creationDate;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    private User wroteBy;

    @ManyToMany
    private Set<User> likedBy;

    public Content() {

    }


    @Transient
    public String getDiscriminatorValue(){
        DiscriminatorValue val = this.getClass().getAnnotation( DiscriminatorValue.class );

        return val == null ? null : val.value();
    }

    public boolean isLikedBy(User user) {
        if (user != null)
            return this.getLikedBy().contains(user);
        return false;
    }

    public String getSummary() {
        if (this.content.length() <= 50)
            return this.getContent();
        else
            return this.getContent().substring(0, 50) + "...";
    }

    public int getId() {
        if (this.id == null)
            return 0;
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

    public Set<User> getLikedBy() {
        if (this.likedBy == null)
            return new HashSet<>();
        return this.likedBy;
    }

    public void setLikedBy(Set<User> likedBy) {
        this.likedBy = likedBy;
    }

    public String getCreationDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm dd-MM-yyyy");
        String s = simpleDateFormat.format(this.creationDate);
        return s;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
