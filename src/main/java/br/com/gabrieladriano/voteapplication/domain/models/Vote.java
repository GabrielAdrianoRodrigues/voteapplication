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
@Table(name = "votes")
@AllArgsConstructor@NoArgsConstructor
public class Vote implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;    

    @Column(name = "fk_associated_id", nullable = false)
    private Long associatedId;

    @Column(name = "fk_agenda_id", nullable = false)
    private Long agendaId;

    @Column(name = "vote", nullable = false)
    private String vote;
}
