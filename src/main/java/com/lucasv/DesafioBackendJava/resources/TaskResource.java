package com.lucasv.DesafioBackendJava.resources;

import com.lucasv.DesafioBackendJava.entities.Task;
import com.lucasv.DesafioBackendJava.entities.User;
import com.lucasv.DesafioBackendJava.services.TaskService;
import com.lucasv.DesafioBackendJava.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/tasks")
public class TaskResource {

    @Autowired
    private TaskService service;
    @GetMapping
    public ResponseEntity<List<Task>> findAll(){
        List<Task> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
}
