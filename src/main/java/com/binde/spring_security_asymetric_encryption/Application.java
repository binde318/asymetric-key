package com.binde.spring_security_asymetric_encryption;

import com.binde.spring_security_asymetric_encryption.role.Role;
import com.binde.spring_security_asymetric_encryption.role.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(final RoleRepository repository){
		return args -> {
			final Optional<Role> userRole = repository.findByName("ROLE_USER");
			if (userRole.isEmpty()){
				final  Role role = new Role();
				role.setName("ROLE_USER");
				role.setCreatedBy("APP");
				repository.save(role);
			}
		};

	}

}
