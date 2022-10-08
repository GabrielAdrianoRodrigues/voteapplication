package br.com.gabrieladriano.voteapplication.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.gabrieladriano.voteapplication.domain.filters.AssociatedFilter;
import br.com.gabrieladriano.voteapplication.domain.forms.AssociatedForm;

@RestController
@RequestMapping("api/v1/associated")
public class AssociatedController {
    
    @GetMapping
    public ResponseEntity<?> findAllAssociated(
        @RequestParam(name = "page_index", required = false, defaultValue =  "0") int pageIndex,
        @RequestParam(name = "page_size" , required = false, defaultValue = "10") int pageSize,
        AssociatedFilter associatedFilter) {
        return null;
    }

    @GetMapping("{associatedId}")
    public ResponseEntity<?> findaAssociated(@RequestParam("associatedId") Long associatedId) {
        return null;
    }

    @PostMapping
    public ResponseEntity<?> createAssociated(@RequestBody AssociatedForm form) {
        return null;
    }
}
