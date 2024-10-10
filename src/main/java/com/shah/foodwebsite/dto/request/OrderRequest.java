package com.shah.foodwebsite.dto.request;

import com.shah.foodwebsite.entity.Address;
import lombok.Data;

@Data
public class OrderRequest {
    private Long restaurantId;
    private Address deliveryAddress;
}
