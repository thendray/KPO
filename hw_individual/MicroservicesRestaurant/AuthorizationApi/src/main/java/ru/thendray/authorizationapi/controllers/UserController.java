package ru.thendray.authorizationapi.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.thendray.authorizationapi.dto.SignInRequestDto;
import ru.thendray.authorizationapi.dto.SignInResponseDto;
import ru.thendray.authorizationapi.dto.SignUpRequestDto;
import ru.thendray.authorizationapi.dto.SignUpResponseDto;

import ru.thendray.authorizationapi.entities.UserEntity;
import ru.thendray.authorizationapi.exceptions.BadRequestException;
import ru.thendray.authorizationapi.jwt.JwtProvider;
import ru.thendray.authorizationapi.services.UserService;

import java.time.Instant;
import java.util.Date;


@RestController
@RequestMapping("/auth/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign_up")
    public ResponseEntity<SignUpResponseDto> Registration(
            @RequestBody SignUpRequestDto request) {

        // TODO добавить провреку на валидность

        var response = userService.SignUpNewUser(
                request.getEmail(), request.getUserName(), request.getPassword());

        return ResponseEntity.ok(response);

    }

    @PostMapping("/log_in")
    public ResponseEntity<SignInResponseDto> Authorization(
            @RequestBody SignInRequestDto request) {

        var email = request.getEmail();
        var password = request.getPassword();

        var response = userService.SignInUser(email, password);

        return ResponseEntity.ok(response);

    }


}
