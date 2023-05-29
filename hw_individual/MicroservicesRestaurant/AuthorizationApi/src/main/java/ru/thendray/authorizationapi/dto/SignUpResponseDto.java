package ru.thendray.authorizationapi.dto;


import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class SignUpResponseDto {

    private String email;
    private String userName;
    private String token;

}
