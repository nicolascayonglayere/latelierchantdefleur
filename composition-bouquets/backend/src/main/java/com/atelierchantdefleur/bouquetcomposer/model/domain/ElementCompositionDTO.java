package com.atelierchantdefleur.bouquetcomposer.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElementCompositionDTO {

    private Long id;
    private String type;
    private String nom;
    private Integer quantite;
    private Integer prixUnitaire;
}
