package com.shah.foodwebsite.controller;

import com.shah.foodwebsite.entity.Food;
import com.shah.foodwebsite.entity.User;
import com.shah.foodwebsite.service.FoodService;
import com.shah.foodwebsite.service.RestaurantService;
import com.shah.foodwebsite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {
    @Autowired
    private FoodService foodService;
    @Autowired
    private UserService userService;
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(@RequestParam String name,
                                                 @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Food> food = foodService.searchFood(name);
        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Food>> getRestaurantFood(@RequestParam boolean vegetarian,
                                                        @RequestParam boolean nonVeg,
                                                        @RequestParam boolean seasonal,
                                                        @PathVariable Long restaurantId,
                                                        @RequestParam(required = false) String food_category,
                                                        @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Food> food = foodService.getRestaurantsFood(restaurantId, vegetarian, nonVeg, seasonal, food_category);
        return new ResponseEntity<>(food, HttpStatus.OK);
    }
}
