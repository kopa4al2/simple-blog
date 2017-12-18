package com.example.website.services.services;

import com.example.website.models.entities.User;

import java.util.List;

public interface UserService {

    List<User> findAllUsers();

    void save(User u);

    User findByEmail(String username);

    User findById(Integer id);

    void deleteUser(Integer user);

    int count();
}
