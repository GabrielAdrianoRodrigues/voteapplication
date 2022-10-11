package br.com.gabrieladriano.voteapplication.domain.dtos;

import java.util.List;
import java.util.stream.Collectors;

import br.com.gabrieladriano.voteapplication.domain.models.Agenda;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor@AllArgsConstructor
public class AgendaDTO {
    private Long id;
    private String title;
    private String description;
    private Boolean open;

    //metodos estaticos que encapsulam o parsing entre DTO e entity
    public static List<AgendaDTO> parseList(List<Agenda> toParseList) {
        return toParseList.stream().map(a -> new AgendaDTO(a.getId(), a.getTitle(), a.getDescription(), a.getOpen())).collect(Collectors.toList());
    }

    public static AgendaDTO parse(Agenda toParse) {
        return new AgendaDTO(toParse.getId(), toParse.getTitle(), toParse.getDescription(), toParse.getOpen());
    } 
}
