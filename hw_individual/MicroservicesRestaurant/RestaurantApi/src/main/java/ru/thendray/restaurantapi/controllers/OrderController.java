package ru.thendray.restaurantapi.controllers;


import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.thendray.restaurantapi.dto.MakeOrderRequestDto;
import ru.thendray.restaurantapi.dto.MakeOrderResponseDto;
import ru.thendray.restaurantapi.dto.OrderResponseDto;
import ru.thendray.restaurantapi.services.OrderService;


@RestController
@RequestMapping("/restaurant/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping("/make_order")
    public ResponseEntity<MakeOrderResponseDto> makeOrder(
            @RequestBody MakeOrderRequestDto request,
            @NonNull HttpServletRequest httpRequest) {

        var response = orderService.makeOrder(request, httpRequest);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/get_order/{id}")
    public ResponseEntity<OrderResponseDto> getOrder(
            @PathVariable("id") Long orderId) {

        var response = orderService.getOrder(orderId);

        return ResponseEntity.ok(response);
    }

}
