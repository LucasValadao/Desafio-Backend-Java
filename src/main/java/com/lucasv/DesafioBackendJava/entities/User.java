package com.lucasv.DesafioBackendJava.entities;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_user")
@NoArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 4,max = 255,message = "Tamanho minimo de user= 4 caracteres")
    private String username;

    @Email
    private String email;

    @Size(min = 8, message = "Tamanho minimo de senha: 8 caracteres")
    private String senha;

    @ElementCollection(fetch = FetchType.EAGER)
    List<UserRole> userRoles;
}
