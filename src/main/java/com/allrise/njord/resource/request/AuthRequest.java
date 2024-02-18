package com.allrise.njord.resource.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class AuthRequest {

    @NotNull
    @Email
    @Length(min = 5, max = 120)
    private String email;

    @NotNull
    @Length(min = 5, max = 64)
    private String password;
}
