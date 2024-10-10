package com.shah.foodwebsite.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;
    @ManyToOne
    @JsonIgnore
    private Cart cart;
    @ManyToOne
    private Food food;
    private int quantity;
    private List<String> ingredients;
    private Long totalPrice;
}
