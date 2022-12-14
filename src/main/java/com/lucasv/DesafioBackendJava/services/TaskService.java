package com.lucasv.DesafioBackendJava.services;

import com.lucasv.DesafioBackendJava.entities.Task;
import com.lucasv.DesafioBackendJava.repositories.TaskRepository;
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

    public Task insert(Task obj){
        return repository.save(obj);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public Task update(Long id, Task obj){
        Task entity = repository.getById(id);
        updateData(entity,obj);
        return repository.save(entity);
    }

    private void updateData(Task entity, Task obj) {
        entity.setDescricao(obj.getDescricao());
        entity.setTaskPriorityStatus(obj.getTaskPriorityStatus());
    }

    public Task updateStatus(Long id, Task obj){
        Task entity = repository.getById(id);
        updateDataStatus(entity,obj);
        return repository.save(entity);
    }

    private void updateDataStatus(Task entity, Task obj){
        entity.setTaskStatus(obj.getTaskStatus());
    }
}
