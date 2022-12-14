package com.lucasv.DesafioBackendJava.entities;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 4,max = 255,message = "Tamanho minimo de user= 4 caracteres")
    private String username;

    @Email
    private String email;

    @Size(min = 8, message = "Tamanho minimo de senha: 8 caracteres")
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    List<UserRole> userRoles;

    public User(){
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserRole> getUserRoles() { return userRoles; }

    public void setUserRoles(List<UserRole> userRoles) { this.userRoles = userRoles; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
