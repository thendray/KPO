package ru.thendray.restaurantapi.repositories;

import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.thendray.restaurantapi.entities.OrderEntity;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    Optional<OrderEntity> getOrderEntityById(Long id);

}
