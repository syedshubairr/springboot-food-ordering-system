package com.shah.foodwebsite.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Embeddable
@Builder
public class RestaurantDTO {
    private String title;
    @Column(length = 1000)
    private List<String> images;
    private String description;
    private long Id;
}
