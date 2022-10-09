package br.com.gabrieladriano.voteapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class VoteapplicationApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoteapplicationApplication.class, args);
	}

}
