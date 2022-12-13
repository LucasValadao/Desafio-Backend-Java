package com.lucasv.DesafioBackendJava.resources;

import com.lucasv.DesafioBackendJava.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @GetMapping
    public ResponseEntity<List> findAll(){
        User user = new User();
        return ResponseEntity.ok().body(Collections.singletonList(user));
    }
}
