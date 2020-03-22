package com.example.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRegisterDto {

    @Pattern(regexp = ".+@.+\\..+", message = "Email not valid")
    private String email;
    @Pattern(regexp = "[A-Z]+[a-z]+[0-9]+", message = "Password is not valid")
    @Size(min = 6, message = "Pass length not valid")

    private String password;
    @NotNull(message = "Full name must not be null")
    private String fullName;

}
