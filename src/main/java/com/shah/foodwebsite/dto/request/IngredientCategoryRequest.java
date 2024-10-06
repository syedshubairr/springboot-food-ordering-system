package com.shah.foodwebsite.dto.request;

import lombok.Data;

@Data
public class IngredientCategoryRequest {
    private String name;
    private Long restaurantID;
}
