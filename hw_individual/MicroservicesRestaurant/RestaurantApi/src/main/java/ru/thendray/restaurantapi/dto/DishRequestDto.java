package ru.thendray.restaurantapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DishRequestDto {

    @JsonProperty("dish_id")
    private Long dishId;
    private Integer quantity;
}
