package ru.thendray.authorizationapi.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SignUpRequestDto {

    private String email;
    private String password;
    @JsonProperty("user_name")
    private String userName;

}
