package com.example.website.models.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    @NotNull
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    private String lastName;

    @NotNull
    @Column(name = "email", nullable = false, unique = true)
    @Pattern(regexp = "([a-zA-Z0-9]+)@[a-zA-Z0-9]+\\.[a-z]{1,4}",
            message = "Invalid email address please provide an email address like foo@foo.com")
    private String email;

    @NotNull
    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles")
    private Set<Role> roles;

    @ManyToMany(mappedBy = "likedBy")
    private Set<Content> likes;

    @OneToMany(targetEntity = Content.class)
    private Set<Content> content;

    public User() {
    }

    public boolean isAdmin() {
        Role r = new Role();
        r.setName("ROLE_ADMIN");
        return this.getRoles().contains(r);
    }


    public String getFullName() {
        return this.getFirstName() + " " + this.getLastName();
    }

    public Integer getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        if (this.roles == null)
            return new HashSet<>();
        return this.roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Content> getLikes() {
        if (this.likes == null)
            return new HashSet<>();
        return this.likes;
    }

    public void setLikes(Set<Content> likes) {
        this.likes = likes;
    }

    public Set<Content> getContent() {
        if (this.content == null)
            return new HashSet<>();
        return this.content;
    }

    public void setContent(Set<Content> content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
