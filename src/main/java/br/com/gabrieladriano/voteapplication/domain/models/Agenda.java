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
@Table(name = "agendas")
@Data
@AllArgsConstructor@NoArgsConstructor
public class Agenda implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nm_agenda", nullable = false)
    private String title;

    @Column(name = "desc_agenda", nullable = true)
    private String description;

    @Column(name = "is_open", nullable = false)
    private Boolean open;
}
