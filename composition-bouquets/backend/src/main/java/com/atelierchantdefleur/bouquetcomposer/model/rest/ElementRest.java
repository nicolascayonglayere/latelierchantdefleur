package com.atelierchantdefleur.bouquetcomposer.model.rest;

import lombok.Data;

@Data
public class ElementRest {
    private Long id;
    private String type;
    private String nom;
    private Double quantite;
    private Double prixUnitaire;
}
