package br.com.gabrieladriano.voteapplication.domain.filters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor@NoArgsConstructor
public class AssociatedFilter {
    private String name;
    private Boolean able;
    private String cpf;
}
