package com.example.education_3_1_1.repository;

import com.example.education_3_1_1.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByFirstName (String name);
}
