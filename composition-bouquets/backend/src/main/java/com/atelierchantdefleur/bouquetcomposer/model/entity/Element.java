package com.atelierchantdefleur.bouquetcomposer.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="element")
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class Element {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name="type", nullable = false)
    private String type;
    @Column(name="nom", nullable = false)
    private String nom;
    @Column(name="prix_unitaire", nullable = false, precision=7, scale=3)
    private Double prixUnitaire;
    @OneToMany(mappedBy = "element")
    private List<ElementComposition> compositions;
}
