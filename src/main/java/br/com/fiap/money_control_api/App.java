package br.com.fiap.money_control_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableCaching
@OpenAPIDefinition(info = @Info(title = "Money Control API", version = "v1", description = "API do sistema de controle de finanças"))
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}