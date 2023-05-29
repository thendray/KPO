package ru.thendray.authorizationapi.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ErrorDto {

    private String error;

    @JsonProperty("error_description")
    String errorDescription;
}
