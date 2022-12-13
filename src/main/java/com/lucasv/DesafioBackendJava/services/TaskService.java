package com.lucasv.DesafioBackendJava.services;

import com.lucasv.DesafioBackendJava.entities.Task;
import com.lucasv.DesafioBackendJava.entities.User;
import com.lucasv.DesafioBackendJava.repositories.TaskRepository;
import com.lucasv.DesafioBackendJava.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    public List<Task> findAll(){
        return repository.findAll();
    }

}
