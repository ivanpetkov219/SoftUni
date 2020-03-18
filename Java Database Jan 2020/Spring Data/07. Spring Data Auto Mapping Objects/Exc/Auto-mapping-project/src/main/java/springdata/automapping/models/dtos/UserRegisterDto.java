package springdata.automapping.models.dtos;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDto {

    @Pattern(regexp = "[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}", message = "Email is not valid!")
    private String email;
    @Size(min = 6, message = "Lenght of password must be at least 6 characters!")
    @Pattern(regexp = "[A-Z]+[a-z]+[0-9]+", message = "Password must contain 1 uppercase, 1 lowercase and 1 digit!")
    private String password;
    @NotNull(message = "Full name cannot be null!")
    private String fullName;
}
