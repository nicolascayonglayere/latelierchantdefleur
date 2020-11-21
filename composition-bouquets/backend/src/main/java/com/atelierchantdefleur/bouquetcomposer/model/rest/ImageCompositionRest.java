package com.atelierchantdefleur.bouquetcomposer.model.rest;

import lombok.Data;

@Data
public class ImageCompositionRest {
    private Long id;
    private String nom;
    private String description;
    private byte[] content;

}
