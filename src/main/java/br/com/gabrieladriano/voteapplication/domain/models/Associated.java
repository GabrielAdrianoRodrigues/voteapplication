package br.com.gabrieladriano.voteapplication.domain.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "associateds")
@AllArgsConstructor@NoArgsConstructor
public class Associated implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nm_associated", nullable = false)
    private String name;

    @Column(name = "is_able", nullable = false)
    private Boolean able;

    @Column(name = "desc_cpf", nullable = false)
    private String cpf;
}
