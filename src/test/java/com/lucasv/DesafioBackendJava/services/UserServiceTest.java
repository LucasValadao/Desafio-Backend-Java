package com.lucasv.DesafioBackendJava.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.lucasv.DesafioBackendJava.entities.User;
import com.lucasv.DesafioBackendJava.entities.UserRole;
import com.lucasv.DesafioBackendJava.exception.CustomException;
import com.lucasv.DesafioBackendJava.repositories.UserRepository;
import com.lucasv.DesafioBackendJava.security.JwtTokenProvider;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserService.class})
@ExtendWith(SpringExtension.class)
class UserServiceTest {
    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    /**
     * Method under test: {@link UserService#signin(String, String)}
     */
    @Test
    void testSignin() throws AuthenticationException {
        User user = new User();
        user.setEmail("lukasfv10@gmail.com");
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setUserRoles(new ArrayList<>());
        user.setUsername("janedoe");
        when(userRepository.findByUsername((String) any())).thenReturn(user);
        when(jwtTokenProvider.createToken((String) any(), (List<UserRole>) any())).thenReturn("ABC123");
        when(authenticationManager.authenticate((Authentication) any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        assertEquals("ABC123", userService.signin("janedoe", "iloveyou"));
        verify(userRepository).findByUsername((String) any());
        verify(jwtTokenProvider).createToken((String) any(), (List<UserRole>) any());
        verify(authenticationManager).authenticate((Authentication) any());
    }

    /**
     * Method under test: {@link UserService#signin(String, String)}
     */
    @Test
    void testSignin2() throws AuthenticationException {
        User user = new User();
        user.setEmail("lukasfv10@gmail.com");
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setUserRoles(new ArrayList<>());
        user.setUsername("janedoe");
        when(userRepository.findByUsername((String) any())).thenReturn(user);
        when(jwtTokenProvider.createToken((String) any(), (List<UserRole>) any())).thenReturn("ABC123");
        when(authenticationManager.authenticate((Authentication) any())).thenThrow(new AccountExpiredException("Msg"));
        assertThrows(CustomException.class, () -> userService.signin("janedoe", "iloveyou"));
        verify(authenticationManager).authenticate((Authentication) any());
    }

    /**
     * Method under test: {@link UserService#signin(String, String)}
     */
    @Test
    void testSignin3() throws AuthenticationException {
        User user = new User();
        user.setEmail("lukasfv10@gmail.com");
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setUserRoles(new ArrayList<>());
        user.setUsername("janedoe");
        when(userRepository.findByUsername((String) any())).thenReturn(user);
        when(jwtTokenProvider.createToken((String) any(), (List<UserRole>) any())).thenReturn("ABC123");
        when(authenticationManager.authenticate((Authentication) any()))
                .thenThrow(new CustomException("An error occurred", HttpStatus.CONTINUE));
        assertThrows(CustomException.class, () -> userService.signin("janedoe", "iloveyou"));
        verify(authenticationManager).authenticate((Authentication) any());
    }

    /**
     * Method under test: {@link UserService#signup(User)}
     */
    @Test
    void testSignup() {
        User user = new User();
        user.setEmail("lukasfv10@gmail.com");
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setUserRoles(new ArrayList<>());
        user.setUsername("janedoe");
        when(userRepository.existsByUsername((String) any())).thenReturn(true);
        when(userRepository.save((User) any())).thenReturn(user);

        User user1 = new User();
        user1.setEmail("lukasfv10@gmail.com");
        user1.setId(123L);
        user1.setPassword("iloveyou");
        user1.setUserRoles(new ArrayList<>());
        user1.setUsername("janedoe");
        assertThrows(CustomException.class, () -> userService.signup(user1));
        verify(userRepository).existsByUsername((String) any());
    }

    /**
     * Method under test: {@link UserService#signup(User)}
     */
    @Test
    void testSignup2() {
        User user = new User();
        user.setEmail("lukasfv10@gmail.com");
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setUserRoles(new ArrayList<>());
        user.setUsername("janedoe");
        when(userRepository.existsByUsername((String) any())).thenReturn(false);
        when(userRepository.save((User) any())).thenReturn(user);
        when(jwtTokenProvider.createToken((String) any(), (List<UserRole>) any())).thenReturn("ABC123");
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");

        User user1 = new User();
        user1.setEmail("lukasfv10@gmail.com");
        user1.setId(123L);
        user1.setPassword("iloveyou");
        user1.setUserRoles(new ArrayList<>());
        user1.setUsername("janedoe");
        assertEquals("ABC123", userService.signup(user1));
        verify(userRepository).existsByUsername((String) any());
        verify(userRepository).save((User) any());
        verify(jwtTokenProvider).createToken((String) any(), (List<UserRole>) any());
        verify(passwordEncoder).encode((CharSequence) any());
        assertEquals("secret", user1.getPassword());
    }

    /**
     * Method under test: {@link UserService#signup(User)}
     */
    @Test
    void testSignup3() {
        User user = new User();
        user.setEmail("lukasfv10@gmail.com");
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setUserRoles(new ArrayList<>());
        user.setUsername("janedoe");
        when(userRepository.existsByUsername((String) any())).thenReturn(false);
        when(userRepository.save((User) any())).thenReturn(user);
        when(jwtTokenProvider.createToken((String) any(), (List<UserRole>) any())).thenReturn("ABC123");
        when(passwordEncoder.encode((CharSequence) any())).thenThrow(new AccountExpiredException("Msg"));

        User user1 = new User();
        user1.setEmail("lukasfv10@gmail.com");
        user1.setId(123L);
        user1.setPassword("iloveyou");
        user1.setUserRoles(new ArrayList<>());
        user1.setUsername("janedoe");
        assertThrows(AccountExpiredException.class, () -> userService.signup(user1));
        verify(userRepository).existsByUsername((String) any());
        verify(passwordEncoder).encode((CharSequence) any());
    }

    /**
     * Method under test: {@link UserService#search(String)}
     */
    @Test
    void testSearch() {
        User user = new User();
        user.setEmail("lukasfv10@gmail.com");
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setUserRoles(new ArrayList<>());
        user.setUsername("janedoe");
        when(userRepository.findByUsername((String) any())).thenReturn(user);
        assertSame(user, userService.search("janedoe"));
        verify(userRepository).findByUsername((String) any());
    }

    /**
     * Method under test: {@link UserService#search(String)}
     */
    @Test
    void testSearch2() {
        when(userRepository.findByUsername((String) any())).thenThrow(new AccountExpiredException("Usuario nao existe"));
        assertThrows(AccountExpiredException.class, () -> userService.search("janedoe"));
        verify(userRepository).findByUsername((String) any());
    }

    /**
     * Method under test: {@link UserService#whoami(HttpServletRequest)}
     */
    @Test
    void testWhoami() {
        User user = new User();
        user.setEmail("lukasfv10@gmail.com");
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setUserRoles(new ArrayList<>());
        user.setUsername("janedoe");
        when(userRepository.findByUsername((String) any())).thenReturn(user);
        when(jwtTokenProvider.getUsername((String) any())).thenReturn("janedoe");
        when(jwtTokenProvider.resolveToken((HttpServletRequest) any())).thenReturn("ABC123");
        assertSame(user, userService.whoami(new MockHttpServletRequest()));
        verify(userRepository).findByUsername((String) any());
        verify(jwtTokenProvider).getUsername((String) any());
        verify(jwtTokenProvider).resolveToken((HttpServletRequest) any());
    }

    /**
     * Method under test: {@link UserService#whoami(HttpServletRequest)}
     */
    @Test
    void testWhoami2() {
        User user = new User();
        user.setEmail("lukasfv10@gmail.com");
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setUserRoles(new ArrayList<>());
        user.setUsername("janedoe");
        when(userRepository.findByUsername((String) any())).thenReturn(user);
        when(jwtTokenProvider.getUsername((String) any()))
                .thenThrow(new CustomException("An error occurred", HttpStatus.CONTINUE));
        when(jwtTokenProvider.resolveToken((HttpServletRequest) any()))
                .thenThrow(new CustomException("An error occurred", HttpStatus.CONTINUE));
        assertThrows(CustomException.class, () -> userService.whoami(new MockHttpServletRequest()));
        verify(jwtTokenProvider).resolveToken((HttpServletRequest) any());
    }

    /**
     * Method under test: {@link UserService#refresh(String)}
     */
    @Test
    void testRefresh() {
        User user = new User();
        user.setEmail("lukasfv10@gmail.com");
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setUserRoles(new ArrayList<>());
        user.setUsername("janedoe");
        when(userRepository.findByUsername((String) any())).thenReturn(user);
        when(jwtTokenProvider.createToken((String) any(), (List<UserRole>) any())).thenReturn("ABC123");
        assertEquals("ABC123", userService.refresh("janedoe"));
        verify(userRepository).findByUsername((String) any());
        verify(jwtTokenProvider).createToken((String) any(), (List<UserRole>) any());
    }

    /**
     * Method under test: {@link UserService#refresh(String)}
     */
    @Test
    void testRefresh2() {
        User user = new User();
        user.setEmail("lukasfv10@gmail.com");
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setUserRoles(new ArrayList<>());
        user.setUsername("janedoe");
        when(userRepository.findByUsername((String) any())).thenReturn(user);
        when(jwtTokenProvider.createToken((String) any(), (List<UserRole>) any()))
                .thenThrow(new AccountExpiredException("Msg"));
        assertThrows(AccountExpiredException.class, () -> userService.refresh("janedoe"));
        verify(userRepository).findByUsername((String) any());
        verify(jwtTokenProvider).createToken((String) any(), (List<UserRole>) any());
    }
}

