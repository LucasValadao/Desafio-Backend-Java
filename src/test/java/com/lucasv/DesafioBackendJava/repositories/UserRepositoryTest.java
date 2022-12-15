package com.lucasv.DesafioBackendJava.repositories;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.lucasv.DesafioBackendJava.entities.User;

import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {UserRepository.class})
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.lucasv.DesafioBackendJava.entities"})
@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    /**
     * Method under test: {@link UserRepository#existsByUsername(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testExistsByUsername() {

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setUserRoles(new ArrayList<>());
        user.setUsername("janedoe");
        userRepository.save(user);

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        user1.setUserRoles(new ArrayList<>());
        user1.setUsername("janedoe");
        userRepository.save(user1);
        userRepository.existsByUsername("foo");
    }

    /**
     * Method under test: {@link UserRepository#existsByUsername(String)}
     */
    @Test
    void testExistsByUsername2() {
        User user = new User();
        user.setEmail("Email");
        user.setPassword("iloveyou");
        user.setUserRoles(new ArrayList<>());
        user.setUsername("Username");

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        user1.setUserRoles(new ArrayList<>());
        user1.setUsername("janedoe");
        userRepository.save(user);
        userRepository.save(user1);
        assertFalse(userRepository.existsByUsername("foo"));
    }

    /**
     * Method under test: {@link UserRepository#findByUsername(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFindByUsername() {

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setUserRoles(new ArrayList<>());
        user.setUsername("janedoe");
        userRepository.save(user);

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        user1.setUserRoles(new ArrayList<>());
        user1.setUsername("janedoe");
        userRepository.save(user1);
        userRepository.findByUsername("foo");
    }

    /**
     * Method under test: {@link UserRepository#findByUsername(String)}
     */
    @Test
    void testFindByUsername2() {
        User user = new User();
        user.setEmail("Email");
        user.setPassword("iloveyou");
        user.setUserRoles(new ArrayList<>());
        user.setUsername("Username");

        User user1 = new User();
        user1.setEmail("jane.doe@example.org");
        user1.setPassword("iloveyou");
        user1.setUserRoles(new ArrayList<>());
        user1.setUsername("janedoe");
        userRepository.save(user);
        userRepository.save(user1);
        assertNull(userRepository.findByUsername("foo"));
    }
}

