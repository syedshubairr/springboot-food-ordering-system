package com.shah.foodwebsite.service;

import com.shah.foodwebsite.entity.User;

public interface UserService {
    public User findUserByJwtToken(String jwt) throws Exception;
    public User findUserByEmail(String Email) throws Exception;
}
