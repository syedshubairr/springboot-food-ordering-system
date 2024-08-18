package com.shah.foodwesbite.service.Impl;

import com.shah.foodwesbite.config.JwtProvider;
import com.shah.foodwesbite.entity.User;
import com.shah.foodwesbite.repository.UserRepository;
import com.shah.foodwesbite.service.UserService;
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
