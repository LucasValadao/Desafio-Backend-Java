package com.lucasv.DesafioBackendJava.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import com.lucasv.DesafioBackendJava.entities.UserRole;

@Data
@NoArgsConstructor
public class UserDataDTO {

    private String username;
    private String email;
    private String password;
    List<UserRole> UserRoles;
}