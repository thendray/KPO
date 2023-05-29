package ru.thendray.restaurantapi.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class MakeOrderResponseDto {

    @JsonProperty("total_price")
    private Double totalPrice;

}
