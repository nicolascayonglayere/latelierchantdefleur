package com.atelierchantdefleur.bouquetcomposer.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name="composition")
@Data
public class Composition implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name="date_creation", nullable = false)
    private LocalDate dateCreation;
    @Column(name="duree_creation", nullable = false)
    private LocalTime dureeCreation;
    @Column(name="prix_unitaire")
    private Integer prixUnitaire;
    @ManyToMany
    @JoinTable(
            name = "elements_composition",
            joinColumns = @JoinColumn(name = "composition_id"),
            inverseJoinColumns = @JoinColumn(name = "elements_id"))
    private List<ElementComposition> elements;
}
