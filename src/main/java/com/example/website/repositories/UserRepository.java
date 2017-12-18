package com.example.website.repositories;

import com.example.website.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);

    @Modifying
    @Query(nativeQuery=true, value = "DELETE FROM users WHERE ID = ?1")
    public void deleteById(Integer id);
}
