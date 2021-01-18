package com.atelierchantdefleur.bouquetcomposer.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="element_compo")
@Data
public class ElementComposition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name="element_id")
    private Element element;
    @ManyToOne
    @JoinColumn(name="composition_id")
    private Composition composition;
    @Column(name="quantite", precision = 6, scale = 2)
    private Double quantite;
}
