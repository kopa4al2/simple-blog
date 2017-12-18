package com.example.website.services.implementations;

import com.example.website.models.entities.User;
import com.example.website.repositories.UserRepository;
import com.example.website.services.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public void save(User u) {
        this.userRepository.save(u);
    }

    @Override
    public User findByEmail(String username) {
        return this.userRepository.findByEmail(username);
    }

    @Override
    public User findById(Integer id) {
        return this.userRepository.findOne(id);
    }

    @Override
    @Transactional
    public void deleteUser(Integer userId) {
        this.userRepository.deleteById(userId);
    }

    @Override
    public int count() {
        return (int) this.userRepository.count();
    }
}
