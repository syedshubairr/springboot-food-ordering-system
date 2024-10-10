package com.shah.foodwebsite.service;

import com.shah.foodwebsite.dto.request.OrderRequest;
import com.shah.foodwebsite.entity.Order;
import com.shah.foodwebsite.entity.User;

import java.util.List;

public interface OrderService {
    public Order createOrder(OrderRequest order, User user) throws Exception;

    public Order updateOrder(Long orderId, String orderStatus) throws Exception;

    public void cancelOrder(Long orderId) throws Exception;

    public List<Order> getUsersOrders(Long userId) throws Exception;

    public List<Order> getRestaurantsOrder(Long restaurantId, String orderStatus) throws Exception;
}
