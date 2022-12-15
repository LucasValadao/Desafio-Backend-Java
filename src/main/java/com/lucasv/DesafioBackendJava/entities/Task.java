package com.lucasv.DesafioBackendJava.entities;

import com.lucasv.DesafioBackendJava.entities.enums.TaskPriorityStatus;
import com.lucasv.DesafioBackendJava.entities.enums.TaskStatus;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "tb_task")
public class Task implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private TaskStatus taskStatus;
    private TaskPriorityStatus taskPriorityStatus;
}
