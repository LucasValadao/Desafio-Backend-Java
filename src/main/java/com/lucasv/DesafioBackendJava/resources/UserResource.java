package com.lucasv.DesafioBackendJava.resources;

import com.lucasv.DesafioBackendJava.dto.UserDataDTO;
import com.lucasv.DesafioBackendJava.dto.UserResponseDTO;
import com.lucasv.DesafioBackendJava.entities.User;
import com.lucasv.DesafioBackendJava.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/users")
@Api(tags = "users")
@RequiredArgsConstructor
public class UserResource {

    private final ModelMapper modelMapper;
    private final UserService service;

    @PostMapping("/signin")
    @ApiOperation(value = "${UserResource.signin}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Algo deu errado"),
            @ApiResponse(code = 422, message = "Nome de usuario/senha invalidos")})
    public String login(@ApiParam("Username") @RequestParam String username,@ApiParam("Password") @RequestParam String password){
        return service.signin(username,password) ;
    }

    @PostMapping("/signup")
    @ApiOperation(value = "${UserResource.signup}")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Algo deu errado"),
            @ApiResponse(code = 403, message = "Acesso negado"),
            @ApiResponse(code = 422, message = "Nome de usuario ja esta em uso")})
    public String signup(@ApiParam("Signup User") @RequestBody UserDataDTO user) {
        return service.signup(modelMapper.map(user,User.class));
    }


    @GetMapping(value = "/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "${UserResource.search}", response = UserResponseDTO.class, authorizations = { @Authorization(value="apiKey") })
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Algo deu errado"),
            @ApiResponse(code = 403, message = "Acesso negado"),
            @ApiResponse(code = 404, message = "O usuario nao existe"),
            @ApiResponse(code = 500, message = "JWT Token invalido ou expirado")})
    public UserResponseDTO search(@ApiParam("Username") @PathVariable String username) {
        return modelMapper.map(service.search(username), UserResponseDTO.class);
    }

    @GetMapping(value = "/me")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    @ApiOperation(value = "${UserResource.me}", response = UserResponseDTO.class, authorizations = { @Authorization(value="apiKey") })
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Algo deu errado"),
            @ApiResponse(code = 403, message = "Acesso negado"),
            @ApiResponse(code = 500, message = "JWT Token invalido ou expirado")})
    public UserResponseDTO whoami(HttpServletRequest req) {
        return modelMapper.map(service.whoami(req), UserResponseDTO.class);
    }

    @GetMapping("/refresh")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public String refresh(HttpServletRequest req) {
        return service.refresh(req.getRemoteUser());
    }

}
