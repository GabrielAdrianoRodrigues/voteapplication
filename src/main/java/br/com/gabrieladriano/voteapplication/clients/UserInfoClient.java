package br.com.gabrieladriano.voteapplication.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${userinfo.service.name}", url = "${userinfo.service.url}")
public interface UserInfoClient {

    @GetMapping("users/{cpf}")
    public ResponseEntity<?> isAbleToVote(@PathVariable("cpf") String cpf);
}
