package com.atelierchantdefleur.bouquetcomposer.model.rest;

import lombok.Data;

@Data
public class TigeRest {

    private Long id;
    private String nom;
    private String nomLatin;
    private Double prixUnitaire;
    private FournisseurRest fournisseurRest;
}
