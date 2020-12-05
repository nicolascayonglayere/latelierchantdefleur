package com.atelierchantdefleur.bouquetcomposer.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="evenement")
@Data
public class Evenement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name="nom", nullable = false)
    private String nom;
    @Column(name="date_creation", nullable = false)
    private LocalDate dateCreation;
    @Column(name="date_prevue", nullable = false)
    private LocalDate datePrevue;
    @Column(name="forfait_main_oeuvre", nullable = false, columnDefinition = "integer default 0")
    private Integer forfaitMo;
    @Column(name="forfait_deplacement", nullable = false, columnDefinition = "integer default 0")
    private Integer forfaitDplct;
    @ManyToMany
    @JoinTable(
            name = "evenement_composition",
            joinColumns = @JoinColumn(name = "evenement_id"),
            inverseJoinColumns = @JoinColumn(name = "composition_id"))
    private List<Composition> compositions;

}
