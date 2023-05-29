package ru.thendray.restaurantapi.dto;


import lombok.Data;

@Data
public class AddDishRequestDto {
    private String name;
    private Double price;
    private String description;
    private Integer quantity;

}
