package com.atelierchantdefleur.bouquetcomposer.model.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoefficientVariableRest {
    private Integer marge;
    private Integer tauxHoraire;
    private Integer tva;
}
