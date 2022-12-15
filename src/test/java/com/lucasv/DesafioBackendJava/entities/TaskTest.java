package com.lucasv.DesafioBackendJava.entities;

import com.lucasv.DesafioBackendJava.entities.enums.TaskPriorityStatus;
import com.lucasv.DesafioBackendJava.entities.enums.TaskStatus;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskTest {
    /**
     * Method under test: {@link Task#canEqual(Object)}
     */
    @Test
    void testCanEqual() {
        assertFalse((new Task()).canEqual("Other"));
    }

    /**
     * Method under test: {@link Task#canEqual(Object)}
     */
    @Test
    void testCanEqual2() {
        Task task = new Task();

        Task task1 = new Task();
        task1.setDescricao("teste123");
        task1.setId(123L);
        task1.setTaskStatus(TaskStatus.NAO_CONCLUIDO);
        task1.setTaskPriorityStatus(TaskPriorityStatus.ALTO);
        assertTrue(task.canEqual(task1));
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link Task}
     *   <li>{@link Task#setDescricao(String)}
     *   <li>{@link Task#setId(Long)}
     *   <li>{@link Task#setTaskPriorityStatus(TaskPriorityStatus)}
     *   <li>{@link Task#setTaskStatus(TaskStatus)}
     *   <li>{@link Task#toString()}
     *   <li>{@link Task#getDescricao()}
     *   <li>{@link Task#getId()}
     *   <li>{@link Task#getTaskPriorityStatus()}
     *   <li>{@link Task#getTaskStatus()}
     * </ul>
     */
    @Test
    void testConstructor() {
        Task actualTask = new Task();
        actualTask.setDescricao("Descricao");
        actualTask.setId(123L);
        actualTask.setTaskPriorityStatus(TaskPriorityStatus.ALTO);
        actualTask.setTaskStatus(TaskStatus.NAO_CONCLUIDO);
        String actualToStringResult = actualTask.toString();
        assertEquals("Descricao", actualTask.getDescricao());
        assertEquals(123L, actualTask.getId().longValue());
        assertEquals(TaskPriorityStatus.ALTO, actualTask.getTaskPriorityStatus());
        assertEquals(TaskStatus.NAO_CONCLUIDO, actualTask.getTaskStatus());
        assertEquals("Task(id=123, descricao=Descricao, taskStatus=NAO_CONCLUIDO, taskPriorityStatus=ALTO)",
                actualToStringResult);
    }

    /**
     * Method under test: {@link Task#equals(Object)}
     */
    @Test
    void testEquals() {
        Task task = new Task();
        task.setDescricao("Descricao");
        task.setId(123L);
        task.setTaskPriorityStatus(TaskPriorityStatus.ALTO);
        task.setTaskStatus(TaskStatus.NAO_CONCLUIDO);
        assertNotEquals(task, null);
    }

    /**
     * Method under test: {@link Task#equals(Object)}
     */
    @Test
    void testEquals2() {
        Task task = new Task();
        task.setDescricao("Descricao");
        task.setId(123L);
        task.setTaskPriorityStatus(TaskPriorityStatus.ALTO);
        task.setTaskStatus(TaskStatus.NAO_CONCLUIDO);
        assertNotEquals(task, "Different type to Task");
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Task#equals(Object)}
     *   <li>{@link Task#hashCode()}
     * </ul>
     */
    @Test
    void testEquals3() {
        Task task = new Task();
        task.setDescricao("Descricao");
        task.setId(123L);
        task.setTaskPriorityStatus(TaskPriorityStatus.ALTO);
        task.setTaskStatus(TaskStatus.NAO_CONCLUIDO);
        assertEquals(task, task);
        int expectedHashCodeResult = task.hashCode();
        assertEquals(expectedHashCodeResult, task.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Task#equals(Object)}
     *   <li>{@link Task#hashCode()}
     * </ul>
     */
    @Test
    void testEquals4() {
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
        assertEquals(task, task1);
        int expectedHashCodeResult = task.hashCode();
        assertEquals(expectedHashCodeResult, task1.hashCode());
    }

    /**
     * Method under test: {@link Task#equals(Object)}
     */
    @Test
    void testEquals5() {
        Task task = new Task();
        task.setDescricao(null);
        task.setId(123L);
        task.setTaskPriorityStatus(TaskPriorityStatus.ALTO);
        task.setTaskStatus(TaskStatus.NAO_CONCLUIDO);

        Task task1 = new Task();
        task1.setDescricao("Descricao");
        task1.setId(123L);
        task1.setTaskPriorityStatus(TaskPriorityStatus.ALTO);
        task1.setTaskStatus(TaskStatus.NAO_CONCLUIDO);
        assertNotEquals(task, task1);
    }

    /**
     * Method under test: {@link Task#equals(Object)}
     */
    @Test
    void testEquals6() {
        Task task = new Task();
        task.setDescricao("com.lucasv.DesafioBackendJava.entities.Task");
        task.setId(123L);
        task.setTaskPriorityStatus(TaskPriorityStatus.ALTO);
        task.setTaskStatus(TaskStatus.NAO_CONCLUIDO);

        Task task1 = new Task();
        task1.setDescricao("Descricao");
        task1.setId(123L);
        task1.setTaskPriorityStatus(TaskPriorityStatus.ALTO);
        task1.setTaskStatus(TaskStatus.NAO_CONCLUIDO);
        assertNotEquals(task, task1);
    }

    /**
     * Method under test: {@link Task#equals(Object)}
     */
    @Test
    void testEquals7() {
        Task task = new Task();
        task.setDescricao("Descricao");
        task.setId(1L);
        task.setTaskPriorityStatus(TaskPriorityStatus.ALTO);
        task.setTaskStatus(TaskStatus.NAO_CONCLUIDO);

        Task task1 = new Task();
        task1.setDescricao("Descricao");
        task1.setId(123L);
        task1.setTaskPriorityStatus(TaskPriorityStatus.ALTO);
        task1.setTaskStatus(TaskStatus.NAO_CONCLUIDO);
        assertNotEquals(task, task1);
    }

    /**
     * Method under test: {@link Task#equals(Object)}
     */
    @Test
    void testEquals8() {
        Task task = new Task();
        task.setDescricao("Descricao");
        task.setId(null);
        task.setTaskPriorityStatus(TaskPriorityStatus.ALTO);
        task.setTaskStatus(TaskStatus.NAO_CONCLUIDO);

        Task task1 = new Task();
        task1.setDescricao("Descricao");
        task1.setId(123L);
        task1.setTaskPriorityStatus(TaskPriorityStatus.ALTO);
        task1.setTaskStatus(TaskStatus.NAO_CONCLUIDO);
        assertNotEquals(task, task1);
    }

    /**
     * Method under test: {@link Task#equals(Object)}
     */
    @Test
    void testEquals9() {
        Task task = new Task();
        task.setDescricao("Descricao");
        task.setId(123L);
        task.setTaskPriorityStatus(null);
        task.setTaskStatus(TaskStatus.NAO_CONCLUIDO);

        Task task1 = new Task();
        task1.setDescricao("Descricao");
        task1.setId(123L);
        task1.setTaskPriorityStatus(TaskPriorityStatus.ALTO);
        task1.setTaskStatus(TaskStatus.NAO_CONCLUIDO);
        assertNotEquals(task, task1);
    }

    /**
     * Method under test: {@link Task#equals(Object)}
     */
    @Test
    void testEquals10() {
        Task task = new Task();
        task.setDescricao("Descricao");
        task.setId(123L);
        task.setTaskPriorityStatus(TaskPriorityStatus.MEDIO);
        task.setTaskStatus(TaskStatus.NAO_CONCLUIDO);

        Task task1 = new Task();
        task1.setDescricao("Descricao");
        task1.setId(123L);
        task1.setTaskPriorityStatus(TaskPriorityStatus.ALTO);
        task1.setTaskStatus(TaskStatus.NAO_CONCLUIDO);
        assertNotEquals(task, task1);
    }

    /**
     * Method under test: {@link Task#equals(Object)}
     */
    @Test
    void testEquals11() {
        Task task = new Task();
        task.setDescricao("Descricao");
        task.setId(123L);
        task.setTaskPriorityStatus(TaskPriorityStatus.ALTO);
        task.setTaskStatus(null);

        Task task1 = new Task();
        task1.setDescricao("Descricao");
        task1.setId(123L);
        task1.setTaskPriorityStatus(TaskPriorityStatus.ALTO);
        task1.setTaskStatus(TaskStatus.NAO_CONCLUIDO);
        assertNotEquals(task, task1);
    }

    /**
     * Method under test: {@link Task#equals(Object)}
     */
    @Test
    void testEquals12() {
        Task task = new Task();
        task.setDescricao("Descricao");
        task.setId(123L);
        task.setTaskPriorityStatus(TaskPriorityStatus.ALTO);
        task.setTaskStatus(TaskStatus.CONCLUIDO);

        Task task1 = new Task();
        task1.setDescricao("Descricao");
        task1.setId(123L);
        task1.setTaskPriorityStatus(TaskPriorityStatus.ALTO);
        task1.setTaskStatus(TaskStatus.NAO_CONCLUIDO);
        assertNotEquals(task, task1);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Task#equals(Object)}
     *   <li>{@link Task#hashCode()}
     * </ul>
     */
    @Test
    void testEquals13() {
        Task task = new Task();
        task.setDescricao(null);
        task.setId(123L);
        task.setTaskPriorityStatus(TaskPriorityStatus.ALTO);
        task.setTaskStatus(TaskStatus.NAO_CONCLUIDO);

        Task task1 = new Task();
        task1.setDescricao(null);
        task1.setId(123L);
        task1.setTaskPriorityStatus(TaskPriorityStatus.ALTO);
        task1.setTaskStatus(TaskStatus.NAO_CONCLUIDO);
        assertEquals(task, task1);
        int expectedHashCodeResult = task.hashCode();
        assertEquals(expectedHashCodeResult, task1.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Task#equals(Object)}
     *   <li>{@link Task#hashCode()}
     * </ul>
     */
    @Test
    void testEquals14() {
        Task task = new Task();
        task.setDescricao("Descricao");
        task.setId(null);
        task.setTaskPriorityStatus(TaskPriorityStatus.ALTO);
        task.setTaskStatus(TaskStatus.NAO_CONCLUIDO);

        Task task1 = new Task();
        task1.setDescricao("Descricao");
        task1.setId(null);
        task1.setTaskPriorityStatus(TaskPriorityStatus.ALTO);
        task1.setTaskStatus(TaskStatus.NAO_CONCLUIDO);
        assertEquals(task, task1);
        int expectedHashCodeResult = task.hashCode();
        assertEquals(expectedHashCodeResult, task1.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Task#equals(Object)}
     *   <li>{@link Task#hashCode()}
     * </ul>
     */
    @Test
    void testEquals15() {
        Task task = new Task();
        task.setDescricao("Descricao");
        task.setId(123L);
        task.setTaskPriorityStatus(null);
        task.setTaskStatus(TaskStatus.NAO_CONCLUIDO);

        Task task1 = new Task();
        task1.setDescricao("Descricao");
        task1.setId(123L);
        task1.setTaskPriorityStatus(null);
        task1.setTaskStatus(TaskStatus.NAO_CONCLUIDO);
        assertEquals(task, task1);
        int expectedHashCodeResult = task.hashCode();
        assertEquals(expectedHashCodeResult, task1.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Task#equals(Object)}
     *   <li>{@link Task#hashCode()}
     * </ul>
     */
    @Test
    void testEquals16() {
        Task task = new Task();
        task.setDescricao("Descricao");
        task.setId(123L);
        task.setTaskPriorityStatus(TaskPriorityStatus.ALTO);
        task.setTaskStatus(null);

        Task task1 = new Task();
        task1.setDescricao("Descricao");
        task1.setId(123L);
        task1.setTaskPriorityStatus(TaskPriorityStatus.ALTO);
        task1.setTaskStatus(null);
        assertEquals(task, task1);
        int expectedHashCodeResult = task.hashCode();
        assertEquals(expectedHashCodeResult, task1.hashCode());
    }
}

