package br.com.gabrieladriano.voteapplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.gabrieladriano.voteapplication.domain.filters.AssociatedFilter;
import br.com.gabrieladriano.voteapplication.domain.forms.AssociatedForm;
import br.com.gabrieladriano.voteapplication.services.AssociatedService;

//Controler do associado
//seguindo boas praticas utilizei Forms/DTOS 
@RestController
@RequestMapping("api/v1/associated")
public class AssociatedController {

    @Autowired
    private AssociatedService associatedService;

    @GetMapping
    public ResponseEntity<?> findAllAssociated (
        @RequestParam(name = "page_index", required = false, defaultValue =  "0") int pageIndex,
        @RequestParam(name = "page_size" , required = false, defaultValue = "10") int pageSize,
        AssociatedFilter associatedFilter) {
        return ResponseEntity.ok(associatedService.findAllPaginetedAndFiltered(pageIndex, pageSize, associatedFilter));
    }

    @GetMapping("{associatedId}")
    public ResponseEntity<?> findAssociated(@PathVariable("associatedId") Long associatedId) {
        try {
            return ResponseEntity.ok(associatedService.findAssociated(associatedId));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createAssociated(@RequestBody AssociatedForm form) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(associatedService.createAssociated(form));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PutMapping("{associatedId}")
    public ResponseEntity<?> changeAssociated(@PathVariable("associatedId") Long associatedId, @RequestBody AssociatedForm form) {
        try {
            return ResponseEntity.ok(associatedService.changeAssociated(associatedId, form));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }  
    }

    @DeleteMapping("{associatedId}")
    public ResponseEntity<?> deleteAssociated(@PathVariable("associatedId") Long associatedId) {
        try {
            return ResponseEntity.ok(associatedService.deleteAssociated(associatedId));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }  
    }
}
