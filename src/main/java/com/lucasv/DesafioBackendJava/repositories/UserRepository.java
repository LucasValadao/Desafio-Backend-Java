package com.lucasv.DesafioBackendJava.repositories;

import com.lucasv.DesafioBackendJava.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
