package com.shah.foodwebsite.service.Impl;

import com.shah.foodwebsite.dto.request.AddCartItemRequest;
import com.shah.foodwebsite.entity.Cart;
import com.shah.foodwebsite.entity.CartItem;
import com.shah.foodwebsite.entity.Food;
import com.shah.foodwebsite.entity.User;
import com.shah.foodwebsite.repository.CartItemRepository;
import com.shah.foodwebsite.repository.CartRepository;
import com.shah.foodwebsite.service.CartService;
import com.shah.foodwebsite.service.FoodService;
import com.shah.foodwebsite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private FoodService foodService;

    @Override
    public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Food food = foodService.findFoodById(req.getFoodId());
        Cart cart = cartRepository.findByCustomerId(user.getId());
        for (CartItem cartItem : cart.getCartItems()) {
            if (cartItem.getFood().equals(food)) {
                int newQuantity = cartItem.getQuantity() + req.getQuantity();
                return updateCartItemQuantity(cartItem.getId(), newQuantity);
            }
        }
        CartItem newCartItem = CartItem.builder()
                .food(food)
                .cart(cart)
                .quantity(req.getQuantity())
                .ingredients(req.getIngredients())
                .totalPrice(req.getQuantity() * food.getPrice())
                .build();
        CartItem savedCartItem = cartItemRepository.save(newCartItem);
        cart.getCartItems().add(savedCartItem);
        return savedCartItem;
    }

    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new Exception("Cart Item not Present."));
        cartItem.setQuantity(quantity);
        cartItem.setTotalPrice(cartItem.getFood().getPrice() * quantity);
        return cartItemRepository.save(cartItem);
    }

    @Override
    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartRepository.findByCustomerId(user.getId());
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new Exception("Cart Item not Present."));
        cart.getCartItems().remove(cartItem);
        return cartRepository.save(cart);
    }

    @Override
    public Long calculateCartTotals(Cart cart) throws Exception {
        long total = 0L;
        for (CartItem cartItem : cart.getCartItems()) {
            total += cartItem.getFood().getPrice() * cartItem.getQuantity();
        }
        return total;
    }

    @Override
    public Cart findCartById(Long id) throws Exception {
        return cartRepository.findById(id)
                .orElseThrow(() -> new Exception("Cart not found with id: " + id));
    }

    @Override
    public Cart findCartByUserId(Long userId) throws Exception {
        Cart cart = cartRepository.findByCustomerId(userId);
        cart.setTotal(calculateCartTotals(cart));
        return cart;
    }

    @Override
    public Cart clearCart(Long userId) throws Exception {
        Cart cart = findCartByUserId(userId);
        cart.getCartItems().clear();
        return cartRepository.save(cart);
    }
}
