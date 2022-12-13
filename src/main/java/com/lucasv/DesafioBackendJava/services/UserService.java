package com.lucasv.DesafioBackendJava.services;

import com.lucasv.DesafioBackendJava.entities.User;
import com.lucasv.DesafioBackendJava.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    
    public User insert(User obj) throws Exception {
        User existsUser = repository.findByEmail(obj.getEmail());
        
        if(existsUser != null) {
        throw new Exception("Usuario ja existe");
        }

        String hashSenha = new BCryptPasswordEncoder().encode(obj.getSenha());
        obj.setSenha(hashSenha);

        return repository.save(obj);
    }

}
