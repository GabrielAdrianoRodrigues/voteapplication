package br.com.gabrieladriano.voteapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition(
	info = @Info(title = "Voting API", version = "1.0", description = "API Sicredi Challenge"),
	servers = {@Server(url = "http://localhost:8080")}
)
public class VoteapplicationApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoteapplicationApplication.class, args);
	}

}
