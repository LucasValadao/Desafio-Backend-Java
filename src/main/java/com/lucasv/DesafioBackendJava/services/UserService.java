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

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    
    public User insert(User obj) throws Exception {
        User existsUser = repository.findByEmail(obj.getEmail());
        
        if(existsUser != null) {
        throw new Exception("Usuario ja existe");
        }

        String hashSenha = new BCryptPasswordEncoder().encode(obj.getSenha());
        obj.setSenha(hashSenha);

        return repository.save(obj);
    }

    public String signin(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtTokenProvider.createToken(username, repository.findByUsername(username).getAppUserRoles());
        } catch (AuthenticationException e) {
            throw new CustomException("Usuario/senha invalido", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

}
