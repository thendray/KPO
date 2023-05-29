package ru.thendray.authorizationapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import ru.thendray.authorizationapi.entities.UserEntity;
import ru.thendray.authorizationapi.entities.enums.UserRole;

import java.util.Optional;

@Component
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findUserEntityByEmail(String email);

}
