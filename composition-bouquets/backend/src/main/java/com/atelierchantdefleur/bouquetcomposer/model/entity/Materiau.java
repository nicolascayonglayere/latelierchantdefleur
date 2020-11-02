package com.atelierchantdefleur.bouquetcomposer.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="materiau")
@Data
public class Materiau implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nom", nullable = false)
    private String nom;
    @Column(name="prix_unitaire")
    private Integer prixUnitaire;
    @ManyToMany(mappedBy = "materiaux")
    private List<Composition> compositions;

}
