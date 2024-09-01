package com.shah.foodwebsite.service.Impl;

import com.shah.foodwebsite.config.JwtProvider;
import com.shah.foodwebsite.entity.User;
import com.shah.foodwebsite.repository.UserRepository;
import com.shah.foodwebsite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User findUserByJwtToken(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromJwt(jwt);
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new Exception("User not found");
        }
        return user;
    }

    @Override
    public User findUserByEmail(String Email) throws Exception {
        User user = userRepository.findByEmail(Email);
        if (user == null) {
            throw new Exception("User not found");
        }
        return user;
    }
}
