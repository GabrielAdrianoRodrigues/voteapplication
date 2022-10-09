package br.com.gabrieladriano.voteapplication.services;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gabrieladriano.voteapplication.clients.UserInfoClient;
import br.com.gabrieladriano.voteapplication.domain.dtos.AssociatedDTO;
import br.com.gabrieladriano.voteapplication.domain.filters.AssociatedFilter;
import br.com.gabrieladriano.voteapplication.domain.forms.AssociatedForm;
import br.com.gabrieladriano.voteapplication.domain.models.Associated;
import br.com.gabrieladriano.voteapplication.repositories.AssociatedRepository;

@Service
public class AssociatedService {
    
    @Autowired
    private AssociatedRepository associatedRepository;

    @Autowired
    private UserInfoClient userInfoClient;

    public List<AssociatedDTO> findAllPaginetedAndFiltered(int page_index, int page_size, AssociatedFilter filter) {
        return  AssociatedDTO.parseList(associatedRepository.findAllPaginetedAndFiltered(page_index, 
                                                                                         page_size, 
                                                                                         (filter.getName() == null) ? "%" : "%"+filter.getName()+"%",
                                                                                         filter.getCpf(),
                                                                                         filter.getAble()
        ));
    }

    public AssociatedDTO findAssociated(Long associatedId) throws NoResultException {
        return AssociatedDTO.parse(associatedRepository.findById(associatedId).orElseThrow(() -> new NoResultException()));
    }

    public AssociatedDTO createAssociatedDTO(AssociatedForm form) {
        return AssociatedDTO.parse(associatedRepository.save(new Associated(null, form.getName(), (userInfoClient.isAbleToVote(form.getCpf()).getBody().toString().contains("ABLE_TO_VOTE")) ? true : false, form.getCpf())));
    }
} 
