package com.atelierchantdefleur.bouquetcomposer.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElementDTO {

    private Long id;
    private String type;
    private String nom;
    private Double quantite;
    private Double prixUnitaire;
}
