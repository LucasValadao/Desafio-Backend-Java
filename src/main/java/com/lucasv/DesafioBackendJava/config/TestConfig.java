package com.lucasv.DesafioBackendJava.config;

import com.lucasv.DesafioBackendJava.entities.*;
import com.lucasv.DesafioBackendJava.entities.enums.TaskStatus;
import com.lucasv.DesafioBackendJava.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void run(String... args) throws Exception {


        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "123456");
        User u2 = new User(null, "Joao Blue", "joao@gmail.com","123456");

        Task t1 = new Task(null,"Teste123", TaskStatus.ALTO);
        Task t2 = new Task(null,"Teste321", TaskStatus.BAIXO);

        userRepository.saveAll(Arrays.asList(u1,u2));
        taskRepository.saveAll(Arrays.asList(t1,t2));


    }

}
