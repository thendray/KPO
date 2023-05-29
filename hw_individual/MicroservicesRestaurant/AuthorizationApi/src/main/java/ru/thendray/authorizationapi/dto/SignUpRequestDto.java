package ru.thendray.authorizationapi.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ru.thendray.authorizationapi.entities.enums.UserRole;

@Data
public class SignUpRequestDto {

    private String email;
    private String password;
    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("user_role")
    private UserRole userRole;

}
