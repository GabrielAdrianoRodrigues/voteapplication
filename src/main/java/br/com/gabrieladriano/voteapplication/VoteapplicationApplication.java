package br.com.gabrieladriano.voteapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
//Tag responsavel por habilitar  o feign
@EnableFeignClients
//Habilito o Swagger utilizando a biblioteca OpenUi visto que a SpringFox está desatualizada
@OpenAPIDefinition(
	info = @Info(title = "Voting API", version = "1.0", description = "API Sicredi Challenge"),
	servers = {@Server(url = "https://gb-voteapi-sicredi.herokuapp.com/")}
)
public class VoteapplicationApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoteapplicationApplication.class, args);
	}

}
