package br.com.gabrieladriano.voteapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gabrieladriano.voteapplication.domain.models.Agenda;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {
    
}
