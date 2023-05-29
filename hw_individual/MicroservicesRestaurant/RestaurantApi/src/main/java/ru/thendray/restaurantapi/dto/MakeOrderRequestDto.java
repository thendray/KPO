package ru.thendray.restaurantapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class MakeOrderRequestDto {
    @JsonProperty("dishes")
    private List<DishRequestDto> dishes;
    @JsonProperty("special_requirements")
    private String specialRequirements;
}
