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
    private String name;
    private String cpf;
    private Boolean able;

    public static List<AssociatedDTO> parseList(List<Associated> toParseList) {
        return toParseList.stream().map(a -> new AssociatedDTO(a.getName(), a.getCpf(), a.getAble())).collect(Collectors.toList());
    }

    public static AssociatedDTO parse(Associated toParse) {
        return new AssociatedDTO(toParse.getName(), toParse.getCpf(), toParse.getAble());
    } 
}
