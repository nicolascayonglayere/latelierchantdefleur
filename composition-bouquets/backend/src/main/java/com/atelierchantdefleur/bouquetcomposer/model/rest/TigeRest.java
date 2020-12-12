package com.atelierchantdefleur.bouquetcomposer.model.rest;

import lombok.Data;

@Data
public class TigeRest {

    private Long id;
    private String nom;
    private String nomLatin;
    private float prixUnitaire;
    private FournisseurRest fournisseurRest;
}
