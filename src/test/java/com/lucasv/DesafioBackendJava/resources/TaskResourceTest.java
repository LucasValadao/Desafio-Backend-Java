package com.lucasv.DesafioBackendJava.resources;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucasv.DesafioBackendJava.entities.Task;
import com.lucasv.DesafioBackendJava.entities.enums.TaskPriorityStatus;
import com.lucasv.DesafioBackendJava.entities.enums.TaskStatus;
import com.lucasv.DesafioBackendJava.services.TaskService;
import com.sun.security.auth.UserPrincipal;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {TaskResource.class})
@ExtendWith(SpringExtension.class)
class TaskResourceTest {
    @Autowired
    private TaskResource taskResource;

    @MockBean
    private TaskService taskService;

    /**
     * Method under test: {@link TaskResource#delete(Long)}
     */
    @Test
    void testDelete() throws Exception {
        doNothing().when(taskService).delete((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/tasks/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(taskResource).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link TaskResource#delete(Long)}
     */
    @Test
    void testDelete2() throws Exception {
        doNothing().when(taskService).delete((Long) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/tasks/{id}", 123L);
        deleteResult.characterEncoding("Encoding");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(taskResource).build().perform(deleteResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link TaskResource#update(Long, Task)}
     */
    @Test
    void testUpdate() throws Exception {
        Task task = new Task();
        task.setDescricao("Descricao");
        task.setId(123L);
        task.setTaskPriorityStatus(TaskPriorityStatus.ALTO);
        task.setTaskStatus(TaskStatus.NAO_CONCLUIDO);
        when(taskService.update((Long) any(), (Task) any())).thenReturn(task);

        Task task1 = new Task();
        task1.setDescricao("Descricao");
        task1.setId(123L);
        task1.setTaskPriorityStatus(TaskPriorityStatus.ALTO);
        task1.setTaskStatus(TaskStatus.NAO_CONCLUIDO);
        String content = (new ObjectMapper()).writeValueAsString(task1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/tasks/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(taskResource)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"descricao\":\"Descricao\",\"taskStatus\":\"NAO_CONCLUIDO\",\"taskPriorityStatus\":\"ALTO\"}"));
    }

    /**
     * Method under test: {@link TaskResource#updateStatus(Long, Task)}
     */
    @Test
    void testUpdateStatus() throws Exception {
        Task task = new Task();
        task.setDescricao("Descricao");
        task.setId(123L);
        task.setTaskPriorityStatus(TaskPriorityStatus.ALTO);
        task.setTaskStatus(TaskStatus.NAO_CONCLUIDO);
        when(taskService.updateStatus((Long) any(), (Task) any())).thenReturn(task);

        Task task1 = new Task();
        task1.setDescricao("Descricao");
        task1.setId(123L);
        task1.setTaskPriorityStatus(TaskPriorityStatus.ALTO);
        task1.setTaskStatus(TaskStatus.NAO_CONCLUIDO);
        String content = (new ObjectMapper()).writeValueAsString(task1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/tasks/status/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(taskResource)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"descricao\":\"Descricao\",\"taskStatus\":\"NAO_CONCLUIDO\",\"taskPriorityStatus\":\"ALTO\"}"));
    }

    /**
     * Method under test: {@link TaskResource#insert(Task)}
     */
    @Test
    void testInsert() throws Exception {
        Task task = new Task();
        task.setDescricao("Descricao");
        task.setId(123L);
        task.setTaskPriorityStatus(TaskPriorityStatus.ALTO);
        task.setTaskStatus(TaskStatus.NAO_CONCLUIDO);
        String content = (new ObjectMapper()).writeValueAsString(task);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(taskResource).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(405));
    }

    /**
     * Method under test: {@link TaskResource#listTasks(TaskPriorityStatus)}
     */
    @Test
    void testListTasks() throws Exception {
        when(taskService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/tasks/{taskps}",
                TaskPriorityStatus.ALTO);
        MockMvcBuilders.standaloneSetup(taskResource)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link TaskResource#listTasks(TaskPriorityStatus)}
     */
    @Test
    void testListTasks2() throws Exception {
        Task task = new Task();
        task.setDescricao("?");
        task.setId(123L);
        task.setTaskPriorityStatus(TaskPriorityStatus.ALTO);
        task.setTaskStatus(TaskStatus.NAO_CONCLUIDO);

        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(task);
        when(taskService.findAll()).thenReturn(taskList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/tasks/{taskps}",
                TaskPriorityStatus.ALTO);
        MockMvcBuilders.standaloneSetup(taskResource)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":123,\"descricao\":\"?\",\"taskStatus\":\"NAO_CONCLUIDO\",\"taskPriorityStatus\":\"ALTO\"}]"));
    }

    /**
     * Method under test: {@link TaskResource#listTasks(TaskPriorityStatus)}
     */
    @Test
    void testListTasks3() throws Exception {
        Task task = new Task();
        task.setDescricao("?");
        task.setId(123L);
        task.setTaskPriorityStatus(TaskPriorityStatus.ALTO);
        task.setTaskStatus(TaskStatus.NAO_CONCLUIDO);

        Task task1 = new Task();
        task1.setDescricao("?");
        task1.setId(123L);
        task1.setTaskPriorityStatus(TaskPriorityStatus.ALTO);
        task1.setTaskStatus(TaskStatus.NAO_CONCLUIDO);

        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task);
        when(taskService.findAll()).thenReturn(taskList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/tasks/{taskps}",
                TaskPriorityStatus.ALTO);
        MockMvcBuilders.standaloneSetup(taskResource)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":123,\"descricao\":\"?\",\"taskStatus\":\"NAO_CONCLUIDO\",\"taskPriorityStatus\":\"ALTO\"},{\"id\":123,\"descricao"
                                        + "\":\"?\",\"taskStatus\":\"NAO_CONCLUIDO\",\"taskPriorityStatus\":\"ALTO\"}]"));
    }

    /**
     * Method under test: {@link TaskResource#listTasks(TaskPriorityStatus)}
     */
    @Test
    void testListTasks4() throws Exception {
        when(taskService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/tasks/{taskps}", "Uri Vars",
                "Uri Vars");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(taskResource).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link TaskResource#listTasks(TaskPriorityStatus)}
     */
    @Test
    void testListTasks5() throws Exception {
        when(taskService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/tasks/{taskps}", "Uri Vars",
                "Uri Vars", "Uri Vars");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(taskResource).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link TaskResource#listTasks(TaskPriorityStatus)}
     */
    @Test
    void testListTasks6() throws Exception {
        when(taskService.findAll()).thenReturn(new ArrayList<>());
        SecurityMockMvcRequestBuilders.FormLoginRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders
                .formLogin();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(taskResource).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link TaskResource#listTasks(TaskPriorityStatus)}
     */
    @Test
    void testListTasks7() throws Exception {
        when(taskService.findAll()).thenReturn(new ArrayList<>());
        SecurityMockMvcRequestBuilders.LogoutRequestBuilder requestBuilder = SecurityMockMvcRequestBuilders.logout();
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(taskResource).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link TaskResource#listTasks(TaskPriorityStatus)}
     */
    @Test
    void testListTasks8() throws Exception {
        when(taskService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/tasks/{taskps}", TaskPriorityStatus.ALTO);
        getResult.secure(true);
        MockMvcBuilders.standaloneSetup(taskResource)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link TaskResource#listTasks(TaskPriorityStatus)}
     */
    @Test
    void testListTasks9() throws Exception {
        when(taskService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/tasks/{taskps}", TaskPriorityStatus.ALTO);
        getResult.content("AAAAAAAA".getBytes("UTF-8"));
        MockMvcBuilders.standaloneSetup(taskResource)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link TaskResource#listTasks(TaskPriorityStatus)}
     */
    @Test
    void testListTasks10() throws Exception {
        when(taskService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/tasks/{taskps}", TaskPriorityStatus.ALTO);
        getResult.content("https://example.org/example");
        MockMvcBuilders.standaloneSetup(taskResource)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link TaskResource#listTasks(TaskPriorityStatus)}
     */
    @Test
    void testListTasks11() throws Exception {
        when(taskService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/tasks/{taskps}", TaskPriorityStatus.ALTO);
        getResult.contentType("text/plain");
        MockMvcBuilders.standaloneSetup(taskResource)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link TaskResource#listTasks(TaskPriorityStatus)}
     */
    @Test
    void testListTasks12() throws Exception {
        when(taskService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/tasks/{taskps}", TaskPriorityStatus.ALTO);
        getResult.accept("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(taskResource).build().perform(getResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(406));
    }

    /**
     * Method under test: {@link TaskResource#listTasks(TaskPriorityStatus)}
     */
    @Test
    void testListTasks13() throws Exception {
        when(taskService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/tasks/{taskps}", TaskPriorityStatus.ALTO);
        getResult.session(new MockHttpSession());
        MockMvcBuilders.standaloneSetup(taskResource)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link TaskResource#listTasks(TaskPriorityStatus)}
     */
    @Test
    void testListTasks14() throws Exception {
        when(taskService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/tasks/{taskps}", TaskPriorityStatus.ALTO);
        getResult.principal(new UserPrincipal("principal"));
        MockMvcBuilders.standaloneSetup(taskResource)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link TaskResource#listTasks(TaskPriorityStatus)}
     */
    @Test
    void testListTasks15() throws Exception {
        when(taskService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/tasks/{taskps}", TaskPriorityStatus.ALTO);
        getResult.characterEncoding("UTF-8");
        MockMvcBuilders.standaloneSetup(taskResource)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link TaskResource#listTasks(TaskPriorityStatus)}
     */
    @Test
    void testListTasks16() throws Exception {
        when(taskService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/tasks/{taskps}", TaskPriorityStatus.ALTO);
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(taskResource)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link TaskResource#listTasks(TaskPriorityStatus)}
     */
    @Test
    void testListTasks17() throws Exception {
        when(taskService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/tasks/{taskps}", TaskPriorityStatus.ALTO);
        getResult.characterEncoding("42");
        MockMvcBuilders.standaloneSetup(taskResource)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link TaskResource#listTasks(TaskPriorityStatus)}
     */
    @Test
    void testListTasks18() throws Exception {
        when(taskService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/tasks/{taskps}", TaskPriorityStatus.ALTO);
        getResult.characterEncoding("Content-Length");
        MockMvcBuilders.standaloneSetup(taskResource)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link TaskResource#listTasks(TaskPriorityStatus)}
     */
    @Test
    void testListTasks19() throws Exception {
        Task task = new Task();
        task.setDescricao("Descricao");
        task.setId(123L);
        task.setTaskPriorityStatus(TaskPriorityStatus.ALTO);
        task.setTaskStatus(TaskStatus.NAO_CONCLUIDO);

        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(task);
        when(taskService.findAll()).thenReturn(taskList);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/tasks/{taskps}", TaskPriorityStatus.ALTO);
        getResult.characterEncoding("UTF-8");
        MockMvcBuilders.standaloneSetup(taskResource)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":123,\"descricao\":\"Descricao\",\"taskStatus\":\"NAO_CONCLUIDO\",\"taskPriorityStatus\":\"ALTO\"}]"));
    }

    /**
     * Method under test: {@link TaskResource#listTasks(TaskPriorityStatus)}
     */
    @Test
    void testListTasks20() throws Exception {
        Task task = new Task();
        task.setDescricao("Descricao");
        task.setId(123L);
        task.setTaskPriorityStatus(TaskPriorityStatus.ALTO);
        task.setTaskStatus(TaskStatus.NAO_CONCLUIDO);

        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(task);
        when(taskService.findAll()).thenReturn(taskList);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/tasks/{taskps}", TaskPriorityStatus.ALTO);
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(taskResource)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":123,\"descricao\":\"Descricao\",\"taskStatus\":\"NAO_CONCLUIDO\",\"taskPriorityStatus\":\"ALTO\"}]"));
    }

    /**
     * Method under test: {@link TaskResource#listTasks(TaskPriorityStatus)}
     */
    @Test
    void testListTasks21() throws Exception {
        Task task = new Task();
        task.setDescricao("Descricao");
        task.setId(123L);
        task.setTaskPriorityStatus(TaskPriorityStatus.ALTO);
        task.setTaskStatus(TaskStatus.NAO_CONCLUIDO);

        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(task);
        when(taskService.findAll()).thenReturn(taskList);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/tasks/{taskps}", TaskPriorityStatus.ALTO);
        getResult.characterEncoding("42");
        MockMvcBuilders.standaloneSetup(taskResource)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":123,\"descricao\":\"Descricao\",\"taskStatus\":\"NAO_CONCLUIDO\",\"taskPriorityStatus\":\"ALTO\"}]"));
    }

    /**
     * Method under test: {@link TaskResource#listTasks(TaskPriorityStatus)}
     */
    @Test
    void testListTasks22() throws Exception {
        Task task = new Task();
        task.setDescricao("Descricao");
        task.setId(123L);
        task.setTaskPriorityStatus(TaskPriorityStatus.ALTO);
        task.setTaskStatus(TaskStatus.NAO_CONCLUIDO);

        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(task);
        when(taskService.findAll()).thenReturn(taskList);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/tasks/{taskps}", TaskPriorityStatus.ALTO);
        getResult.characterEncoding("Content-Length");
        MockMvcBuilders.standaloneSetup(taskResource)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":123,\"descricao\":\"Descricao\",\"taskStatus\":\"NAO_CONCLUIDO\",\"taskPriorityStatus\":\"ALTO\"}]"));
    }

    /**
     * Method under test: {@link TaskResource#listTasks(TaskPriorityStatus)}
     */
    @Test
    void testListTasks23() throws Exception {
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

        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task);
        when(taskService.findAll()).thenReturn(taskList);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/tasks/{taskps}", TaskPriorityStatus.ALTO);
        getResult.characterEncoding("UTF-8");
        MockMvcBuilders.standaloneSetup(taskResource)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":123,\"descricao\":\"Descricao\",\"taskStatus\":\"NAO_CONCLUIDO\",\"taskPriorityStatus\":\"ALTO\"},{\"id\":123"
                                        + ",\"descricao\":\"Descricao\",\"taskStatus\":\"NAO_CONCLUIDO\",\"taskPriorityStatus\":\"ALTO\"}]"));
    }

    /**
     * Method under test: {@link TaskResource#listTasks(TaskPriorityStatus)}
     */
    @Test
    void testListTasks24() throws Exception {
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

        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task);
        when(taskService.findAll()).thenReturn(taskList);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/tasks/{taskps}", TaskPriorityStatus.ALTO);
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(taskResource)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":123,\"descricao\":\"Descricao\",\"taskStatus\":\"NAO_CONCLUIDO\",\"taskPriorityStatus\":\"ALTO\"},{\"id\":123"
                                        + ",\"descricao\":\"Descricao\",\"taskStatus\":\"NAO_CONCLUIDO\",\"taskPriorityStatus\":\"ALTO\"}]"));
    }

    /**
     * Method under test: {@link TaskResource#listTasks(TaskPriorityStatus)}
     */
    @Test
    void testListTasks25() throws Exception {
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

        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task);
        when(taskService.findAll()).thenReturn(taskList);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/tasks/{taskps}", TaskPriorityStatus.ALTO);
        getResult.characterEncoding("42");
        MockMvcBuilders.standaloneSetup(taskResource)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":123,\"descricao\":\"Descricao\",\"taskStatus\":\"NAO_CONCLUIDO\",\"taskPriorityStatus\":\"ALTO\"},{\"id\":123"
                                        + ",\"descricao\":\"Descricao\",\"taskStatus\":\"NAO_CONCLUIDO\",\"taskPriorityStatus\":\"ALTO\"}]"));
    }

    /**
     * Method under test: {@link TaskResource#listTasks(TaskPriorityStatus)}
     */
    @Test
    void testListTasks26() throws Exception {
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

        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task);
        when(taskService.findAll()).thenReturn(taskList);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/tasks/{taskps}", TaskPriorityStatus.ALTO);
        getResult.characterEncoding("Content-Length");
        MockMvcBuilders.standaloneSetup(taskResource)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":123,\"descricao\":\"Descricao\",\"taskStatus\":\"NAO_CONCLUIDO\",\"taskPriorityStatus\":\"ALTO\"},{\"id\":123"
                                        + ",\"descricao\":\"Descricao\",\"taskStatus\":\"NAO_CONCLUIDO\",\"taskPriorityStatus\":\"ALTO\"}]"));
    }

    /**
     * Method under test: {@link TaskResource#listTasks(TaskPriorityStatus)}
     */
    @Test
    void testListTasks27() throws Exception {
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

        Task task2 = new Task();
        task2.setDescricao("Descricao");
        task2.setId(123L);
        task2.setTaskPriorityStatus(TaskPriorityStatus.ALTO);
        task2.setTaskStatus(TaskStatus.NAO_CONCLUIDO);

        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(task2);
        taskList.add(task1);
        taskList.add(task);
        when(taskService.findAll()).thenReturn(taskList);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/tasks/{taskps}", TaskPriorityStatus.ALTO);
        getResult.characterEncoding("UTF-8");
        MockMvcBuilders.standaloneSetup(taskResource)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":123,\"descricao\":\"Descricao\",\"taskStatus\":\"NAO_CONCLUIDO\",\"taskPriorityStatus\":\"ALTO\"},{\"id\":123"
                                        + ",\"descricao\":\"Descricao\",\"taskStatus\":\"NAO_CONCLUIDO\",\"taskPriorityStatus\":\"ALTO\"},{\"id\":123,\"descricao"
                                        + "\":\"Descricao\",\"taskStatus\":\"NAO_CONCLUIDO\",\"taskPriorityStatus\":\"ALTO\"}]"));
    }

    /**
     * Method under test: {@link TaskResource#listTasks(TaskPriorityStatus)}
     */
    @Test
    void testListTasks28() throws Exception {
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

        Task task2 = new Task();
        task2.setDescricao("Descricao");
        task2.setId(123L);
        task2.setTaskPriorityStatus(TaskPriorityStatus.ALTO);
        task2.setTaskStatus(TaskStatus.NAO_CONCLUIDO);

        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(task2);
        taskList.add(task1);
        taskList.add(task);
        when(taskService.findAll()).thenReturn(taskList);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/tasks/{taskps}", TaskPriorityStatus.ALTO);
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(taskResource)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":123,\"descricao\":\"Descricao\",\"taskStatus\":\"NAO_CONCLUIDO\",\"taskPriorityStatus\":\"ALTO\"},{\"id\":123"
                                        + ",\"descricao\":\"Descricao\",\"taskStatus\":\"NAO_CONCLUIDO\",\"taskPriorityStatus\":\"ALTO\"},{\"id\":123,\"descricao"
                                        + "\":\"Descricao\",\"taskStatus\":\"NAO_CONCLUIDO\",\"taskPriorityStatus\":\"ALTO\"}]"));
    }

    /**
     * Method under test: {@link TaskResource#listTasks(TaskPriorityStatus)}
     */
    @Test
    void testListTasks29() throws Exception {
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

        Task task2 = new Task();
        task2.setDescricao("Descricao");
        task2.setId(123L);
        task2.setTaskPriorityStatus(TaskPriorityStatus.ALTO);
        task2.setTaskStatus(TaskStatus.NAO_CONCLUIDO);

        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(task2);
        taskList.add(task1);
        taskList.add(task);
        when(taskService.findAll()).thenReturn(taskList);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/tasks/{taskps}", TaskPriorityStatus.ALTO);
        getResult.characterEncoding("42");
        MockMvcBuilders.standaloneSetup(taskResource)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":123,\"descricao\":\"Descricao\",\"taskStatus\":\"NAO_CONCLUIDO\",\"taskPriorityStatus\":\"ALTO\"},{\"id\":123"
                                        + ",\"descricao\":\"Descricao\",\"taskStatus\":\"NAO_CONCLUIDO\",\"taskPriorityStatus\":\"ALTO\"},{\"id\":123,\"descricao"
                                        + "\":\"Descricao\",\"taskStatus\":\"NAO_CONCLUIDO\",\"taskPriorityStatus\":\"ALTO\"}]"));
    }

    /**
     * Method under test: {@link TaskResource#listTasks(TaskPriorityStatus)}
     */
    @Test
    void testListTasks30() throws Exception {
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

        Task task2 = new Task();
        task2.setDescricao("Descricao");
        task2.setId(123L);
        task2.setTaskPriorityStatus(TaskPriorityStatus.ALTO);
        task2.setTaskStatus(TaskStatus.NAO_CONCLUIDO);

        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(task2);
        taskList.add(task1);
        taskList.add(task);
        when(taskService.findAll()).thenReturn(taskList);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/tasks/{taskps}", TaskPriorityStatus.ALTO);
        getResult.characterEncoding("Content-Length");
        MockMvcBuilders.standaloneSetup(taskResource)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":123,\"descricao\":\"Descricao\",\"taskStatus\":\"NAO_CONCLUIDO\",\"taskPriorityStatus\":\"ALTO\"},{\"id\":123"
                                        + ",\"descricao\":\"Descricao\",\"taskStatus\":\"NAO_CONCLUIDO\",\"taskPriorityStatus\":\"ALTO\"},{\"id\":123,\"descricao"
                                        + "\":\"Descricao\",\"taskStatus\":\"NAO_CONCLUIDO\",\"taskPriorityStatus\":\"ALTO\"}]"));
    }
}

