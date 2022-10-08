package br.com.gabrieladriano.voteapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gabrieladriano.voteapplication.domain.models.Associated;

public interface AssociatedRepository extends JpaRepository<Associated, Long> {
    
}
