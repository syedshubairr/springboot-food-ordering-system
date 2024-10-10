package com.shah.foodwebsite.repository;

import com.shah.foodwebsite.entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItems, Long> {
}
