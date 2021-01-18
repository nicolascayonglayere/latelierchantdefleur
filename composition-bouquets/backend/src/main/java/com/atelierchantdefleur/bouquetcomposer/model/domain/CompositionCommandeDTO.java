package com.atelierchantdefleur.bouquetcomposer.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompositionCommandeDTO {
    private Long id;
    private CompositionDTO compositionDTO;
    private Integer quantite;
}
