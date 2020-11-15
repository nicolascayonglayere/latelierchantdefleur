package com.atelierchantdefleur.bouquetcomposer.model.rest;

import lombok.Data;

@Data
public class ElementCompositionRest {
    private Long id;
    private String type;
    private String nom;
    private Integer quantite;
    private float prixUnitaire;
}
