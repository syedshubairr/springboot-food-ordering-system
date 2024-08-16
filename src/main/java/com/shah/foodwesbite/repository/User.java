package com.shah.foodwesbite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface User extends JpaRepository<User, Long> {
    public User findByEmail(String username);
}
