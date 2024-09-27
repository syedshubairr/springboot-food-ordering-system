package com.shah.foodwebsite.service;

import com.shah.foodwebsite.dto.request.CreateFoodRequest;
import com.shah.foodwebsite.entity.Category;
import com.shah.foodwebsite.entity.Food;
import com.shah.foodwebsite.entity.Restaurant;

import java.util.List;

public interface FoodService {
    public Food createFood(CreateFoodRequest request, Category category, Restaurant restaurant);

    public void deleteFood(Long foodId) throws Exception;

    public List<Food> getRestaurantsFood(Long restaurantId,
                                         boolean isVegetarian,
                                         boolean isNonVeg,
                                         boolean isSeasonal,
                                         String foodCategory);

    public List<Food> searchFood(String keyword);

    public Food findFoodById(Long foodId) throws Exception;

    public Food updateAvailabilityStatus(Long foodId) throws Exception;
}
