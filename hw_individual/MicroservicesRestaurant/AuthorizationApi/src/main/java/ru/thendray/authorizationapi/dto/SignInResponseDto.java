package ru.thendray.authorizationapi.dto;


import lombok.Data;

@Data
public class SignInResponseDto {
    private String email;
    private String token;
}
