package br.com.gabrieladriano.voteapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.gabrieladriano.voteapplication.domain.models.Vote;

//documentado em AgendaRepository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    @Query(value = "select count(vote) from votes v where vote ilike 'sim' and fk_agenda_id = :agendaId", nativeQuery = true)
    int countSimVotes(@Param("agendaId") Long agendaId);

    @Query(value = "select count(vote) from votes v where vote ilike 'n_o' and fk_agenda_id = :agendaId", nativeQuery = true)
    int countNaoVotes(@Param("agendaId") Long agendaId);
}
