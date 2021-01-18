package com.atelierchantdefleur.bouquetcomposer.model.rest;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CommandeRest {
    private Long id;
    private String nom;
    private LocalDate dateCreation;
    private LocalDate datePrevue;
    private List<CompositionCommandeRest> compositions;
    private Integer forfaitMo;
    private Integer forfaitDplct;
    private ClientRest clientRest;
    private Double budget;
}
