package com.lucasv.DesafioBackendJava.resources;

import com.lucasv.DesafioBackendJava.dto.UserDataDTO;
import com.lucasv.DesafioBackendJava.dto.UserResponseDTO;
import com.lucasv.DesafioBackendJava.entities.User;
import com.lucasv.DesafioBackendJava.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserResource {

    private final ModelMapper modelMapper;
    private final UserService service;

    @PostMapping("/insert")
    public String insert(@Valid @RequestBody UserDataDTO user) {
        return service.insert(modelMapper.map(user,User.class));
    }

    @PostMapping("/signin")
    public String login(@RequestParam String username,@RequestParam String password){
            return service.signin(username,password) ;
    }

    @GetMapping(value = "/{username}")
    public UserResponseDTO search(@PathVariable String username) {
        return modelMapper.map(service.search(username), UserResponseDTO.class);
    }

    @GetMapping(value = "/me")
    public UserResponseDTO whoami(HttpServletRequest req) {
        return modelMapper.map(service.whoami(req), UserResponseDTO.class);
    }

    @GetMapping("/refresh")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public String refresh(HttpServletRequest req) {
        return service.refresh(req.getRemoteUser());
    }

}
