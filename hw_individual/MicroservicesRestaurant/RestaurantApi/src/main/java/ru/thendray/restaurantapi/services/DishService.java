package ru.thendray.restaurantapi.services;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import ru.thendray.restaurantapi.dto.AddDishRequestDto;
import ru.thendray.restaurantapi.dto.GetDishResponseDto;
import ru.thendray.restaurantapi.dto.PutDishRequestDto;
import ru.thendray.restaurantapi.entities.DishEntity;
import ru.thendray.restaurantapi.entities.OrderDishEntity;
import ru.thendray.restaurantapi.exceptions.BadRequestException;
import ru.thendray.restaurantapi.exceptions.ForbiddenException;
import ru.thendray.restaurantapi.exceptions.NotFoundException;
import ru.thendray.restaurantapi.jwt.JwtExtractService;
import ru.thendray.restaurantapi.models.enums.OrderStatus;
import ru.thendray.restaurantapi.models.enums.UserRole;
import ru.thendray.restaurantapi.repositories.DishRepository;
import ru.thendray.restaurantapi.repositories.OrderDishRepository;
import ru.thendray.restaurantapi.repositories.UserRepository;


import java.util.List;


@Component
public class DishService {

    private final DishRepository dishRepository;
    private final UserRepository userRepository;
    private final OrderDishRepository orderDishRepository;
    private final JwtExtractService jwtExtractService;

    public DishService(
            DishRepository dishRepository,
            UserRepository userRepository,
            OrderDishRepository orderRepository,
            JwtExtractService jwtExtractService) {
        this.dishRepository = dishRepository;
        this.userRepository = userRepository;
        this.orderDishRepository = orderRepository;
        this.jwtExtractService = jwtExtractService;
    }


    private void checkUserRole(HttpServletRequest httpRequest) {
        var header = httpRequest.getHeader("Authorization").substring(7);
        var userId = jwtExtractService.extractId(header);

        var user = userRepository.getUserEntityById(userId).get();


        if (user.getRole() != UserRole.MANAGER) {
            throw new ForbiddenException("Refused in action! Your role must be MANAGER!");
        }
    }

    private void checkDishRequestData(AddDishRequestDto request) {

        if (request.getPrice() < 0) {
            throw new BadRequestException("Price can not be below zero!");
        }

        if (request.getQuantity() < 0) {
            throw new BadRequestException("Quantity can not be below zero!");
        }

    }

    public String AddDish(AddDishRequestDto request, HttpServletRequest httpRequest) {

        checkUserRole(httpRequest);

        checkDishRequestData(request);

        var dish = new DishEntity();
        dish.setName(request.getName());
        dish.setPrice(request.getPrice());
        dish.setQuantity(request.getQuantity());
        dish.setDescription(request.getDescription());

        dishRepository.saveAndFlush(dish);

        return "Dish added successfully!";

    }

    @Transactional
    public String deleteDish(Long dishId, HttpServletRequest httpRequest) {

        checkUserRole(httpRequest);

        var dishForDelete = dishRepository.findDishEntityById(dishId);

        if (dishForDelete.isEmpty()) {
            throw new NotFoundException("No such dish in menu!");
        }

        var orderDishes = orderDishRepository.findAllByDishId(dishId);
        var ordersWithDeletedDish = orderDishes
                .map(OrderDishEntity::getOrder);


        var isAllReady = ordersWithDeletedDish.allMatch(x ->
                x.getStatus() == OrderStatus.READY || x.getStatus() == OrderStatus.CANCELLED);

        if (!isAllReady) {
            throw new BadRequestException("Can not delete this dish now!");
        }

        var orderDishIds = orderDishRepository.findAllByDishId(dishId)
                .map(OrderDishEntity::getId)
                .toList();

        for (var id : orderDishIds) {
            orderDishRepository.deleteById(id);
        }

        dishRepository.delete(dishForDelete.get());

        return "Dish deleted successfully";
    }


    public String updateDish(Long dishId, PutDishRequestDto request, HttpServletRequest httpRequest) {

        checkUserRole(httpRequest);

        var dishForUpdating = dishRepository.findDishEntityById(dishId);

        if (dishForUpdating.isEmpty()) {
            throw new NotFoundException("No such dish in menu!");
        }

        if (request.getName() != null) {
            dishForUpdating.get().setName(request.getName());
        }

        if (request.getDescription() != null) {
            dishForUpdating.get().setDescription(request.getDescription());
        }

        if (request.getQuantity() != null && request.getQuantity() >= 0) {
            dishForUpdating.get().setQuantity(request.getQuantity());
        } else if (request.getQuantity() != null && request.getQuantity() < 0 ) {
            throw new BadRequestException("Quantity can not be below zero!");
        }

        if (request.getPrice() != null && request.getPrice() >= 0) {
            dishForUpdating.get().setPrice(request.getPrice());
        } else if (request.getPrice() != null && request.getPrice() < 0) {
            throw new BadRequestException("Price can not be below zero!");
        }

        dishRepository.saveAndFlush(dishForUpdating.get());

        return "Dish updated successfully!";
    }

    public GetDishResponseDto getDish(Long dishId, HttpServletRequest httpRequest) {

        checkUserRole(httpRequest);

        var dishForReturning = dishRepository.findDishEntityById(dishId);

        if (dishForReturning.isEmpty()) {
            throw new NotFoundException("No such dish in menu!");
        }


        var returnDishResponse = new GetDishResponseDto();

        returnDishResponse.setId(dishForReturning.get().getId());
        returnDishResponse.setName(dishForReturning.get().getName());
        returnDishResponse.setDescription(dishForReturning.get().getDescription());
        returnDishResponse.setQuantity(dishForReturning.get().getQuantity());
        returnDishResponse.setPrice(dishForReturning.get().getPrice());

        return returnDishResponse;

    }


    @Transactional
    public List<GetDishResponseDto> getAllAvailableDishes() {

        var availableDish = dishRepository.getDishEntitiesByQuantityGreaterThan(0)
                .map(x ->
                {
                    var dishDto = new GetDishResponseDto();

                    dishDto.setId(x.getId());
                    dishDto.setName(x.getName());
                    dishDto.setDescription(x.getDescription());
                    dishDto.setQuantity(x.getQuantity());
                    dishDto.setPrice(x.getPrice());


                    return dishDto;
                })
                .toList();

        return availableDish;
    }
}
