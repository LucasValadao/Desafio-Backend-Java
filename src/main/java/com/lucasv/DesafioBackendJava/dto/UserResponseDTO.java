package com.lucasv.DesafioBackendJava.dto;

import java.util.List;

import lombok.Data;
import com.lucasv.DesafioBackendJava.entities.UserRole;

@Data
public class UserResponseDTO {

    private Integer id;
    private String username;
    private String email;
    List<UserRole> UserRoles;
}