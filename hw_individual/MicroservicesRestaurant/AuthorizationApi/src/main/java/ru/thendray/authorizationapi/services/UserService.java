package ru.thendray.authorizationapi.services;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.thendray.authorizationapi.dto.SignInResponseDto;
import ru.thendray.authorizationapi.dto.SignUpResponseDto;
import ru.thendray.authorizationapi.entities.UserEntity;
import ru.thendray.authorizationapi.entities.enums.UserRole;
import ru.thendray.authorizationapi.exceptions.BadRequestException;
import ru.thendray.authorizationapi.jwt.JwtProvider;
import ru.thendray.authorizationapi.repositories.UserRepository;



@Component
public class UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, JwtProvider provider, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtProvider = provider;
        this.passwordEncoder = passwordEncoder;
    }


    public SignUpResponseDto SignUpNewUser(String email, String name, String password) {

        // TODO check email on correct input
        UserEntity user = new UserEntity();
        user.setEmail(email);
        user.setUsername(name);
        user.setPasswordHash(passwordEncoder.encode(password));
        user.setRole(UserRole.CLIENT);
        try {
            user = userRepository.saveAndFlush(user);
        } catch (Exception exception) {
            throw new BadRequestException("User name | Email must be unique!");
        }

        var token = jwtProvider.generateJwt(user);

        return new SignUpResponseDto(user.getEmail(), user.getUsername(), token);

    }

    public SignInResponseDto SignInUser(String email, String password) {

        var user = userRepository.findUserEntityByEmail(email);

        user.orElseThrow(() -> {
            throw new BadRequestException("Email or Password is incorrect!");
        });

        var isMatchPassword = BCrypt.checkpw(password, user.get().getPasswordHash());

        if (!isMatchPassword) {

            throw new BadRequestException("Email or Password is incorrect!");
        }

        var token = jwtProvider.generateJwt(user.get());

        var response = new SignInResponseDto();
        response.setEmail(email);
        response.setToken(token);

        return response;

    }
}
