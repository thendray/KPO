package ru.thendray.restaurantapi.controllers;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.thendray.restaurantapi.dto.AddDishRequestDto;
import ru.thendray.restaurantapi.dto.GetDishResponseDto;
import ru.thendray.restaurantapi.dto.PutDishRequestDto;
import ru.thendray.restaurantapi.services.DishService;

import java.util.List;


@RestController
@RequestMapping("/restaurant/dish")
public class DishController {

    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }


    @PostMapping("/add_dish")
    public ResponseEntity<String> addDish(
            @RequestBody AddDishRequestDto request,
            HttpServletRequest httpRequest) {

        String result = dishService.AddDish(request, httpRequest);
        return ResponseEntity.ok(result);

    }

    @DeleteMapping("/delete_dish/{id}")
    public ResponseEntity<String> deleteDish(
            @PathVariable("id") Long dishId,
            HttpServletRequest httpRequest) {

        String result = dishService.deleteDish(dishId, httpRequest);
        return ResponseEntity.ok(result);

    }

    @PutMapping("/update_dish/{id}")
    public ResponseEntity<String> updateDish(
            @PathVariable("id") Long dishId,
            @RequestBody PutDishRequestDto request,
            HttpServletRequest httpRequest) {

        String result = dishService.updateDish(dishId, request, httpRequest);
        return ResponseEntity.ok(result);

    }


    @GetMapping("get_dish/{id}")
    public ResponseEntity<GetDishResponseDto> getDish(
            @PathVariable("id") Long dishId,
            HttpServletRequest httpRequest) {

        GetDishResponseDto response = dishService.getDish(dishId, httpRequest);
        return ResponseEntity.ok(response);
    }


    @GetMapping("get_menu")
    public ResponseEntity<List<GetDishResponseDto>> getMenu() {

        List<GetDishResponseDto> response = dishService.getAllAvailableDishes();
        return ResponseEntity.ok(response);
    }


}
