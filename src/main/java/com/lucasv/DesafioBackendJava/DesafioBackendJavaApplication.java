package com.lucasv.DesafioBackendJava;

import java.util.ArrayList;
import java.util.Arrays;

import com.lucasv.DesafioBackendJava.services.UserService;
import lombok.RequiredArgsConstructor;
import com.lucasv.DesafioBackendJava.entities.User;
import com.lucasv.DesafioBackendJava.entities.UserRole;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
public class DesafioBackendJavaApplication implements CommandLineRunner{

	final UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(DesafioBackendJavaApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... params) throws Exception {
		User admin = new User();
		admin.setUsername("admin");
		admin.setPassword("admin");
		admin.setEmail("admin@email.com");
		admin.setUserRoles(new ArrayList<UserRole>(Arrays.asList(UserRole.ROLE_ADMIN)));

		userService.insert(admin);

		User client = new User();
		client.setUsername("client");
		client.setPassword("client");
		client.setEmail("client@email.com");
		client.setUserRoles(new ArrayList<UserRole>(Arrays.asList(UserRole.ROLE_CLIENT)));

		userService.insert(client);
	}

}
