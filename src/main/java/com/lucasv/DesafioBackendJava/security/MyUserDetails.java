package com.lucasv.DesafioBackendJava.security;

import lombok.RequiredArgsConstructor;
import com.lucasv.DesafioBackendJava.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lucasv.DesafioBackendJava.repositories.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyUserDetails implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<User> user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User '" + username + "' nao encontrado");
        }

        return org.springframework.security.core.userdetails.User//
                .withUsername(username)//
                .password(user.get().getSenha())//
                .authorities(user.getUserRoles())//
                .accountExpired(false)//
                .accountLocked(false)//
                .credentialsExpired(false)//
                .disabled(false)//
                .build();
    }

}
