package com.shah.foodwesbite.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.List;

@Data
@Embeddable
public class RestaurantDTO {
    private String title;
    @Column(length = 1000)
    private List<String> images;
    private String description;
    private String Id;
}
