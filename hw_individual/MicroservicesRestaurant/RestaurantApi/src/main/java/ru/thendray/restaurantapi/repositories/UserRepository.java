package ru.thendray.restaurantapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import ru.thendray.restaurantapi.entities.UserEntity;

import java.util.Optional;

@Component
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> getUserEntityById(Long id);

    Optional<UserEntity> findUserEntityByEmail(String email);


}
