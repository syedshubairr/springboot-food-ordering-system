package com.shah.foodwesbite.service;

import com.shah.foodwesbite.entity.User;

public interface UserService {
    public User findUserByJwtToken(String jwt) throws Exception;
    public User findUserByEmail(String Email) throws Exception;
}
