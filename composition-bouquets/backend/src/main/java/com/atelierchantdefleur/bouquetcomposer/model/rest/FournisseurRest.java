package com.atelierchantdefleur.bouquetcomposer.model.rest;

import lombok.Data;

import java.util.List;

@Data
public class FournisseurRest {

    private Long id;
    private String nom;
    private String numeroSiret;
    private String adresse;
    private String codePostal;
    private String ville;
    private String email;
    private String telephone;
    private List<MateriauRest> materiaux;
    private List<TigeRest> tiges;
}
