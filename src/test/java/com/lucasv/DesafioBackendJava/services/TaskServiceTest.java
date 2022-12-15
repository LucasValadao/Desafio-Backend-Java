package com.lucasv.DesafioBackendJava.services;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.lucasv.DesafioBackendJava.entities.Task;
import com.lucasv.DesafioBackendJava.entities.enums.TaskPriorityStatus;
import com.lucasv.DesafioBackendJava.entities.enums.TaskStatus;
import com.lucasv.DesafioBackendJava.repositories.TaskRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {TaskService.class})
@ExtendWith(SpringExtension.class)
class TaskServiceTest {
    @MockBean
    private TaskRepository taskRepository;

    @Autowired
    private TaskService taskService;

    /**
     * Method under test: {@link TaskService#findAll()}
     */
    @Test
    void testFindAll() {
        ArrayList<Task> taskList = new ArrayList<>();
        when(taskRepository.findAll()).thenReturn(taskList);
        List<Task> actualFindAllResult = taskService.findAll();
        assertSame(taskList, actualFindAllResult);
        assertTrue(actualFindAllResult.isEmpty());
        verify(taskRepository).findAll();
    }

    /**
     * Method under test: {@link TaskService#insert(Task)}
     */
    @Test
    void testInsert() {
        Task task = new Task();
        task.setDescricao("Descricao");
        task.setId(123L);
        task.setTaskPriorityStatus(TaskPriorityStatus.ALTO);
        task.setTaskStatus(TaskStatus.NAO_CONCLUIDO);
        when(taskRepository.save((Task) any())).thenReturn(task);

        Task task1 = new Task();
        task1.setDescricao("Descricao");
        task1.setId(123L);
        task1.setTaskPriorityStatus(TaskPriorityStatus.ALTO);
        task1.setTaskStatus(TaskStatus.NAO_CONCLUIDO);
        assertSame(task, taskService.insert(task1));
        verify(taskRepository).save((Task) any());
    }

    /**
     * Method under test: {@link TaskService#delete(Long)}
     */
    @Test
    void testDelete() {
        doNothing().when(taskRepository).deleteById((Long) any());
        taskService.delete(123L);
        verify(taskRepository).deleteById((Long) any());
        assertTrue(taskService.findAll().isEmpty());
    }

    /**
     * Method under test: {@link TaskService#update(Long, Task)}
     */
    @Test
    void testUpdate() {
        Task task = new Task();
        task.setDescricao("Descricao");
        task.setId(123L);
        task.setTaskPriorityStatus(TaskPriorityStatus.ALTO);
        task.setTaskStatus(TaskStatus.NAO_CONCLUIDO);

        Task task1 = new Task();
        task1.setDescricao("Descricao");
        task1.setId(123L);
        task1.setTaskPriorityStatus(TaskPriorityStatus.ALTO);
        task1.setTaskStatus(TaskStatus.NAO_CONCLUIDO);
        when(taskRepository.save((Task) any())).thenReturn(task1);
        when(taskRepository.getById((Long) any())).thenReturn(task);

        Task task2 = new Task();
        task2.setDescricao("Descricao");
        task2.setId(123L);
        task2.setTaskPriorityStatus(TaskPriorityStatus.ALTO);
        task2.setTaskStatus(TaskStatus.NAO_CONCLUIDO);
        assertSame(task1, taskService.update(123L, task2));
        verify(taskRepository).getById((Long) any());
        verify(taskRepository).save((Task) any());
    }

    /**
     * Method under test: {@link TaskService#updateStatus(Long, Task)}
     */
    @Test
    void testUpdateStatus() {
        Task task = new Task();
        task.setDescricao("Descricao");
        task.setId(123L);
        task.setTaskPriorityStatus(TaskPriorityStatus.ALTO);
        task.setTaskStatus(TaskStatus.NAO_CONCLUIDO);

        Task task1 = new Task();
        task1.setDescricao("Descricao");
        task1.setId(123L);
        task1.setTaskPriorityStatus(TaskPriorityStatus.ALTO);
        task1.setTaskStatus(TaskStatus.NAO_CONCLUIDO);
        when(taskRepository.save((Task) any())).thenReturn(task1);
        when(taskRepository.getById((Long) any())).thenReturn(task);

        Task task2 = new Task();
        task2.setDescricao("Descricao");
        task2.setId(123L);
        task2.setTaskPriorityStatus(TaskPriorityStatus.ALTO);
        task2.setTaskStatus(TaskStatus.NAO_CONCLUIDO);
        assertSame(task1, taskService.updateStatus(123L, task2));
        verify(taskRepository).getById((Long) any());
        verify(taskRepository).save((Task) any());
    }
}

