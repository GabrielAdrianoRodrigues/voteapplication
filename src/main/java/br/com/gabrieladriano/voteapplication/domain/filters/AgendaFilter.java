package br.com.gabrieladriano.voteapplication.domain.filters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor@NoArgsConstructor
public class AgendaFilter {
    private String title;
    private String describle;
    private Boolean open;
}
