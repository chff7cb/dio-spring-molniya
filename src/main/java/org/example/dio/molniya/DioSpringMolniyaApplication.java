package org.example.dio.molniya;

import org.example.dio.molniya.domain.Usuario;
import org.example.dio.molniya.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.util.logging.Logger;

@SpringBootApplication
// @EnableWebSecurity(debug = true)
public class DioSpringMolniyaApplication {
	public static void main(String[] args) {
		SpringApplication.run(DioSpringMolniyaApplication.class, args);
	}

	@Bean
	public Logger getLogger() {
		return Logger.getLogger(DioSpringMolniyaApplication.class.getName());
	}

	@Bean
	public CommandLineRunner migrateDefaultUsers(UsuarioRepository repository) {
		return (args) -> {
			Usuario admin = new Usuario();
			admin.setNome("Thanos");
			admin.setUsuario("admin");
			admin.setSenha("dio123");
			admin.setQuota(1000000L);
			repository.save(admin);
		};
	}
}
