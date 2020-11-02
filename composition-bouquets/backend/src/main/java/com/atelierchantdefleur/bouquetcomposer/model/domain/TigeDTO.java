package com.atelierchantdefleur.bouquetcomposer.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TigeDTO {

    private Long id;
    private String nom;
    private String nomLatin;
    private Integer prixUnitaire;
}
