package br.com.gabrieladriano.voteapplication.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.gabrieladriano.voteapplication.domain.models.Agenda;

//Resolvi por utilizar spring data por conta de ser uma biblioteca muito poderosa existindo inumeros metodos
//ja implementados
public interface AgendaRepository extends JpaRepository<Agenda, Long> {
    //o select ja esta pensado para caso existam parametros nulos
    //resolvi optar pela robustes da anotation @Query que utilizar derived querys ou interfaces Custom e Impl
    //tambem optei por nao utilizar projections pois já existem os DTOS
    @Query(value = "SELECT *                                                                     "
                 + "    FROM agendas                                                             "
                 + "        WHERE UNACCENT(nm_title) ILIKE UNACCENT(:title)                      "
                 + "            AND desc_agenda ILIKE :desc                                      "
                 + "            AND ((:open IS NULL) OR (:open IS NOT NULL AND is_open = :open)) "
                 + "                ORDER BY nm_title                                            "
                 + "                    OFFSET :page_index                                       "
                 + "                    LIMIT  :page_size                                        "
            ,nativeQuery = true
    )
    List<Agenda> findAllPaginetedAndFiltered(@Param("page_index") int page_index, 
                                             @Param("page_size") int page_size, 
                                             @Param("title") String title, 
                                             @Param("desc") String desc,
                                             @Param("open") Boolean open
    );
}
