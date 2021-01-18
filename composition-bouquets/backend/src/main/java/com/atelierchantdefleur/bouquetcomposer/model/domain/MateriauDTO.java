package com.atelierchantdefleur.bouquetcomposer.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MateriauDTO {

    private Long id;
    @NotBlank
    @NotEmpty
    private String nom;
    @Digits(integer = 6, message = "Format du prix unitaire invalide", fraction = 0)
    private Double prixUnitaire;
    private FournisseurDTO fournisseurDTO;
}
