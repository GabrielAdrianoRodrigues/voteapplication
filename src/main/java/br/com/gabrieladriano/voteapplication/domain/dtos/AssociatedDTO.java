package br.com.gabrieladriano.voteapplication.domain.dtos;

import java.util.List;
import java.util.stream.Collectors;

import br.com.gabrieladriano.voteapplication.domain.models.Associated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor@NoArgsConstructor
public class AssociatedDTO {
    private Long id;
    private String name;
    private String cpf;
    private Boolean able;

    //metodos estaticos que encapsulam o parsing entre DTO e entity
    public static List<AssociatedDTO> parseList(List<Associated> toParseList) {
        return toParseList.stream().map(a -> new AssociatedDTO(a.getId(), a.getName(), a.getCpf(), a.getAble())).collect(Collectors.toList());
    }

    public static AssociatedDTO parse(Associated toParse) {
        return new AssociatedDTO(toParse.getId(), toParse.getName(), toParse.getCpf(), toParse.getAble());
    } 
}
