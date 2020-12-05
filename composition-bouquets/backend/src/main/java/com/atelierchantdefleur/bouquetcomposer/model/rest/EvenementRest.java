package com.atelierchantdefleur.bouquetcomposer.model.rest;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class EvenementRest {
    private Long id;
    private String nom;
    private LocalDate dateCreation;
    private LocalDate datePrevue;
    private List<CompositionRest> compositions;
    private Integer forfaitMo;
    private Integer forfaitDplct;
}
