package com.shah.foodwebsite.dto.request;

import com.shah.foodwebsite.entity.Category;
import com.shah.foodwebsite.entity.IngredientsItem;
import lombok.Data;

import java.util.List;

@Data

public class CreateFoodRequest {
    private String name;
    private String description;
    private Long price;
    private Category category;
    private List<String> images;
    private Long restaurantId;
    private boolean vegetarian;
    private boolean seasonal;
    private List<IngredientsItem> ingredients   ;
}
