package ru.thendray.restaurantapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.thendray.restaurantapi.entities.OrderDishEntity;

import java.util.stream.Stream;

public interface OrderDishRepository extends JpaRepository<OrderDishEntity, Long> {

    Stream<OrderDishEntity> findAllByDishId(Long id);


}
