package com.atelierchantdefleur.bouquetcomposer.model.rest;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class CompositionRest {

    private Long id;
    private LocalDate dateCreation;
    private LocalTime dureeCreation;
    private Integer prixUnitaire;
    private List<TigeRest> tiges;
    private List<MateriauRest> materiaux;
}
