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
    @Column(name="nom")
    private String nom;
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
    @OneToMany(mappedBy = "composition")
    private List<ImageComposition> images;
    @ManyToMany(mappedBy = "compositions")
    private List<Evenement> evenements;
    @Column(name="tva", nullable = false, columnDefinition = "integer default 20")
    private Integer tva;

    @Override
    public String toString() {
        return "Composition{" +
                "id=" + id +
                ", dateCreation=" + dateCreation +
                ", dureeCreation=" + dureeCreation +
                ", prixUnitaire=" + prixUnitaire +
                '}';
    }
}
