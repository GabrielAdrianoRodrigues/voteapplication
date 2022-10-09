package br.com.gabrieladriano.voteapplication.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.gabrieladriano.voteapplication.domain.filters.AgendaFilter;
import br.com.gabrieladriano.voteapplication.domain.forms.AgendaForm;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/v1/agenda")
public class AgendaController {
    
    @GetMapping
    public ResponseEntity<?> findAllAgendas(
        @RequestParam(name = "page_index", required = false, defaultValue =  "0") int pageIndex,
        @RequestParam(name = "page_size" , required = false, defaultValue = "10") int pageSize,
        AgendaFilter agendaFilter) {
        return null;
    }

    @GetMapping("{agendaId}")
    public ResponseEntity<?> findAgenda(@RequestParam("agendaId") Long agendaId) {
        return null;
    }

    @PostMapping
    public ResponseEntity<?> createAgenda(@RequestBody AgendaForm form) {
        return null;
    }
    
    @PostMapping("{agendaId}/vote")
    public ResponseEntity<?> vote(@RequestParam("agendaId") Long agendaId) {
        return null;
    }

}
