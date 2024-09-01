package com.shah.foodwebsite.service.Impl;

import com.shah.foodwebsite.dto.RestaurantDTO;
import com.shah.foodwebsite.dto.request.CreateRestaurantRequest;
import com.shah.foodwebsite.entity.Address;
import com.shah.foodwebsite.entity.Restaurant;
import com.shah.foodwebsite.entity.User;
import com.shah.foodwebsite.repository.AddressRepository;
import com.shah.foodwebsite.repository.RestaurantRepository;
import com.shah.foodwebsite.repository.UserRepository;
import com.shah.foodwebsite.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user) {
        Address address = addressRepository.save(req.getAddress());
        Restaurant restaurant = new Restaurant();
        restaurant.setAddress(address);
        restaurant.setContactInformation(req.getContactInformation());
        restaurant.setCuisineType(req.getCuisineType());
        restaurant.setDescription(req.getDescription());
        restaurant.setImages(req.getImages());
        restaurant.setName(req.getName());
        restaurant.setOpeningHours(req.getOpeningHours());
        restaurant.setRegistrationDate(LocalDateTime.now());
        restaurant.setOwner(user);
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception {
        // TODO: Change the logic of this function, change restaurant to updatedRestaurant in every conditions.
        Restaurant restaurant = findRestaurantById(restaurantId);
        if (!restaurant.getCuisineType().isEmpty()) {
            restaurant.setCuisineType(updatedRestaurant.getCuisineType());
        }
        if (!restaurant.getDescription().isEmpty()) {
            restaurant.setDescription(updatedRestaurant.getDescription());
        }
        if (!restaurant.getName().isEmpty()) {
            restaurant.setName(updatedRestaurant.getName());
        }
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void DeleteRestaurant(Long restaurantId) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);
        restaurantRepository.delete(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurants() {
        return List.of();
    }

    @Override
    public Restaurant findRestaurantById(Long id) throws Exception {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if (restaurant.isEmpty()) {
            throw new Exception("Restaurants not found for id " + id);
        }
        return restaurant.get();
    }

    @Override
    public Restaurant getRestaurantByUserId(Long userId) throws Exception {
        Restaurant restaurant = restaurantRepository.findByOwnerId(userId);
        if (restaurant == null) {
            throw new Exception("Restaurants not found for user " + userId);
        }
        return restaurant;
    }

    @Override
    public RestaurantDTO addToFavorites(Long restaurantId, User user) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);
        RestaurantDTO dto = RestaurantDTO.builder()
                .description(restaurant.getDescription())
                .images(restaurant.getImages())
                .title(restaurant.getName())
                .Id(restaurant.getId())
                .build();

        if (user.getFavorites().contains(dto)) {
            user.getFavorites().remove(dto);
        } else {
            user.getFavorites().add(dto);
        }
        userRepository.save(user);
        return dto;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long restaurantId) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);
        restaurant.setOpen(!restaurant.isOpen());
        return restaurantRepository.save(restaurant);
    }
}
