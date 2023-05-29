package ru.thendray.restaurantapi.dto;

import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class PutDishRequestDto {
    @Nullable
    private String name;
    @Nullable
    private Double price;
    @Nullable
    private String description;
    @Nullable
    private Integer quantity;
}
