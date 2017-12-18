package com.example.website.services.implementations;

import com.example.website.models.entities.Role;
import com.example.website.repositories.RoleRepository;
import com.example.website.services.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByName(String roleName) {
        Role r = this.roleRepository.findByName(roleName);
        if(r == null) {
            r = new Role();
            r.setName(roleName);
            this.roleRepository.save(r);
        }
        return r;
    }

    @Override
    public void save(Role role) {
        this.roleRepository.save(role);
    }

}
