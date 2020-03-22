package com.example.demo.dtos;

import com.example.demo.entities.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserDto {

    private String fullName;
    private String email;
    private String password;
    private Role role;
}
