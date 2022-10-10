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
        System.out.println(filter.toString());
        return  AssociatedDTO.parseList(associatedRepository.findAllPaginetedAndFiltered(page_index, 
                                                                                         page_size, 
                                                                                         (filter.getName().isEmpty() || filter.getName() == null) ? "%" : "%"+filter.getName()+"%",
                                                                                         (filter.getCpf().isEmpty() || filter.getCpf() == null) ? "%" : filter.getCpf(),
                                                                                         filter.getAble()
        ));
    }

    public AssociatedDTO findAssociated(Long associatedId) throws Exception {
        return AssociatedDTO.parse(associatedRepository.findById(associatedId).orElseThrow(() -> new NoResultException()));
    }

    public AssociatedDTO createAssociated(AssociatedForm form) throws Exception {
        if (associatedRepository.findByCpf(form.getCpf()).isPresent()) {
           throw new Exception("there is already a associated registered with this CPF : "+form.getCpf());
        }    

        boolean able;

        try {
            able = (userInfoClient.isAbleToVote(form.getCpf()).getBody().toString().contains("UNABLE_TO_VOTE")) ? false : true;
        } catch (Exception e) {
            throw new Exception("provide a valid CPF");      
        }    

        return AssociatedDTO.parse(associatedRepository.save(new Associated(null, form.getName(), able, form.getCpf())));
    }

    public AssociatedDTO changeAssociated(Long associatedId, AssociatedForm form) throws NoResultException {
        Associated toChange = associatedRepository.findById(associatedId).orElseThrow(() -> new NoResultException());
        toChange.setName(form.getName());
        toChange.setCpf(form.getCpf());

        return AssociatedDTO.parse(associatedRepository.save(toChange));
    }

    public AssociatedDTO deleteAssociated(Long associatedId) throws NoResultException {
        Associated toDelete = associatedRepository.findById(associatedId).orElseThrow(() -> new NoResultException());
        toDelete.setAble(false);

        return AssociatedDTO.parse(associatedRepository.save(toDelete));
    }
} 
