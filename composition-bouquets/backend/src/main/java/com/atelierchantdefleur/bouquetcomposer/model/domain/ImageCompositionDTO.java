package com.atelierchantdefleur.bouquetcomposer.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageCompositionDTO {
    private Long id;
    private String nom;
    private String description;
    private byte[] content;
}
