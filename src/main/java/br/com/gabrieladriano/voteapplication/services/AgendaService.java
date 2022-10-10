package br.com.gabrieladriano.voteapplication.services;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gabrieladriano.voteapplication.domain.dtos.AgendaDTO;
import br.com.gabrieladriano.voteapplication.domain.filters.AgendaFilter;
import br.com.gabrieladriano.voteapplication.domain.forms.AgendaForm;
import br.com.gabrieladriano.voteapplication.domain.models.Agenda;
import br.com.gabrieladriano.voteapplication.domain.models.Associated;
import br.com.gabrieladriano.voteapplication.domain.models.Vote;
import br.com.gabrieladriano.voteapplication.repositories.AgendaRepository;
import br.com.gabrieladriano.voteapplication.repositories.AssociatedRepository;
import br.com.gabrieladriano.voteapplication.repositories.VoteRepository;

@Service
public class AgendaService {
    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private AssociatedRepository associatedRepository;

    public List<AgendaDTO> findAllPaginetedAndFiltered(int page_index, int page_size, AgendaFilter filter) {
        /*return AgendaDTO.parseList(agendaRepository.findAllPaginetedAndFiltered(page_index,
                                                                                page_size,
                                                                                (filter.getTitle() == null) ? "%" : "%"+filter.getTitle()+"%",
                                                                                (filter.getDescrible() == null) ? "%" : "%"+filter.getDescrible()+"%",
                                                                                filter.getOpen()
        ));*/
        return AgendaDTO.parseList(agendaRepository.findAll());
    }

    public AgendaDTO findAgenda(Long agendaId) throws Exception {
        return AgendaDTO.parse(agendaRepository.findById(agendaId).orElseThrow(() -> new NoResultException()));
    }

    public AgendaDTO createAgenda(AgendaForm form) {
        return AgendaDTO.parse(agendaRepository.save(new Agenda(null, form.getTitle(), form.getDescribe(), true)));
    }

    public AgendaDTO changeAgenda(Long agendaId, AgendaForm form) throws Exception {
        Agenda toChange = agendaRepository.findById(agendaId).orElseThrow(() -> new NoResultException());

        toChange.setTitle(form.getTitle());
        toChange.setDescription(form.getDescribe());

        return AgendaDTO.parse(agendaRepository.save(toChange));
    }

    public String vote(Long agendaId, Long associatedId, String vote) throws Exception {
        Agenda isOpen = agendaRepository.findById(agendaId).orElseThrow(() -> new NoResultException());
        
        if (!isOpen.getOpen()) {
            return "the agenda is closed for voting";
        }

        Associated isAble = associatedRepository.findById(associatedId).orElseThrow(() -> new NoResultException());

        if (!isAble.getAble()) {
            return "the associated is not able to vote";
        }

        if (vote.toUpperCase().equals("SIM") || vote.toUpperCase().equals("NAO")) {
            voteRepository.save(new Vote(null, associatedId, agendaId, vote));
            return "your vote has been saved successfully";
        }

        return "the vote must be only sim or nao";
    }
}
