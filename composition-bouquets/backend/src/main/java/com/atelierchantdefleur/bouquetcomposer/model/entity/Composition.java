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
    @Column(name="prix_unitaire", precision = 7, scale = 3)
    private Double prixUnitaire;
    @OneToMany(mappedBy = "composition")
    private List<ElementComposition> elements;
    @OneToMany(mappedBy = "composition")
    private List<ImageComposition> images;
    @OneToMany(mappedBy = "composition")
    private List<CompositionsCommande> commandes;
//    @ManyToMany(cascade = {
//            CascadeType.PERSIST,
//            CascadeType.MERGE
//    })
//    @JoinTable(
//            name = "commande_composition",
//            joinColumns = @JoinColumn(name = "composition_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "commande_id", referencedColumnName = "id"))
//    private List<Commande> commandes;
    @Column(name="tva", nullable = false, columnDefinition = "integer default 20")
    private Integer tva;
    @Column (name="marge", nullable = false, columnDefinition = "integer default 60")
    private Integer marge;
    @Column (name="taux_horaire", nullable = false, columnDefinition = "decimal(6, 2) default 35")
    private Double tauxHoraire;

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
