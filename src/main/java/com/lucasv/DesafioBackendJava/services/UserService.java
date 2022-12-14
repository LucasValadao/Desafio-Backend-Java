package com.lucasv.DesafioBackendJava.services;

import com.lucasv.DesafioBackendJava.entities.User;
import com.lucasv.DesafioBackendJava.exception.CustomException;
import com.lucasv.DesafioBackendJava.repositories.UserRepository;
import com.lucasv.DesafioBackendJava.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.lucasv.DesafioBackendJava.repositories.UserRepository;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    
    public String insert(User obj) {
        User existsUser = repository.findByEmail(obj.getEmail());
        
        if(existsUser != null) {
        throw new CustomException("Usuario ja existe",HttpStatus.UNPROCESSABLE_ENTITY);
        }

        String hashSenha = new BCryptPasswordEncoder().encode(obj.getPassword());
        obj.setPassword(hashSenha);
        repository.save(obj);
        return jwtTokenProvider.createToken(obj.getUsername(),obj.getUserRoles());
    }

    public String signin(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtTokenProvider.createToken(username, repository.findByUsername(username).getUserRoles());
        } catch (AuthenticationException e) {
            throw new CustomException("Usuario/senha invalido", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public User search(String username) {
        User user = repository.findByUsername(username);
        if (user == null) {
            throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
        }
        return user;
    }

    public User whoami(HttpServletRequest req) {
        return repository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
    }

    public String refresh(String username) {
        return jwtTokenProvider.createToken(username, repository.findByUsername(username).getUserRoles());
    }

}
