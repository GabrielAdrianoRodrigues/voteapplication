package br.com.gabrieladriano.voteapplication.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//Por conta da facilidade resolvi utilizar o FeignClient para consumir a api externa
@FeignClient(name = "${userinfo.service.name}", url = "${userinfo.service.url}")
public interface UserInfoClient {

    //Eu nao presciso saber sobre a implementacao deixo com que o spring implemente o mesmo
    @GetMapping("users/{cpf}")
    public ResponseEntity<?> isAbleToVote(@PathVariable("cpf") String cpf);
}
