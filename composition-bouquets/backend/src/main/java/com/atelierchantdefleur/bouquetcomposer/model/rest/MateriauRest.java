package com.atelierchantdefleur.bouquetcomposer.model.rest;

import lombok.Data;

@Data
public class MateriauRest {

    private Long id;
    private String nom;
    private Double prixUnitaire;
    private FournisseurRest fournisseurRest;
}
