package com.lucasv.DesafioBackendJava.resources;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucasv.DesafioBackendJava.dto.UserDataDTO;
import com.lucasv.DesafioBackendJava.dto.UserResponseDTO;
import com.lucasv.DesafioBackendJava.entities.User;
import com.lucasv.DesafioBackendJava.services.UserService;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UserResource.class})
@ExtendWith(SpringExtension.class)
class UserResourceTest {
    @MockBean
    private ModelMapper modelMapper;

    @Autowired
    private UserResource userResource;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link UserResource#signup(UserDataDTO)}
     */
    @Test
    void testSignup() throws Exception {
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setUserRoles(new ArrayList<>());
        user.setUsername("janedoe");
        when(modelMapper.map((Object) any(), (Class<User>) any())).thenReturn(user);
        when(userService.signup((User) any())).thenReturn("Signup");

        UserDataDTO userDataDTO = new UserDataDTO();
        userDataDTO.setEmail("jane.doe@example.org");
        userDataDTO.setPassword("iloveyou");
        userDataDTO.setUserRoles(new ArrayList<>());
        userDataDTO.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(userDataDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(userResource)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Signup"));
    }

    /**
     * Method under test: {@link UserResource#search(String)}
     */
    @Test
    void testSearch() throws Exception {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setEmail("jane.doe@example.org");
        userResponseDTO.setId(1);
        userResponseDTO.setUserRoles(new ArrayList<>());
        userResponseDTO.setUsername("janedoe");
        when(modelMapper.map((Object) any(), (Class<UserResponseDTO>) any())).thenReturn(userResponseDTO);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setUserRoles(new ArrayList<>());
        user.setUsername("janedoe");
        when(userService.search((String) any())).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/{username}", "janedoe");
        MockMvcBuilders.standaloneSetup(userResource)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"username\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"userRoles\":[]}"));
    }

    /**
     * Method under test: {@link UserResource#whoami(HttpServletRequest)}
     */
    @Test
    void testWhoami() throws Exception {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setEmail("jane.doe@example.org");
        userResponseDTO.setId(1);
        userResponseDTO.setUserRoles(new ArrayList<>());
        userResponseDTO.setUsername("janedoe");
        when(modelMapper.map((Object) any(), (Class<UserResponseDTO>) any())).thenReturn(userResponseDTO);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setUserRoles(new ArrayList<>());
        user.setUsername("janedoe");
        when(userService.whoami((HttpServletRequest) any())).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/me");
        MockMvcBuilders.standaloneSetup(userResource)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"username\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"userRoles\":[]}"));
    }

    /**
     * Method under test: {@link UserResource#login(String, String)}
     */
    @Test
    void testLogin() throws Exception {
        when(userService.signin((String) any(), (String) any())).thenReturn("Signin");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/signin")
                .param("password", "foo")
                .param("username", "foo");
        MockMvcBuilders.standaloneSetup(userResource)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Signin"));
    }

    /**
     * Method under test: {@link UserResource#refresh(HttpServletRequest)}
     */
    @Test
    void testRefresh() throws Exception {
        when(userService.refresh((String) any())).thenReturn("Refresh");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/refresh");
        MockMvcBuilders.standaloneSetup(userResource)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Refresh"));
    }
}

