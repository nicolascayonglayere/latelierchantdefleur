package com.atelierchantdefleur.bouquetcomposer.model.rest;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CompositionRest {

    private Long id;
    private String nom;
    private LocalDate dateCreation;
    private float dureeCreation;
    private float prixUnitaire;
    private List<ElementCompositionRest> elements;
    private List<ImageCompositionRest> images;
    private Integer tva;
}
