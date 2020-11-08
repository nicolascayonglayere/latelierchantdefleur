package com.atelierchantdefleur.bouquetcomposer.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="tige")
@Data
public class Tige implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nom", nullable = false)
    private String nom;
    @Column(name = "nom_latin")
    private String nomLatin;
    @Column(name="prix_unitaire", nullable = false)
    private Integer prixUnitaire;
    @ManyToMany(mappedBy = "tiges")
    private List<Composition> compositions;
    @ManyToOne
    @JoinColumn(name="fournisseur_id", nullable = false)
    private Fournisseur fournisseur;
}
