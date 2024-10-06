package com.shah.foodwebsite.service.Impl;

import com.shah.foodwebsite.entity.IngredientCategory;
import com.shah.foodwebsite.entity.IngredientsItem;
import com.shah.foodwebsite.entity.Restaurant;
import com.shah.foodwebsite.repository.IngredientCategoryRepository;
import com.shah.foodwebsite.repository.IngredientItemRepository;
import com.shah.foodwebsite.service.IngredientsService;
import com.shah.foodwebsite.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientsService {
    @Autowired
    private IngredientItemRepository ingredientItemRepository;
    @Autowired
    private IngredientCategoryRepository ingredientCategoryRepository;
    @Autowired
    private RestaurantService restaurantService;

    @Override
    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientCategory ingredientCategory = IngredientCategory.builder()
                .restaurant(restaurant)
                .name(name)
                .build();
        return ingredientCategoryRepository.save(ingredientCategory);
    }

    @Override
    public IngredientCategory findIngredientCategoryById(Long id) throws Exception {
        return ingredientCategoryRepository.findById(id)
                .orElseThrow(() -> new Exception("Ingredient Category not found."));
    }

    @Override
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception {
        restaurantService.findRestaurantById(id);
        return ingredientCategoryRepository.findByRestaurantId(id);
    }

    @Override
    public IngredientsItem createIngredientItem(Long restaurantId, String ingredientName, Long categoryId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientCategory category = findIngredientCategoryById(categoryId);
        IngredientsItem item = IngredientsItem.builder()
                .name(ingredientName)
                .restaurant(restaurant)
                .category(category)
                .build();
        IngredientsItem savedIngredient = ingredientItemRepository.save(item);
        category.getIngredients().add(savedIngredient);
        return savedIngredient;
    }

    @Override
    public List<IngredientsItem> findRestaurantIngredients(Long restaurantId) {
        return ingredientItemRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public IngredientsItem updateStock(Long id) throws Exception {
        IngredientsItem item = ingredientItemRepository.findById(id)
                .orElseThrow(() -> new Exception("Ingredient not found"));
        item.setInStock(!item.isInStock());
        return ingredientItemRepository.save(item);
    }
}
