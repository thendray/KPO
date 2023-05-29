package ru.thendray.authorizationapi.services;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.thendray.authorizationapi.dto.SignInResponseDto;
import ru.thendray.authorizationapi.dto.SignUpResponseDto;
import ru.thendray.authorizationapi.dto.UserResponseDto;
import ru.thendray.authorizationapi.entities.UserEntity;
import ru.thendray.authorizationapi.entities.enums.UserRole;
import ru.thendray.authorizationapi.exceptions.BadRequestException;
import ru.thendray.authorizationapi.jwt.JwtExtractService;
import ru.thendray.authorizationapi.jwt.JwtProvider;
import ru.thendray.authorizationapi.repositories.UserRepository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
public class UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final JwtExtractService extractService;

    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            JwtProvider provider,
            PasswordEncoder passwordEncoder,
            JwtExtractService jwtExtractService) {
        this.userRepository = userRepository;
        this.jwtProvider = provider;
        this.passwordEncoder = passwordEncoder;
        this.extractService = jwtExtractService;
    }

    public final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.matches();
    }

    public SignUpResponseDto SignUpNewUser(String email, String name, String password) {

        if (!validate(email)) {
            throw new BadRequestException("Email is incorrect!");
        }

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

        return new SignUpResponseDto(user.getEmail(), user.getUsername(), token, user.getRole());

    }

    public SignInResponseDto LogInUser(String email, String password) {

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

    public UserResponseDto getUserInfo(String token) {

        var email = extractService.extractEmail(token);
        var user = userRepository.findUserEntityByEmail(email);

        if (user.isEmpty()) {
            throw new BadRequestException("Wrong token!");
        }

        var userDto = new UserResponseDto();
        userDto.setId(user.get().getId());
        userDto.setUsername(user.get().getUsername());
        userDto.setRole(user.get().getRole());
        userDto.setEmail(email);

        return userDto;
    }
}
