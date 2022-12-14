package com.lucasv.DesafioBackendJava.services;

import com.lucasv.DesafioBackendJava.entities.User;
import com.lucasv.DesafioBackendJava.exception.CustomException;
import com.lucasv.DesafioBackendJava.repositories.UserRepository;
import com.lucasv.DesafioBackendJava.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public String signin(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtTokenProvider.createToken(username, repository.findByUsername(username).getUserRoles());
        } catch (AuthenticationException e) {
            throw new CustomException("Usuario/senha invalido", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public String signup(User user) {
        if (!repository.existsByUsername(user.getUsername())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            repository.save(user);
            return jwtTokenProvider.createToken(user.getUsername(), user.getUserRoles());
        } else {
            throw new CustomException("Nome de usuario ja esta em uso", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }



    public User search(String username) {
        User user = repository.findByUsername(username);
        if (user == null) {
            throw new CustomException("Usuario nao existe", HttpStatus.NOT_FOUND);
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
