package ru.thendray.restaurantapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.thendray.restaurantapi.entities.DishEntity;

import java.util.Optional;
import java.util.stream.Stream;

public interface DishRepository extends JpaRepository<DishEntity, Long> {

    Optional<DishEntity> findDishEntityById(Long id);

    Stream<DishEntity> getDishEntitiesByQuantityGreaterThan(Integer level);

}
