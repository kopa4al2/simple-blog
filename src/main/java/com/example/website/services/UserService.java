package com.example.website.services;

import com.example.website.models.User;

import java.util.List;

public interface UserService {

    List<User> findAllUsers();

    void save(User u);
}
