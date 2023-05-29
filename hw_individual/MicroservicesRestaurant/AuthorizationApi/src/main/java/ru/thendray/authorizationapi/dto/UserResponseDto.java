package ru.thendray.authorizationapi.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import ru.thendray.authorizationapi.entities.enums.UserRole;

@Data
public class UserResponseDto {
    private Long id;

    @JsonProperty("user_name")
    private String username;

    private String email;

    @Enumerated(value = EnumType.STRING)
    private UserRole role;
}
