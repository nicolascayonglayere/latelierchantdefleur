package com.atelierchantdefleur.bouquetcomposer.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompositionDTO {

    private Long id;
    private LocalDate dateCreation;
    private LocalTime dureeCreation;
    private Integer prixUnitaire;
    private List<TigeDTO> tiges;
    private List<MateriauDTO> materiaux;

    public void addTige(TigeDTO tige){
        if(Objects.isNull(this.tiges)){
            this.tiges = new ArrayList<>();
        }
        this.tiges.add(tige);
    }

    public void addMateriau(MateriauDTO materiau){
        if(Objects.isNull(this.materiaux)){
            this.materiaux = new ArrayList<>();
        }
        this.materiaux.add(materiau);
    }
}
