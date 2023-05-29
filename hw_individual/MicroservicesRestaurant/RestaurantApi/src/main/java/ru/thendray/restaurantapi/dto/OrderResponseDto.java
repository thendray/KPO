package ru.thendray.restaurantapi.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import ru.thendray.restaurantapi.models.enums.OrderStatus;

@Data
public class OrderResponseDto {

    @JsonProperty("special_requirements")
    private String specialRequirements;
    @JsonProperty("order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
