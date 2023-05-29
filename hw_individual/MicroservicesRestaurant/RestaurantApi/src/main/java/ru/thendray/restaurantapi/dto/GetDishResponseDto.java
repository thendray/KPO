package ru.thendray.restaurantapi.dto;

import lombok.Data;

@Data
public class GetDishResponseDto {
    private Long id;
    private String name;
    private Double price;
    private String description;
    private Integer quantity;
}
