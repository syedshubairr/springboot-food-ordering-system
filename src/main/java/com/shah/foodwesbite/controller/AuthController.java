package com.shah.foodwesbite.controller;

import com.shah.foodwesbite.config.JwtProvider;
import com.shah.foodwesbite.entity.User;
import com.shah.foodwesbite.repository.CartRepository;
import com.shah.foodwesbite.repository.UserRepository;
import com.shah.foodwesbite.response.AuthResponse;
import com.shah.foodwesbite.service.CustomerUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth") // All the methods endpoints with start with auth keyword.
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private CustomerUserDetailService customerUserDetailService;
    @Autowired
    private CartRepository cartRepository;

    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) {
        return null;
    }
}
