package com.atelierchantdefleur.bouquetcomposer.model.rest;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CompositionRest {

    private Long id;
    private String nom;
    private LocalDate dateCreation;
    private Double dureeCreation;
    private Double prixUnitaire;
    private List<ElementRest> elements;
    private List<ImageCompositionRest> images;
    private Integer tva;
    private Integer marge;
    private Double tauxHoraire;
}
