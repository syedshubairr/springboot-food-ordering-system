package com.shah.foodwesbite.response;

import com.shah.foodwesbite.entity.USER_ROLE;
import lombok.Data;

@Data
public class AuthResponse {
    private String jwt;
    private String message;
    private USER_ROLE role;
}
