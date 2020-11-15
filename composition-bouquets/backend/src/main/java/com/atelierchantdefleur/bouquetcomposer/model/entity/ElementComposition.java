package com.atelierchantdefleur.bouquetcomposer.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="element")
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class ElementComposition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name="type", nullable = false)
    private String type;
    @Column(name="nom", nullable = false)
    private String nom;
    @Column(name="prix_unitaire", nullable = false)
    private Integer prixUnitaire;
    @ManyToMany(mappedBy = "elements")
    private List<Composition> compositions;
}
