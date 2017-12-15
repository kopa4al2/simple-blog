package com.example.website.services;

import com.example.website.models.entities.Role;

public interface RoleService {
    Role findByName(String role_user);
}
