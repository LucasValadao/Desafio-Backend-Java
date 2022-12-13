package com.lucasv.DesafioBackendJava.repositories;

import com.lucasv.DesafioBackendJava.entities.Task;
import com.lucasv.DesafioBackendJava.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
