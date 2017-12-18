package com.example.website.services.services;

import com.example.website.models.entities.Role;

import java.util.Set;

public interface RoleService {
    Role findByName(String role_user);

    void save(Role role);

}
