package com.shah.foodwebsite.controller;

import com.shah.foodwebsite.dto.request.IngredientCategoryRequest;
import com.shah.foodwebsite.dto.request.IngredientRequest;
import com.shah.foodwebsite.entity.IngredientCategory;
import com.shah.foodwebsite.entity.IngredientsItem;
import com.shah.foodwebsite.service.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientController {
    @Autowired
    private IngredientsService ingredientsService;

    @PostMapping("/category")
    public ResponseEntity<IngredientCategory> createIngredientCategory(
            @RequestBody IngredientCategoryRequest request) throws Exception {
        IngredientCategory item = ingredientsService.createIngredientCategory(request.getName(), request.getRestaurantID());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PostMapping()
    public ResponseEntity<IngredientsItem> createIngredientItem(
            @RequestBody IngredientRequest req) throws Exception {
        IngredientsItem item = ingredientsService
                .createIngredientItem(req.getRestaurantId(), req.getName(), req.getCategoryId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/stock")
    public ResponseEntity<IngredientsItem> updateIngredientStock(
            @PathVariable long id
    ) throws Exception {
        IngredientsItem item = ingredientsService.updateStock(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<IngredientsItem>> getRestaurantIngredients(
            @PathVariable long id
    ) throws Exception {
        List<IngredientsItem> item = ingredientsService.findRestaurantIngredients(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}/category")
    public ResponseEntity<List<IngredientCategory>> getRestaurantIngredientCategory(
            @PathVariable long id
    ) throws Exception {
        List<IngredientCategory> item = ingredientsService.findIngredientCategoryByRestaurantId(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }
}
