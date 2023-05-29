package ru.thendray.restaurantapi.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class MakeOrderResponseDto {

    @JsonProperty("total_price")
    private Double totalPrice;

}
