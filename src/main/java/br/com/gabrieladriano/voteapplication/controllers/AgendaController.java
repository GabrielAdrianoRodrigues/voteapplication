package br.com.gabrieladriano.voteapplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.gabrieladriano.voteapplication.domain.filters.AgendaFilter;
import br.com.gabrieladriano.voteapplication.domain.forms.AgendaForm;
import br.com.gabrieladriano.voteapplication.services.AgendaService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/v1/agenda")
public class AgendaController {
    
    @Autowired
    private AgendaService agendaService;
    
    @GetMapping
    public ResponseEntity<?> findAllAgendas(
        @RequestParam(name = "page_index", required = false, defaultValue =  "0") int pageIndex,
        @RequestParam(name = "page_size" , required = false, defaultValue = "10") int pageSize,
        AgendaFilter agendaFilter) {
        return ResponseEntity.ok(agendaService.findAllPaginetedAndFiltered(pageIndex, pageSize, agendaFilter));
    }

    @GetMapping("{agendaId}")
    public ResponseEntity<?> findAgenda(@PathVariable("agendaId") Long agendaId) {
        try {
            return ResponseEntity.ok(agendaService.findAgenda(agendaId));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createAgenda(@RequestBody AgendaForm form) {
        return ResponseEntity.status(HttpStatus.CREATED).body(agendaService.createAgenda(form));
    }

    @PutMapping("{agendaId}")
    public ResponseEntity<?> changeAgenda(@PathVariable("agendaId") Long agendaId, @RequestBody AgendaForm agendaForm) {
        try {
            return ResponseEntity.ok(agendaService.changeAgenda(agendaId, agendaForm));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("{agendaId}/{associatedId}/vote")
    public ResponseEntity<?> vote(@PathVariable("agendaId") Long agendaId, @PathVariable("associatedId") Long associatedId, String vote) {
        try {
            return ResponseEntity.ok(agendaService.vote(agendaId, associatedId, vote));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
