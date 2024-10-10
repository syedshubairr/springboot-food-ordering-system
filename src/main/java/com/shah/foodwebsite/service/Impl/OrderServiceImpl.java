package com.shah.foodwebsite.service.Impl;

import com.shah.foodwebsite.dto.request.OrderRequest;
import com.shah.foodwebsite.entity.*;
import com.shah.foodwebsite.repository.*;
import com.shah.foodwebsite.service.CartService;
import com.shah.foodwebsite.service.OrderService;
import com.shah.foodwebsite.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private CartService cartService;

    @Override
    public Order createOrder(OrderRequest order, User user) throws Exception {
        Address shippingAddress = order.getDeliveryAddress();
        Address savedAddress = addressRepository.save(shippingAddress);
        if (!user.getAddresses().contains(savedAddress)) {
            user.getAddresses().add(savedAddress);
            userRepository.save(user);
        }
        Restaurant restaurant = restaurantService.findRestaurantById(order.getRestaurantId());
        Order createdOrder = Order.builder()
                .customer(user)
                .createdAt(new Date())
                .orderStatus("PENDING")
                .deliveryAddress(savedAddress)
                .restaurant(restaurant)
                .build();
        Cart cart = cartService.findCartByUserId(user.getId());
        List<OrderItems> orderItems = new ArrayList<>();
        for (CartItem cartItem : cart.getCartItems()) {
            OrderItems orderItem = OrderItems.builder()
                    .food(cartItem.getFood())
                    .ingredients(cartItem.getIngredients())
                    .quantity(cartItem.getQuantity())
                    .totalPrice(cartItem.getTotalPrice())
                    .build();
            OrderItems savedOrderItem = orderItemRepository.save(orderItem);
            orderItems.add(savedOrderItem);
        }
        createdOrder.setOrderItems(orderItems);
        createdOrder.setTotalPrice(cart.getTotal());
    }

    @Override
    public Order updateOrder(Long orderId, String orderStatus) throws Exception {
        return null;
    }

    @Override
    public void cancelOrder(Long orderId) throws Exception {

    }

    @Override
    public List<Order> getUsersOrders(Long userId) throws Exception {
        return List.of();
    }

    @Override
    public List<Order> getRestaurantsOrder(Long restaurantId, String orderStatus) throws Exception {
        return List.of();
    }
}
