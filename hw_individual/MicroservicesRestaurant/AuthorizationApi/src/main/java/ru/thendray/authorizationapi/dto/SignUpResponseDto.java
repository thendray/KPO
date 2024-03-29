package ru.thendray.authorizationapi.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.thendray.authorizationapi.entities.enums.UserRole;


@Data
@AllArgsConstructor
public class SignUpResponseDto {

    private String email;
    private String userName;
    private String token;

    @JsonProperty("user_role")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

}
