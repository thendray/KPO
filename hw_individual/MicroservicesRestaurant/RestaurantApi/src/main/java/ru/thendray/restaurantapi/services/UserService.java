package ru.thendray.restaurantapi.services;

import org.springframework.stereotype.Component;
import ru.thendray.restaurantapi.entities.UserEntity;
import ru.thendray.restaurantapi.exceptions.NotFoundException;
import ru.thendray.restaurantapi.repositories.UserRepository;

import java.util.Optional;

@Component
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserEntity getUserByEmailOrThrowException(String email) {

        Optional<UserEntity> user = userRepository.findUserEntityByEmail(email);

        return user.orElseThrow(() -> {
            throw new NotFoundException("User not found!");
        });

    }
}
