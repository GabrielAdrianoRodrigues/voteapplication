package br.com.gabrieladriano.voteapplication.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.gabrieladriano.voteapplication.domain.models.Associated;

public interface AssociatedRepository extends JpaRepository<Associated, Long> {

    @Query(value = "SELECT *                                                    "
                 + "    FROM associateds                                        "
                 + "        WHERE UNACCENT(nm_associated) ILIKE UNACCENT(:name) "
                 + "            AND desc_cpf = :cpf                             "
                 + "            AND (:able IS NULL OR able = :able)             "
                 + "                OFFSET :page_index                          "
                 + "                LIMIT  :page_size                           "
                 + "    ORDER BY nm_associated                                  " 
        ,nativeQuery = true
    )
    List<Associated> findAllPaginetedAndFiltered(int page_index, int page_size, 
                                                 @Param("name") String name, 
                                                 @Param("cpf") String cpf,
                                                 @Param("able") Boolean able
    );

    Optional<Associated> findByCpf(String cpf);
}
