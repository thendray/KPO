package ru.thendray.restaurantapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.thendray.restaurantapi.entities.OrderEntity;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    Optional<OrderEntity> getOrderEntityById(Long id);

}
