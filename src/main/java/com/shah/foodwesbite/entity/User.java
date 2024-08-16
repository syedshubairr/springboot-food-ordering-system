package com.shah.foodwesbite.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shah.foodwesbite.dto.RestaurantDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String fullName;
    private String email;
    private String password;
    private USER_ROLE role;
    @JsonIgnore // because when fetching the user we don't need orders list.
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer") // TODO
    private List<Order> orderlist = new ArrayList<>();
    @ElementCollection
    private List<RestaurantDTO> favorites = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();
}
