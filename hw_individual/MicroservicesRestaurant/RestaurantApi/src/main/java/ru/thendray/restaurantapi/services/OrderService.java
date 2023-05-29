package ru.thendray.restaurantapi.services;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import ru.thendray.restaurantapi.dto.MakeOrderRequestDto;
import ru.thendray.restaurantapi.dto.MakeOrderResponseDto;
import ru.thendray.restaurantapi.dto.OrderResponseDto;
import ru.thendray.restaurantapi.entities.OrderDishEntity;
import ru.thendray.restaurantapi.entities.OrderEntity;
import ru.thendray.restaurantapi.exceptions.BadRequestException;
import ru.thendray.restaurantapi.exceptions.NotFoundException;
import ru.thendray.restaurantapi.jwt.JwtExtractService;
import ru.thendray.restaurantapi.models.enums.OrderStatus;
import ru.thendray.restaurantapi.repositories.DishRepository;
import ru.thendray.restaurantapi.repositories.OrderDishRepository;
import ru.thendray.restaurantapi.repositories.OrderRepository;
import ru.thendray.restaurantapi.repositories.UserRepository;

@Component
public class OrderService {

    private final JwtExtractService jwtExtractService;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final DishRepository dishRepository;

    private final OrderDishRepository orderDishRepository;

    public OrderService(
            JwtExtractService jwtExtractService,
            UserRepository userRepository,
            OrderRepository orderRepository,
            DishRepository dishRepository,
            OrderDishRepository orderDishRepository) {
        this.jwtExtractService = jwtExtractService;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.dishRepository = dishRepository;
        this.orderDishRepository = orderDishRepository;
    }


    private void checkValidDishesFromRequest(MakeOrderRequestDto request) {
        for (var dish : request.getDishes()) {
            if (dish.getQuantity() < 0) {
                throw new BadRequestException("Quantity of dishes must be grater than zero!");
            }
        }
    }

    public MakeOrderResponseDto makeOrder(MakeOrderRequestDto request, HttpServletRequest httpRequest) {

        var header = httpRequest.getHeader("Authorization").substring(7);
        var userId = jwtExtractService.extractId(header);

        var user = userRepository.getUserEntityById(userId);

//        user.orElseThrow(() -> {
//            throw new ForbiddenException("Forbidden in authentication!");
//        });

        checkValidDishesFromRequest(request);

        OrderEntity order = new OrderEntity();
        order.setId(userId);
        order.setUser(user.get());
        order.setSpecialRequirements(request.getSpecialRequirements());
        order.setStatus(OrderStatus.TAKEN);

        order = orderRepository.saveAndFlush(order);

        double totalPrice = 0.0;

        for (var dish : request.getDishes()) {

            var dishId = dish.getDishId();
            var dishFromMenu = dishRepository.findDishEntityById(dishId);

            if (dishFromMenu.isEmpty()) {
                order.setStatus(OrderStatus.CANCELLED);
                orderRepository.saveAndFlush(order);
                throw new NotFoundException("Order cancelled! Restaurant menu does not include chosen dishes!");
            }

            if (dishFromMenu.get().getQuantity() <= 0) {
                order.setStatus(OrderStatus.CANCELLED);
                orderRepository.saveAndFlush(order);
                throw new BadRequestException("Order cancelled! Chosen dishes have been finished!");
            }

            if (dishFromMenu.get().getQuantity() < dish.getQuantity()) {
                order.setStatus(OrderStatus.CANCELLED);
                orderRepository.saveAndFlush(order);
                throw new BadRequestException("Order cancelled! No dishes in such quantity!");
            }

            dishFromMenu.get().setQuantity(dishFromMenu.get().getQuantity() - dish.getQuantity());


            dishRepository.saveAndFlush(dishFromMenu.get());


            double dishFinalPrice = dish.getQuantity() * dishFromMenu.get().getPrice();

            var orderDish = new OrderDishEntity();
            orderDish.setDish(dishFromMenu.get());
            orderDish.setOrder(order);
            orderDish.setQuantity(dish.getQuantity());
            orderDish.setPrice(dishFinalPrice);

            totalPrice += dishFinalPrice;

            orderDishRepository.saveAndFlush(orderDish);
        }

        return new MakeOrderResponseDto(totalPrice);
    }

    public OrderResponseDto getOrder(Long orderId) {

        var order = orderRepository.getOrderEntityById(orderId);

        if (order.isEmpty()) {
            throw new NotFoundException("Order not found!");
        }

        var orderDto = new OrderResponseDto();
        orderDto.setOrderStatus(order.get().getStatus());
        orderDto.setSpecialRequirements(order.get().getSpecialRequirements());

        return orderDto;
    }
}
