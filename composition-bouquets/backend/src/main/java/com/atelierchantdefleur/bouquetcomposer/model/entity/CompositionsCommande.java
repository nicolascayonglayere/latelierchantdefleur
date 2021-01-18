package com.atelierchantdefleur.bouquetcomposer.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="composition_commande")
@Data
public class CompositionsCommande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name="commande_id")
    private Commande commande;
    @ManyToOne
    @JoinColumn(name="composition_id")
    private Composition composition;
    @Column(name="quantite", precision = 6, scale = 2)
    private Integer quantite;
}
