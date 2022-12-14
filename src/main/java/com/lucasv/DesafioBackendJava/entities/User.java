package com.lucasv.DesafioBackendJava.entities;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "tb_user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 4,max = 255,message = "Tamanho minimo de user= 4 caracteres")
    @Column(unique = true,nullable = false)
    private String username;

    @Column(unique = true,nullable = false)
    private String email;

    @Size(min = 8, message = "Tamanho minimo de senha: 8 caracteres")
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    List<UserRole> userRoles;

}
