package com.lucasv.DesafioBackendJava.services;

import com.lucasv.DesafioBackendJava.entities.User;
import com.lucasv.DesafioBackendJava.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    
    public User insert(User obj){
        User existsUser = repository.findByUsername(obj.getName());
        
        if(existsUser != null) {
        throw new exception("Usuario ja existe");
        }
        
        return repository.save(obj);
    }

}
