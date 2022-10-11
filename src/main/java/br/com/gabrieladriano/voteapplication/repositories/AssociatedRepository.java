package br.com.gabrieladriano.voteapplication.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.gabrieladriano.voteapplication.domain.models.Associated;

public interface AssociatedRepository extends JpaRepository<Associated, Long> {

    @Query(value = "SELECT *                                                                     "
                 + "    FROM associateds                                                         "
                 + "        WHERE nm_associated ILIKE :name                                      "
                 + "            AND desc_cpf = :cpf                                              "   
                 + "            AND ((:able IS NULL) OR (:able IS NOT NULL AND is_able = :able)) "
                 + "                ORDER BY nm_associated                                       " 
                 + "                    OFFSET :page_index                                       "
                 + "                    LIMIT  :page_size                                        "
        ,nativeQuery = true
    )
    List<Associated> findAllPaginetedAndFiltered(@Param("page_index") int page_index, 
                                                 @Param("page_size") int page_size, 
                                                 @Param("name") String name, 
                                                 @Param("cpf") String cpf,
                                                 @Param("able") Boolean able
    );

    Optional<Associated> findByCpf(String cpf);
}
