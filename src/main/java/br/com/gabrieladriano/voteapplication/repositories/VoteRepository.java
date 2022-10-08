package br.com.gabrieladriano.voteapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gabrieladriano.voteapplication.domain.models.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    
}
