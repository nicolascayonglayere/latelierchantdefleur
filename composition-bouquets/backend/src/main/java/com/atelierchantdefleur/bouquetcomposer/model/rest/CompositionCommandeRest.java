package com.atelierchantdefleur.bouquetcomposer.model.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompositionCommandeRest {

    private Long id;
    private CompositionRest composition;
    private Integer quantite;
}
