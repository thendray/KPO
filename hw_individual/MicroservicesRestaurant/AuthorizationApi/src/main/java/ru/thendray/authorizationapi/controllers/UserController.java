package ru.thendray.authorizationapi.controllers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.thendray.authorizationapi.dto.*;

import ru.thendray.authorizationapi.services.UserService;



@RestController
@RequestMapping("/auth/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign_up")
    public ResponseEntity<SignUpResponseDto> registration(
            @RequestBody SignUpRequestDto request) {

        var response = userService.SignUpNewUser(request);

        return ResponseEntity.ok(response);

    }

    @PostMapping("/log_in")
    public ResponseEntity<SignInResponseDto> authorization(
            @RequestBody SignInRequestDto request) {

        var email = request.getEmail();
        var password = request.getPassword();

        var response = userService.LogInUser(email, password);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/get_info")
    public ResponseEntity<UserResponseDto> getInfo(@RequestHeader("Authorization") String token) {

        UserResponseDto response = userService.getUserInfo(token);

        return ResponseEntity.ok(response);

    }


}
