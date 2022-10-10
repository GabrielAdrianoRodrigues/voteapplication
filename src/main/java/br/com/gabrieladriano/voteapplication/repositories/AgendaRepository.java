package br.com.gabrieladriano.voteapplication.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.gabrieladriano.voteapplication.domain.models.Agenda;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {
    @Query(value = "SELECT *                                                    "
                 + "    FROM agendas                                            "
                 + "        WHERE UNACCENT(nm_title) ILIKE UNACCENT(:title)     "
                 + "            AND desc_agenda = :desc                         "
                 + "            AND (:open IS NULL OR is_open = :open)          "
                 + "                OFFSET :page_index                          "
                 + "                LIMIT  :page_size                           "
                 + "    ORDER BY nm_title                                       " 
            ,nativeQuery = true
    )
    List<Agenda> findAllPaginetedAndFiltered(int page_index, int page_size, 
                                             @Param("title") String title, 
                                             @Param("desc") String desc,
                                             @Param("open") Boolean open
    );
}
