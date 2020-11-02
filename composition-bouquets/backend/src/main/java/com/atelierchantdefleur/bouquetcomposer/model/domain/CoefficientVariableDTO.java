package com.atelierchantdefleur.bouquetcomposer.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoefficientVariableDTO {
    private Integer marge;
    private Integer tauxHoraire;
    private Integer tva;
}
